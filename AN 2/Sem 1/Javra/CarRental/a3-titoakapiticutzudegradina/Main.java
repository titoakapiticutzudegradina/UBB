import Domain.Rental;
import Repository.RentalRepository;
import Repository.Repository;
import Service.Service;
import Repository.TextFileRentalRepository;
import Repository.BinaryFileRentalRepository;
import UI.UI;
import Validator.CarValidator;
import Validator.RentalValidator;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Main {

    //Main method
    public static void main(String[] args){
        Properties properties = new Properties();
        Repository<Integer, Rental>  repo = null;
        CarValidator carValidator = new CarValidator();
        RentalValidator rentalValidator = new RentalValidator();

        try{
            properties.load(new FileReader("settings.properties"));

            String RepositoryType = properties.getProperty("Repository");
            String rentalFilename = properties.getProperty("Rentals");

            if(RepositoryType.equals("Text")){
                repo = new TextFileRentalRepository(rentalFilename);
            }
            if(RepositoryType.equals("Memory")){
                repo = new RentalRepository();
            }
            if(RepositoryType.equals("Binary")){
                repo = new BinaryFileRentalRepository(rentalFilename);
            }

            Service service = new Service(repo, carValidator, rentalValidator);
            UI ui = new UI(service);
            ui.run();

        } catch (IOException exception){
            throw new RuntimeException(exception);
        }
    }
}
