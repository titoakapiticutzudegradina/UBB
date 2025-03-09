package Service;

import Domain.Car;
import Domain.Rental;
import Filter.FilterByCar;
import Filter.FilterByName;
import Repository.Repository;
import Repository.FilterRepository;
import Validator.CarValidator;
import Validator.RentalValidator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Service {
    //Initializes the repository
    private Repository<Integer, Rental> rentalRepo;
    private Repository<Integer, Car> carRepo;
    private final CarValidator carValidator;
    private final RentalValidator rentalValidator;


    //Constructor
    public Service(Repository<Integer, Rental> rentalRepo,Repository<Integer,Car> carRepo, CarValidator carValidator, RentalValidator rentalValidator) {
        this.rentalRepo = rentalRepo;
        this.carRepo = carRepo;
        this.carValidator = carValidator;
        this.rentalValidator = rentalValidator;
    }

    //Add a rental
    public void addRental(int carId,String carBrand, String carModel, int yearProduction, double pricePerDay, int rentalId, String name, String phoneNumber, int days) {
        Car car = new Car(carId,carBrand, carModel, yearProduction, pricePerDay);
        carValidator.validateCar(car);
        carRepo.add(carId, car);

        Rental rental = new Rental(rentalId, car, name, phoneNumber, days);
        rentalValidator.validateRental(rental);
        rentalRepo.add(rentalId, rental);
    }
    //Add a car
    public void addCar(int carId,String carBrand, String carModel, int yearProduction, double pricePerDay) {
        Car car = new Car(carId,carBrand, carModel, yearProduction, pricePerDay);
        carValidator.validateCar(car);
        carRepo.add(carId, car);
    }

    //Remove rental
    public void removeRental(int rentalId) {
        //exception handling for the rental id
        if(rentalId < 0)
            throw new RuntimeException("Rental id must be a positive number!");

        rentalRepo.remove(rentalId);
    }
    //Remove car
    public void removeCar(int carId) {
        //exception handling for the car id
        if(carId < 0)
            throw new RuntimeException("Car id must be a positive number!");

        carRepo.remove(carId);
    }

    //Update rental
    public void updateRental(int rentalId, int newCarId,String newCarBrand, String newCarModel, int newYearProduction, double newPricePerDay, String newName, String newPhoneNumber, int newDays) {
        //exception handling for the rentalId
        if(rentalId < 0)
            throw new RuntimeException("Rental id must be a positive number!");

        Car newCar = new Car(newCarId,newCarBrand, newCarModel, newYearProduction, newPricePerDay);
        carValidator.validateCar(newCar);

        Rental newRental = new Rental(rentalId, newCar, newName, newPhoneNumber, newDays);
        rentalValidator.validateRental(newRental);

        rentalRepo.update(rentalId, newRental);
    }
    //Update car
    public void updateCar(int carId, String newCarBrand, String newCarModel, int newYearProduction, double newPricePerDay) {
        //exception handling for the carId
        if(carId < 0)
            throw new RuntimeException("Car id must be a positive number!");

        Car newCar = new Car(carId,newCarBrand, newCarModel, newYearProduction, newPricePerDay);
        carValidator.validateCar(newCar);

        carRepo.update(carId, newCar);
    }

    //Get all rentals
    public Iterable<Rental> getAllRentals() {
        return rentalRepo.getAll();
    }
    //Get all cars
    public Iterable<Car> getAllCars() {
        return carRepo.getAll();
    }

    //Find rental by the id
    public Rental findRentalById(int rentalId) {
        //exception handling for the rental id
        if(rentalId < 0)
            throw new RuntimeException("Rental id must be a positive number!");

        return rentalRepo.findById(rentalId);
    }
    //Find car by the id
    public Car findCarById(int carId) {
        //exception handling for the car id
        if(carId < 0)
            throw new RuntimeException("Car id must be a positive number!");

        return carRepo.findById(carId);
    }

    //Filter rentals by name
    public Iterable<Rental> filterByName(String name) {
        //exception handling for the name input
        if(name == null || name.isEmpty())
            throw new RuntimeException("Name cannot be empty!");

        FilterRepository filterRepo = new FilterRepository(new FilterByName(name),rentalRepo.getAll());
        return filterRepo.filterAndSort(new FilterByName(name),rentalRepo.getAll());
    }

    //Filter rentals by cars
    public Iterable<Rental> filterByCar(int carId,String carBrand, String carModel, int yearProduction, double pricePerDay) {
        Car car = new Car(carId,carBrand, carModel, yearProduction, pricePerDay);
        carValidator.validateCar(car);
        FilterRepository filterRepo = new FilterRepository(new FilterByCar(car),rentalRepo.getAll());
        return filterRepo.filterAndSort(new FilterByCar(car),rentalRepo.getAll());
    }

    //REPORTS
    //NEED TO DO WITH STREAM
    //Get all the cars rented by a person
    public Iterable<Car> allCarsRentedByPerson(String name){
        ArrayList<Rental> rentals = new ArrayList<>();
        for(Rental rental : getAllRentals()){
            rentals.add(rental);
        }
        return rentals.stream()
                .filter(rental -> rental.getNameForRental().equals(name))
                .map(Rental::getCar).toList();

        //Without stream
        //return (List<Rental>) filterByName(name);
    }

    //Get all the clients that rented a car
    public Iterable<String> allClientsThatRentedACar(int carId){
        ArrayList<Rental> rentals = new ArrayList<>();
        for(Rental rental : getAllRentals()){
            rentals.add(rental);
        }
        if (findCarById(carId) == null)
            throw new RuntimeException("Car id does not exist!");
        Car car = findCarById(carId);
        return rentals.stream()
                .filter(rental -> rental.getCar().equals(car))
                .map(Rental::getNameForRental).toList();

        //return (List<Rental>) filterByCar(carId,carBrand, carModel, yearProduction, pricePerDay);
    }

    //Get the phone number of a person by the rental id
    public String getPhoneNumberByRentalId(int rentalId){
        ArrayList<Rental> rentals = new ArrayList<>();
        for(Rental rental : getAllRentals()){
            rentals.add(rental);
        }
        return rentals.stream()
                .filter(rental -> rental.getId() == rentalId)
                .map(Rental::getPhoneNumber)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Rental id does not exist!"));



        //Without stream
        //Rental rental = findRentalById(rentalId);
        //return rental.getPhoneNumber();
    }

    //Get the total number of days of a rented car
    public int getTotalDaysOfRentedCar(int carId){
        Car car = findCarById(carId);
        List<Rental> rentals = (List<Rental>) filterByCar(carId,car.getCarBrand(), car.getCarModel(), car.getYearProduction(), car.getPricePerDay());
        return rentals.stream()
                .map(Rental::getDaysForRental)
                .reduce(0, Integer::sum);


        //Without stream
        //int totalDays = 0;
        //for(Rental rental: rentals){
        //    totalDays += rental.getDaysForRental();
        //}
        //return totalDays;
    }

    //Get the total cost that a client has to pay
    public double getTotalCostForClient(String name){
        List<Rental> rentals = (List<Rental>) filterByName(name);
        return rentals.stream()
                .mapToDouble(rental -> rental.getDaysForRental() * rental.getCar().getPricePerDay())
                .sum();





        //Without stream
        //double totalCost = 0;
        //for(Rental rental: rentals){
        //    totalCost += rental.getDaysForRental() * rental.getCar().getPricePerDay();
        //}
        //return totalCost;
    }

}
