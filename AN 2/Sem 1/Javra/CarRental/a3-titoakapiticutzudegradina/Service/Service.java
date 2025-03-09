package Service;

import Domain.Car;
import Domain.Rental;
import Filter.FilterByCar;
import Filter.FilterByName;
import Repository.Repository;
import Repository.FilterRepository;
import Validator.CarValidator;
import Validator.RentalValidator;


public class Service {
    //Initializes the repository
    private Repository<Integer, Rental> repo;
    private final CarValidator carValidator;
    private final RentalValidator rentalValidator;


    //Constructor
    public Service(Repository<Integer, Rental> repo, CarValidator carValidator, RentalValidator rentalValidator) {
        this.repo = repo;
        this.carValidator = carValidator;
        this.rentalValidator = rentalValidator;
    }

    //Add a rental
    public void addRental(String carBrand, String carModel, int yearProduction, double pricePerDay, int rentalId, String name, String phoneNumber, int days) {
        Car car = new Car(carBrand, carModel, yearProduction, pricePerDay);
        carValidator.validateCar(car);

        Rental rental = new Rental(rentalId, car, name, phoneNumber, days);
        rentalValidator.validateRental(rental);
        repo.add(rentalId, rental);
    }

    //Remove rental
    public void removeRental(int rentalId) {
        //exception handling for the rental id
        if(rentalId < 0)
            throw new RuntimeException("Rental id must be a positive number!");

        repo.remove(rentalId);
    }

    //Update rental
    public void updateRental(int rentalId, String newCarBrand, String newCarModel, int newYearProduction, double newPricePerDay, String newName, String newPhoneNumber, int newDays) {
        //exception handling for the rentalId
        if(rentalId < 0)
            throw new RuntimeException("Rental id must be a positive number!");

        Car newCar = new Car(newCarBrand, newCarModel, newYearProduction, newPricePerDay);
        carValidator.validateCar(newCar);

        Rental newRental = new Rental(rentalId, newCar, newName, newPhoneNumber, newDays);
        rentalValidator.validateRental(newRental);

        repo.update(rentalId, newRental);
    }

    //Get all rentals
    public Iterable<Rental> getAllRentals() {
        return repo.getAll();
    }

    //Find rental by the id
    public Rental findRentalById(int rentalId) {
        //exception handling for the rental id
        if(rentalId < 0)
            throw new RuntimeException("Rental id must be a positive number!");

        return repo.findById(rentalId);
    }

    //Filter rentals by name
    public Iterable<Rental> filterByName(String name) {
        //exception handling for the name input
        if(name == null || name.isEmpty())
            throw new RuntimeException("Name cannot be empty!");

        FilterRepository filterRepo = new FilterRepository(new FilterByName(name),repo.getAll());
        return filterRepo.filterAndSort(new FilterByName(name),repo.getAll());
    }

    //Filter rentals by cars
    public Iterable<Rental> filterByCar(String carBrand, String carModel, int yearProduction, double pricePerDay) {
        Car car = new Car(carBrand, carModel, yearProduction, pricePerDay);
        carValidator.validateCar(car);
        FilterRepository filterRepo = new FilterRepository(new FilterByCar(car),repo.getAll());
        return filterRepo.filterAndSort(new FilterByCar(car),repo.getAll());
    }
}
