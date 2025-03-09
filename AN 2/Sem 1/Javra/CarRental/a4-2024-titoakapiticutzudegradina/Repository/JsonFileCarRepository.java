package Repository;

import Domain.Car;
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

public class JsonFileCarRepository extends FileRepository<Integer, Car>{
    public JsonFileCarRepository(String filename) throws ParserConfigurationException, IOException, SAXException {
        super(filename);
    }

    @Override
    protected void readFromFile(){
        JSONParser parser = new JSONParser();

        try(FileReader fileReader = new FileReader(this.filename)){
            Object parsedObject = parser.parse(fileReader);
            JSONArray jsonObjectArray = (JSONArray) parsedObject;

            for(Object object : jsonObjectArray){
                JSONObject jsonObject = (JSONObject) object;
                JSONObject carObject = (JSONObject) jsonObject.get("car");

                Integer id = Integer.parseInt(carObject.get("id").toString());
                String carBrand = carObject.get("carBrand").toString();
                String carModel = carObject.get("carModel").toString();
                Integer yearProduction = Integer.parseInt(carObject.get("yearProduction").toString());
                Double pricePerDay = Double.parseDouble(carObject.get("pricePerDay").toString());
                Car car = new Car(id, carBrand, carModel, yearProduction, pricePerDay);
                super.add(id, car);
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void writeToFile(){
        Iterator<Car> carIterator = super.iterator();
        JSONArray jsonArray = new JSONArray();

        while(carIterator.hasNext()){
            Car car = carIterator.next();
            JSONObject carObject = new JSONObject();

            carObject.put("id",car.getId());
            carObject.put("carBrand",car.getCarBrand());
            carObject.put("carModel",car.getCarModel());
            carObject.put("yearProduction",car.getYearProduction());
            carObject.put("pricePerDay",car.getPricePerDay());

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("car",carObject);
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
