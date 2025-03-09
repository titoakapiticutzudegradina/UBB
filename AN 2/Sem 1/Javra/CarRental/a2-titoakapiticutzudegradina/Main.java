import Domain.Rental;
import Service.Service;
import Repository.Repository;
import Repository.TextFileRentalRepository;
import UI.UI;

import java.io.IOException;

public class Main {

    //Main method
    public static void main(String[] args){
        //Repository<Integer,Rental> repo = new RentalRepository();
        //Service service = new Service(repo);
        //UI ui = new UI(service);
        //ui.run();

        //try{
            Repository<Integer,Rental> repo = new TextFileRentalRepository("data/rentals.txt");
            Service service = new Service(repo);
            UI ui = new UI(service);
            ui.run();
        //} catch (IOException e){
        //    throw new RuntimeException(e);
        //}
    }
}
