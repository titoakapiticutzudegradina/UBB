package UI;

import Service.Service;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UI {
    //Initialize the service
    private Service service;

    //Options
    public static final int EXIT = 0;
    public static final int ADD_TEMP = 1;
    public static final int ADD_SMOKE = 2;

    //Constructor
    public UI(Service service){
        this.service = service;
    }

    //Show menu
    public void showMenu(){
        System.out.println("0.Exit");
        System.out.println("1.Add temp ");
        System.out.println("2.Add smoke ");
    }

    public void run(){
        //scanner for taking input from the keyboard
        Scanner scanner = new Scanner(System.in);

        //Put initial values
        //initial();
        while (true) {
            try {
                showMenu();
                System.out.println("Enter option: ");
                int option = scanner.nextInt();
                if (option == EXIT) {
                    break;
                }
                switch (option) {
                    case ADD_TEMP:
                        System.out.println("Last recording:");
                        double lastR = scanner.nextDouble();
                        System.out.println("Diameter:");
                        int diameter = scanner.nextInt();
                        service.addTemp(lastR,diameter);
                        break;

                    case ADD_SMOKE:
                        System.out.println("Last recording:");
                        double last= scanner.nextDouble();
                        System.out.println("Diameter:");
                        int length = scanner.nextInt();
                        service.addTemp(last,length);
                        break;



                }
            } catch (InputMismatchException exception){
                System.out.println("Wrong type!");
                scanner.next();
            } catch (RuntimeException exception){
                System.out.println(exception.getMessage());
            }
        }
    }
}
