package GUI;

import Domain.Car;
import Domain.Rental;
import Repository.Repository;
import Repository.CarRepository;
import Repository.RentalRepository;
import Repository.TextFileCarRepository;
import Repository.TextFileRentalRepository;
import Repository.BinaryFileCarRepository;
import Repository.BinaryFileRentalRepository;
import Repository.DataBaseCarRepository;
import Repository.DataBaseRentalRepository;
//import Repository.JsonFileCarRepository;
//import Repository.JsonFileRentalRepository;
import Repository.XmlFileCarRepository;
import Repository.XmlFileRentalRepository;


import Service.Service;
import Validator.CarValidator;
import Validator.RentalValidator;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

public class GUI extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view.fxml"));
        //loader.setClassLoader(getClass().getClassLoader());

        Repository<Integer, Car> carRepo = null;
        Repository<Integer, Rental> rentalRepo = null;
        CarValidator carValidator = new CarValidator();
        RentalValidator rentalValidator = new RentalValidator();
        Properties properties = new Properties();
        try{
            properties.load(new FileReader("settings.properties"));

            String repoType = properties.getProperty("Repository");
            String carFilename = properties.getProperty("Cars");
            String rentalFilename = properties.getProperty("Rentals");

            if(repoType.equals("Text")){
                rentalRepo = new TextFileRentalRepository(rentalFilename);
                carRepo = new TextFileCarRepository(carFilename);
            }
            if(repoType.equals("Memory")){
                rentalRepo = new RentalRepository();
                carRepo = new CarRepository();
            }
            if(repoType.equals("Binary")){
                rentalRepo = new BinaryFileRentalRepository(rentalFilename);
                carRepo = new BinaryFileCarRepository(carFilename);
            }
            if(repoType.equals("DataBase")){
                rentalRepo = new DataBaseRentalRepository(rentalFilename);
                carRepo = new DataBaseCarRepository(carFilename);
            }
            if(repoType.equals("JSON")){
                //rentalRepo = new JsonFileRentalRepository(rentalFilename);
                //carRepo = new JsonFileCarRepository(carFilename);
            }
            if(repoType.equals("XML")){
                rentalRepo = new XmlFileRentalRepository(rentalFilename);
                carRepo = new XmlFileCarRepository(carFilename);
            }

            Service service = new Service(rentalRepo,carRepo, carValidator, rentalValidator);
            if(repoType.equals("Memory")){
                service.addCar(1,"Audi","A4",2010,100);
                service.addCar(2,"BMW","X5",2015,200);

                service.addRental(1,"Audi","A4",2010,100,1,"John","1234567890",5);
                service.addRental(2,"BMW","X5",2015,200,2,"Alice","0987654321",3);
                service.addRental(1,"Audi","A4",2010,100,3,"Bob","1234567890",5);
                service.addRental(2,"BMW","X5",2015,200,4,"Bob","1234567890",3);
            }
            //Here will be the controller
            //UI ui = new UI(service);
            //ui.run();

            Controller controller = new Controller(service);
            loader.setController(controller);
            Scene scene = new Scene(loader.load());
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
