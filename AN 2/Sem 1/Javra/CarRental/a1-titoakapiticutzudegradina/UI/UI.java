package UI;

import Service.Service;
import java.util.Scanner;


public class UI {

    //Initialize the service
    private Service service;

    //Options
    public static final int EXIT_OPTION = 0;
    public static final int ADD_RENTAL = 1;
    public static final int REMOVE_RENTAL = 2;
    public static final int UPDATE_RENTAL = 3;
    public static final int SHOW_ALL_RENTALS = 4;

    //Constructor
    public UI(Service service){
        this.service = service;
    }

    //Initial values
    public void initial(){
        service.addRental(1, "Audi", "RS7", 2013, 300, "Mihaiull", "0745812046", 5);
        service.addRental(2, "BMW", "X5", 2015, 150, "Ana", "0746968899", 3);
        service.addRental(3, "Mercedes", "E200", 2018, 200, "Simona", "0770560685", 2);
        service.addRental(4, "Volkswagen", "Golf", 2019, 50, "Cristian", "0747942470", 7);
        service.addRental(5, "Ford", "Focus", 2017, 70, "Razvan", "0745179461", 4);
    }


    //Show menu
    public void showMenu(){
        System.out.println("0.Exit");
        System.out.println("1.Add a rental");
        System.out.println("2.Remove a rental");
        System.out.println("3.Update a rental");
        System.out.println("4.Show all rentals");
    }

    //Run method
    public void run(){
        //scanner for taking input from the keyboard
        Scanner scanner = new Scanner(System.in);

        //Put initial values
        initial();

        while(true){
            showMenu();
            System.out.println("Enter option: ");
            int option = scanner.nextInt();
            if(option == EXIT_OPTION){
                break;
            }
            switch(option){
                case ADD_RENTAL:
                    System.out.println("Car id: ");
                    int carId = scanner.nextInt();
                    System.out.println("Car brand: ");
                    String carBrand = scanner.next();
                    System.out.println("Car model: ");
                    String carModel = scanner.next();
                    System.out.println("Year production: ");
                    int yearProduction = scanner.nextInt();
                    System.out.println("Price per day: ");
                    double pricePerDay = scanner.nextDouble();
                    System.out.println("Name for the reservation: ");
                    String name = scanner.next();
                    System.out.println("Phone number: ");
                    String phoneNumber = scanner.next();
                    System.out.println("How many days will the reservation be for: ");
                    int days = scanner.nextInt();
                    service.addRental(carId, carBrand, carModel, yearProduction, pricePerDay, name, phoneNumber, days);
                    break;

                case REMOVE_RENTAL:
                    System.out.println("Car id: ");
                    int carIdRemove = scanner.nextInt();
                    System.out.println("Name of the reservation: ");
                    String nameRemove = scanner.next();
                    service.removeRental(carIdRemove, nameRemove);
                    break;

                case UPDATE_RENTAL:
                    System.out.println("Car id: ");
                    int carIdUpdate = scanner.nextInt();
                    System.out.println("Name of the reservation: ");
                    String nameUpdate = scanner.next();
                    System.out.println("New car id: ");
                    int newCarId = scanner.nextInt();
                    System.out.println("New car brand: ");
                    String newCarBrand = scanner.next();
                    System.out.println("New car model: ");
                    String newCarModel = scanner.next();
                    System.out.println("New year production: ");
                    int newYearProduction = scanner.nextInt();
                    System.out.println("New price per day: ");
                    double newPricePerDay = scanner.nextDouble();
                    System.out.println("New name for the reservation: ");
                    String newName = scanner.next();
                    System.out.println("New phone number: ");
                    String newPhoneNumber = scanner.next();
                    System.out.println("How many days will the reservation be for: ");
                    int newDays = scanner.nextInt();
                    service.updateRental(carIdUpdate, nameUpdate, newCarId, newCarBrand, newCarModel, newYearProduction, newPricePerDay, newName, newPhoneNumber, newDays);
                    break;

                case SHOW_ALL_RENTALS:
                    service.getAllRentals();
                    for(int i = 0; i < service.getAllRentals().size(); i++){
                        System.out.println(service.getAllRentals().get(i).toString());
                    }
                    break;
            }
        }
    }
}
