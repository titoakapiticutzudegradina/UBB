package UI;

import Domain.Car;
import Domain.Rental;
import Service.Service;

import java.util.InputMismatchException;
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
    public static final int FILTER_BY_NAME = 5;
    public static final int FILTER_BY_CAR = 6;

    //Constructor
    public UI(Service service){
        this.service = service;
    }

    //Initial values
    public void initial(){
        service.addRental( "Audi", "RS7", 2013, 300,1, "Mihaiull", "0745812046", 5);
        service.addRental( "BMW", "X5", 2015, 150, 2,"Ana", "0746968899", 3);
        service.addRental( "Mercedes", "E200", 2018, 200,3, "Simona", "0770560685", 2);
        service.addRental( "Volkswagen", "Golf", 2019, 50,4, "Cristian", "0747942470", 7);
        service.addRental( "Ford", "Focus", 2017, 70,5, "Razvan", "0745179461", 4);
        service.addRental( "BMW", "X5", 2015, 150, 6,"Mihaiull", "0746968899", 3);
    }


    //Show menu
    public void showMenu(){
        System.out.println("0.Exit");
        System.out.println("1.Add a rental");
        System.out.println("2.Remove a rental");
        System.out.println("3.Update a rental");
        System.out.println("4.Show all rentals");
        System.out.println("5.Filter by name");
        System.out.println("6.Filter by car");
    }

    //Run method
    public void run(){
        //scanner for taking input from the keyboard
        Scanner scanner = new Scanner(System.in);

        //Put initial values
        initial();
        while (true) {
            try {
                showMenu();
                System.out.println("Enter option: ");
                int option = scanner.nextInt();
                if (option == EXIT_OPTION) {
                    break;
                }
                switch (option) {
                    case ADD_RENTAL:
                        System.out.println("Car brand: ");
                        String carBrand = scanner.next();
                        System.out.println("Car model: ");
                        String carModel = scanner.next();
                        System.out.println("Year production: ");
                        int yearProduction = scanner.nextInt();
                        System.out.println("Price per day: ");
                        double pricePerDay = scanner.nextDouble();
                        System.out.println("Rental id: ");
                        int rentalId = scanner.nextInt();
                        System.out.println("Name for the reservation: ");
                        String name = scanner.next();
                        System.out.println("Phone number: ");
                        String phoneNumber = scanner.next();
                        System.out.println("How many days will the reservation be for: ");
                        int days = scanner.nextInt();
                        service.addRental(carBrand, carModel, yearProduction, pricePerDay, rentalId, name, phoneNumber, days);
                        break;

                    case REMOVE_RENTAL:
                        System.out.println("Rental id: ");
                        int rentalIdRemove = scanner.nextInt();
                        service.removeRental(rentalIdRemove);
                        break;

                    case UPDATE_RENTAL:
                        System.out.println("Rental id: ");
                        int rentalIdUpdate = scanner.nextInt();
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
                        service.updateRental(rentalIdUpdate, newCarBrand, newCarModel, newYearProduction, newPricePerDay, newName, newPhoneNumber, newDays);
                        break;

                    case SHOW_ALL_RENTALS:
                        for (Rental rental : service.getAllRentals())
                            System.out.println(rental);
                        break;

                    case FILTER_BY_NAME:
                        System.out.println("Name for the reservation: ");
                        String nameFilter = scanner.next();
                        for (Rental rental : service.filterByName(nameFilter))
                            System.out.println(rental);
                        break;

                    case FILTER_BY_CAR:
                        System.out.println("Car brand: ");
                        String carBrandFilter = scanner.next();
                        System.out.println("Car model: ");
                        String carModelFilter = scanner.next();
                        System.out.println("Year production: ");
                        int yearProductionFilter = scanner.nextInt();
                        System.out.println("Price per day: ");
                        double pricePerDayFilter = scanner.nextDouble();
                        Car car = new Car(carBrandFilter, carModelFilter, yearProductionFilter, pricePerDayFilter);
                        for (Rental rental : service.filterByCar(carBrandFilter, carModelFilter, yearProductionFilter, pricePerDayFilter))
                            System.out.println(rental);
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
