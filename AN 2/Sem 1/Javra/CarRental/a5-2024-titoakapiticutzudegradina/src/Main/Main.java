package Main;

import Domain.Car;
import Domain.Rental;
import Repository.RentalRepository;
import Repository.CarRepository;
import Repository.Repository;
import Service.Service;
import Repository.TextFileRentalRepository;
import Repository.TextFileCarRepository;
import Repository.BinaryFileRentalRepository;
import Repository.BinaryFileCarRepository;
import Repository.DataBaseRentalRepository;
import Repository.DataBaseCarRepository;
//import Repository.JsonFileRentalRepository;
//import Repository.JsonFileCarRepository;
import Repository.XmlFileRentalRepository;
import Repository.XmlFileCarRepository;
import UI.UI;
import Validator.CarValidator;
import Validator.RentalValidator;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Main {

    //Main.Main method
    public static void main(String[] args){
        Properties properties = new Properties();
        Repository<Integer, Rental>  rentalRepo = null;
        Repository<Integer, Car> carRepo = null;
        CarValidator carValidator = new CarValidator();
        RentalValidator rentalValidator = new RentalValidator();

        try{
            properties.load(new FileReader("settings.properties"));

            String RepositoryType = properties.getProperty("src/Repository");
            String rentalFilename = properties.getProperty("Rentals");
            String carFilename = properties.getProperty("Cars");

            if(RepositoryType.equals("Text")){
                rentalRepo = new TextFileRentalRepository(rentalFilename);
                carRepo = new TextFileCarRepository(carFilename);
            }
            if(RepositoryType.equals("Memory")){
                rentalRepo = new RentalRepository();
                carRepo = new CarRepository();
            }
            if(RepositoryType.equals("Binary")){
                rentalRepo = new BinaryFileRentalRepository(rentalFilename);
                carRepo = new BinaryFileCarRepository(carFilename);
            }
            if(RepositoryType.equals("DataBase")){
                rentalRepo = new DataBaseRentalRepository(rentalFilename);
                carRepo = new DataBaseCarRepository(carFilename);
            }
            if(RepositoryType.equals("JSON")){
                //rentalRepo = new JsonFileRentalRepository(rentalFilename);
                //carRepo = new JsonFileCarRepository(carFilename);
            }
            if(RepositoryType.equals("XML")){
                rentalRepo = new XmlFileRentalRepository(rentalFilename);
                carRepo = new XmlFileCarRepository(carFilename);
            }


            Service service = new Service(rentalRepo,carRepo, carValidator, rentalValidator);
            if(RepositoryType.equals("Memory")){
                service.addCar(1,"Audi","A4",2010,100);
                service.addCar(2,"BMW","X5",2015,200);

                service.addRental(1,"Audi","A4",2010,100,1,"John","1234567890",5);
                service.addRental(2,"BMW","X5",2015,200,2,"Alice","0987654321",3);
                service.addRental(1,"Audi","A4",2010,100,3,"Bob","1234567890",5);
                service.addRental(2,"BMW","X5",2015,200,4,"Bob","1234567890",3);
            }
            UI ui = new UI(service);
            ui.run();

        } catch (IOException exception){
            throw new RuntimeException(exception);
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        }
    }
}
