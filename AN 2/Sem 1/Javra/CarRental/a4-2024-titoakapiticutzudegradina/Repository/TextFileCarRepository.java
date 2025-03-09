package Repository;
import Domain.Car;
import Domain.Rental;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.Iterator;

public class TextFileCarRepository extends FileRepository<Integer,Car>{
    public TextFileCarRepository(String filename) throws ParserConfigurationException, IOException, SAXException {
        super(filename);
    }

    @Override
    void readFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(this.filename))){
            String line;
            while((line = br.readLine()) != null){
                String[] tokens = line.split(",");
                if(tokens.length != 5)
                    continue;
                Integer carId = Integer.parseInt(tokens[0]);
                String carModel = tokens[1];
                String carBrand = tokens[2];
                Integer yearOfProduction = Integer.parseInt(tokens[3]);
                Double pricePerDay = Double.parseDouble(tokens[4]);
                Car car = new Car(carId,carModel, carBrand, yearOfProduction, pricePerDay);
                super.add(carId, car);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    void writeToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(this.filename))){
            Iterator<Car> iteratorCar = super.iterator();
            while(iteratorCar.hasNext()){
                Car car = iteratorCar.next();
                bw.write(car.getId() + "," + car.getCarModel() + "," + car.getCarBrand() + "," + car.getYearProduction() + "," + car.getPricePerDay() + "\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
