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
    public static final int ADD_CAR = 2;
    public static final int REMOVE_RENTAL = 3;
    public static final int REMOVE_CAR = 4;
    public static final int UPDATE_RENTAL = 5;
    public static final int UPDATE_CAR = 6;
    public static final int SHOW_ALL_RENTALS = 7;
    public static final int SHOW_ALL_CARS = 8;
    public static final int FIND_RENTAL_BY_ID = 9;
    public static final int FIND_CAR_BY_ID = 10;
    public static final int FILTER_BY_NAME = 11;
    public static final int FILTER_BY_CAR = 12;
    public static final int GET_ALL_CARS_RENTED_BY_A_PERSON = 13;
    public static final int GET_ALL_CLIENTS_THAT_RENTED_THE_SAME_CAR = 14;
    public static final int GET_PHONE_NUMBER_OF_A_PERSON_BY_THE_RENTAL_ID = 15;
    public static final int GET_TOTAL_NUMBER_OF_DAYS_A_CAR_WAS_RENTED = 16;
    public static final int GET_TOTAL_COST_THAT_A_CLIENT_HAS_TO_PAY = 17;

    //Constructor
    public UI(Service service){
        this.service = service;
    }

    //Initial values
    public void initial(){
        service.addRental( 1,"Audi", "RS7", 2013, 300,1, "Mihaiull", "0745812046", 5);
        service.addRental( 2,"BMW", "X5", 2015, 150, 2,"Ana", "0746968899", 3);
        service.addRental( 3,"Mercedes", "E200", 2018, 200,3, "Simona", "0770560685", 2);
        service.addRental( 4,"Volkswagen", "Golf", 2019, 50,4, "Cristian", "0747942470", 7);
        service.addRental( 5,"Ford", "Focus", 2017, 70,5, "Razvan", "0745179461", 4);
        service.addRental( 6,"BMW", "X5", 2015, 150, 6,"Mihaiull", "0746968899", 3);
    }


    //Show menu
    public void showMenu(){
        System.out.println(EXIT_OPTION + ". Exit");
        System.out.println(ADD_RENTAL + ". Add a rental");
        System.out.println(ADD_CAR + ". Add a car");
        System.out.println(REMOVE_RENTAL + ". Remove a rental");
        System.out.println(REMOVE_CAR + ". Remove a car");
        System.out.println(UPDATE_RENTAL + ". Update a rental");
        System.out.println(UPDATE_CAR + ". Update a car");
        System.out.println(SHOW_ALL_RENTALS + ". Show all rentals");
        System.out.println(SHOW_ALL_CARS + ". Show all cars");
        System.out.println(FIND_RENTAL_BY_ID + ". Find a rental by id");
        System.out.println(FIND_CAR_BY_ID + ". Find a car by id");
        System.out.println(FILTER_BY_NAME + ". Filter by name");
        System.out.println(FILTER_BY_CAR + ". Filter by car");
        System.out.println(GET_ALL_CARS_RENTED_BY_A_PERSON + ". Get all cars rented by a person");
        System.out.println(GET_ALL_CLIENTS_THAT_RENTED_THE_SAME_CAR + ". Get all clients that rented the same car");
        System.out.println(GET_PHONE_NUMBER_OF_A_PERSON_BY_THE_RENTAL_ID + ". Get the phone number of a person by the rental id");
        System.out.println(GET_TOTAL_NUMBER_OF_DAYS_A_CAR_WAS_RENTED + ". Get the total number of days a car was rented");
        System.out.println(GET_TOTAL_COST_THAT_A_CLIENT_HAS_TO_PAY + ". Get the total cost that a client has to pay");
    }

    //Run method
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
                if (option == EXIT_OPTION) {
                    break;
                }
                switch (option) {
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
                        System.out.println("Rental id: ");
                        int rentalId = scanner.nextInt();
                        System.out.println("Name for the reservation: ");
                        String name = scanner.next();
                        System.out.println("Phone number: ");
                        String phoneNumber = scanner.next();
                        System.out.println("How many days will the reservation be for: ");
                        int days = scanner.nextInt();
                        service.addRental(carId,carBrand, carModel, yearProduction, pricePerDay, rentalId, name, phoneNumber, days);
                        break;

                    case ADD_CAR:
                        System.out.println("Car id: ");
                        int carIdAdd = scanner.nextInt();
                        System.out.println("Car brand: ");
                        String carBrandAdd = scanner.next();
                        System.out.println("Car model: ");
                        String carModelAdd = scanner.next();
                        System.out.println("Year production: ");
                        int yearProductionAdd = scanner.nextInt();
                        System.out.println("Price per day: ");
                        double pricePerDayAdd = scanner.nextDouble();
                        service.addCar(carIdAdd, carBrandAdd, carModelAdd, yearProductionAdd, pricePerDayAdd);
                        break;

                    case REMOVE_RENTAL:
                        System.out.println("Rental id: ");
                        int rentalIdRemove = scanner.nextInt();
                        service.removeRental(rentalIdRemove);
                        break;

                    case REMOVE_CAR:
                        System.out.println("Car id: ");
                        int carIdRemove = scanner.nextInt();
                        service.removeCar(carIdRemove);
                        break;

                    case UPDATE_RENTAL:
                        System.out.println("Rental id: ");
                        int rentalIdUpdate = scanner.nextInt();
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
                        service.updateRental(rentalIdUpdate, newCarId,newCarBrand, newCarModel, newYearProduction, newPricePerDay, newName, newPhoneNumber, newDays);
                        break;

                    case UPDATE_CAR:
                        System.out.println("Car id: ");
                        int carIdUpdate = scanner.nextInt();
                        System.out.println("New car brand: ");
                        String newCarBrandUpdate = scanner.next();
                        System.out.println("New car model: ");
                        String newCarModelUpdate = scanner.next();
                        System.out.println("New year production: ");
                        int newYearProductionUpdate = scanner.nextInt();
                        System.out.println("New price per day: ");
                        double newPricePerDayUpdate = scanner.nextDouble();
                        service.updateCar(carIdUpdate, newCarBrandUpdate, newCarModelUpdate, newYearProductionUpdate, newPricePerDayUpdate);
                        break;

                    case SHOW_ALL_RENTALS:
                        for (Rental rental : service.getAllRentals())
                            System.out.println(rental);
                        break;

                    case SHOW_ALL_CARS:
                        for (Car car : service.getAllCars())
                            System.out.println(car);
                        break;

                    case FIND_RENTAL_BY_ID:
                        System.out.println("Rental id: ");
                        int rentalIdFind = scanner.nextInt();
                        System.out.println(service.findRentalById(rentalIdFind));
                        break;

                    case FIND_CAR_BY_ID:
                        System.out.println("Car id: ");
                        int carIdFind = scanner.nextInt();
                        System.out.println(service.findCarById(carIdFind));
                        break;

                    case FILTER_BY_NAME:
                        System.out.println("Name for the reservation: ");
                        String nameFilter = scanner.next();
                        for (Rental rental : service.filterByName(nameFilter))
                            System.out.println(rental);
                        break;

                    case FILTER_BY_CAR:
                        System.out.println("Car id: ");
                        int carIdFilter = scanner.nextInt();
                        System.out.println("Car brand: ");
                        String carBrandFilter = scanner.next();
                        System.out.println("Car model: ");
                        String carModelFilter = scanner.next();
                        System.out.println("Year production: ");
                        int yearProductionFilter = scanner.nextInt();
                        System.out.println("Price per day: ");
                        double pricePerDayFilter = scanner.nextDouble();
                        Car car = new Car(carIdFilter,carBrandFilter, carModelFilter, yearProductionFilter, pricePerDayFilter);
                        for (Rental rental : service.filterByCar(carIdFilter,carBrandFilter, carModelFilter, yearProductionFilter, pricePerDayFilter))
                            System.out.println(rental);
                        break;



                    //REPORTS
                    case GET_ALL_CARS_RENTED_BY_A_PERSON:
                        System.out.println("Name for the reservation: ");
                        String nameFilterCars = scanner.next();
                        for (Car carRented : service.allCarsRentedByPerson(nameFilterCars))
                            System.out.println(carRented);
                        break;

                    case GET_ALL_CLIENTS_THAT_RENTED_THE_SAME_CAR:
                        System.out.println("Car id: ");
                        int carIdFilterClients = scanner.nextInt();
                        for (String nameOfClient : service.allClientsThatRentedACar(carIdFilterClients))
                            System.out.println(nameOfClient);
                        break;

                    case GET_PHONE_NUMBER_OF_A_PERSON_BY_THE_RENTAL_ID:
                        System.out.println("Rental id: ");
                        int rentalIdPhoneNumber = scanner.nextInt();
                        System.out.println(service.getPhoneNumberByRentalId(rentalIdPhoneNumber));
                        break;

                    case GET_TOTAL_NUMBER_OF_DAYS_A_CAR_WAS_RENTED:
                        System.out.println("Car id: ");
                        int carIdTotalDays = scanner.nextInt();
                        System.out.println(service.getTotalDaysOfRentedCar(carIdTotalDays));
                        break;

                    case GET_TOTAL_COST_THAT_A_CLIENT_HAS_TO_PAY:
                        System.out.println("Name for the reservation: ");
                        String nameTotalCost = scanner.next();
                        System.out.println(service.getTotalCostForClient(nameTotalCost));
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
