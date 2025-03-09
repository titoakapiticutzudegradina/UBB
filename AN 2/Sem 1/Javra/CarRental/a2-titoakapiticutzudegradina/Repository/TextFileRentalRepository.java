package Repository;

import Domain.Car;
import Domain.Rental;
import java.io.*;
import java.util.Iterator;

public class TextFileRentalRepository extends FileRepository<Integer, Rental>{
    public TextFileRentalRepository(String filename) {
        super(filename);
    }

    @Override
    void readFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(this.filename))){
            String line;
            while((line = br.readLine()) != null){
                String[] tokens = line.split(",");
                if(tokens.length != 8)
                    continue;
                Integer rentalid = Integer.parseInt(tokens[0]);
                String carModel = tokens[1];
                String carBrand = tokens[2];
                Integer yearOfProduction = Integer.parseInt(tokens[3]);
                Double pricePerDay = Double.parseDouble(tokens[4]);
                Car car = new Car(carModel, carBrand, yearOfProduction, pricePerDay);
                String name = tokens[5];
                String phoneNumber = tokens[6];
                Integer daysForRental = Integer.parseInt(tokens[7]);
                Rental rental = new Rental(rentalid, car, name, phoneNumber, daysForRental);
                super.add(rentalid, rental);
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
            Iterator<Rental> iteratorRental = super.iterator();
            while(iteratorRental.hasNext()){
                Rental rental = iteratorRental.next();
                bw.write(rental.getId() + "," + rental.getCar().getCarModel() + "," + rental.getCar().getCarBrand() + "," + rental.getCar().getYearProduction() + "," + rental.getCar().getPricePerDay() + "," + rental.getNameForRental() + "," + rental.getPhoneNumber() + "," + rental.getDaysForRental() + "\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
