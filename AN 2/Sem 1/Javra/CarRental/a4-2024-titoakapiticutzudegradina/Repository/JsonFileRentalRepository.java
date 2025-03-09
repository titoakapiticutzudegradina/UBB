package Repository;

import Domain.Car;
import Domain.Rental;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

public class JsonFileRentalRepository extends FileRepository<Integer, Rental> {
    public JsonFileRentalRepository(String filename) throws ParserConfigurationException, IOException, SAXException {
        super(filename);
    }

    @Override
    protected void readFromFile() {
        JSONParser parser = new JSONParser();

        try(FileReader fileReader = new FileReader(this.filename)){
            Object parsedObject = parser.parse(fileReader);
            JSONArray jsonObjectArray = (JSONArray) parsedObject;

            for(Object object : jsonObjectArray){
                JSONObject jsonObject = (JSONObject)object;
                JSONObject rentalObject = (JSONObject)jsonObject.get("rental");

                Integer id = Integer.parseInt(rentalObject.get("id").toString());
                Integer carId = Integer.parseInt(rentalObject.get("carId").toString());
                String clientName = rentalObject.get("clientName").toString();
                String clientPhoneNumber = rentalObject.get("clientPhoneNumber").toString();
                Integer daysForRental = Integer.parseInt(rentalObject.get("daysForRental").toString());

                Car car = new Car(carId, "mock", "mock", 0, 0);
                Rental rental = new Rental(id, car, clientName, clientPhoneNumber, daysForRental);
                JsonFileCarRepository jsonCarRepo = new JsonFileCarRepository("data/car.json");
                for(Car actualCar : jsonCarRepo.getAll()){
                    if(actualCar.getId().equals(carId)){
                        rental.setCar(actualCar);
                        break;
                    }
                }
                super.add(id, rental);
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void writeToFile() {
        Iterator<Rental> rentalIterator = super.iterator();
        JSONArray jsonArray = new JSONArray();

        while(rentalIterator.hasNext()){
            Rental rental = rentalIterator.next();
            JSONObject rentalObject = new JSONObject();

            rentalObject.put("id", rental.getId());
            rentalObject.put("carId", rental.getCar().getId());
            rentalObject.put("clientName", rental.getNameForRental());
            rentalObject.put("clientPhoneNumber", rental.getPhoneNumber());
            rentalObject.put("daysForRental", rental.getDaysForRental());

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("rental", rentalObject);

            jsonArray.add(jsonObject);
        }

        try(FileWriter fileWriter = new FileWriter(this.filename)){
            fileWriter.write(jsonArray.toJSONString());
            fileWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
