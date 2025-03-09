package Repository;

import Domain.Car;
import Domain.Rental;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.Iterator;

public class TextFileRentalRepository extends FileRepository<Integer, Rental>{
    public TextFileRentalRepository(String filename) throws ParserConfigurationException, IOException, SAXException {
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
                Integer rentalid = Integer.parseInt(tokens[0]);
                Integer carId = Integer.parseInt(tokens[1]);
                Car car = new Car(carId, "mock","mock", 0, 0);
                String name = tokens[2];
                String phoneNumber = tokens[3];
                Integer daysForRental = Integer.parseInt(tokens[4]);
                Rental rental = new Rental(rentalid, car, name, phoneNumber, daysForRental);
                //We need the actual car from the car text file
                TextFileCarRepository carRepository = new TextFileCarRepository("data/car.txt");
                for(Car actualCar : carRepository.getAll()){
                    if(actualCar.getId().equals(carId)){
                        rental.setCar(actualCar);
                        break;
                    }
                }
                super.add(rentalid, rental);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException | ParserConfigurationException | SAXException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    void writeToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(this.filename))){
            Iterator<Rental> iteratorRental = super.iterator();
            while(iteratorRental.hasNext()){
                Rental rental = iteratorRental.next();
                bw.write(rental.getId() + "," + rental.getCar().getId() + "," + rental.getNameForRental() + "," + rental.getPhoneNumber() + "," + rental.getDaysForRental() + "\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
