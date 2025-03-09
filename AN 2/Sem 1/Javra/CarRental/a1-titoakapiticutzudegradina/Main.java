import Service.Service;
import Repository.Repository;
import UI.UI;

public class Main {

    //Main method
    public static void main(String[] args){
        Repository repo = new Repository();
        Service service = new Service(repo);
        UI ui = new UI(service);
        ui.run();
    }
}
