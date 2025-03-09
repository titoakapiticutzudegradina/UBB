package Service;

import Domain.Car;
import Domain.Rental;
import Filter.FilterByCar;
import Filter.FilterByName;
import Repository.Repository;
import Repository.FilterRepository;


public class Service {
    //Initializes the repository
    private Repository<Integer, Rental> repo;

    //Constructor
    public Service(Repository<Integer, Rental> repo) {
        this.repo = repo;
    }

    //Add a rental
    public void addRental(String carBrand, String carModel, int yearProduction, double pricePerDay, int rentalId, String name, String phoneNumber, int days) {
        //exception handling for the car input
        if(carBrand == null || carBrand.isEmpty() )
            throw new RuntimeException("Car brand cannot be empty!");
        if(carModel == null || carModel.isEmpty() )
            throw new RuntimeException("Car model cannot be empty!");
        if(yearProduction < 1886 || yearProduction > 2024)
            throw new RuntimeException("Year production must be between 1886 and 2024!");
        if(pricePerDay < 0)
            throw new RuntimeException("Price per day must be a positive number!");

        Car car = new Car(carBrand, carModel, yearProduction, pricePerDay);

        //exception handling for the rental input
        if(rentalId < 0)
            throw new RuntimeException("Rental id must be a positive number!");
        if(name == null || name.isEmpty())
            throw new RuntimeException("Name cannot be empty!");
        if(phoneNumber == null || phoneNumber.isEmpty())
            throw new RuntimeException("Phone number cannot be empty!");
        if(days < 0)
            throw new RuntimeException("Days must be a positive number!");

        Rental rental = new Rental(rentalId, car, name, phoneNumber, days);
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

        //exception handling for the car input
        if(newCarBrand == null || newCarBrand.isEmpty() )
            throw new RuntimeException("Car brand cannot be empty!");
        if(newCarModel == null || newCarModel.isEmpty() )
            throw new RuntimeException("Car model cannot be empty!");
        if(newYearProduction < 1886 || newYearProduction > 2024 )
            throw new RuntimeException("Year production must be between 1886 and 2024!");
        if(newPricePerDay < 0 || newPricePerDay == (double)newPricePerDay)
            throw new RuntimeException("Price per day must be a positive number!");

        Car newCar = new Car(newCarBrand, newCarModel, newYearProduction, newPricePerDay);

        //exception handling for the rental input
        if(newName == null || newName.isEmpty())
            throw new RuntimeException("Name cannot be empty!");
        if(newPhoneNumber == null || newPhoneNumber.isEmpty())
            throw new RuntimeException("Phone number cannot be empty!");
        if(newDays < 0 )
            throw new RuntimeException("Days must be a positive number!");

        Rental newRental = new Rental(rentalId, newCar, newName, newPhoneNumber, newDays);
        repo.update(rentalId, newRental);
    }

    //Get all rentals
    public Iterable<Rental> getAllRentals() {
        return repo.getAll();
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
        //exception handling for the car input
        if(carBrand == null || carBrand.isEmpty() )
            throw new RuntimeException("Car brand cannot be empty!");
        if(carModel == null || carModel.isEmpty() )
            throw new RuntimeException("Car model cannot be empty!");
        if(yearProduction < 1886 || yearProduction > 2024)
            throw new RuntimeException("Year production must be between 1886 and 2024!");
        if(pricePerDay < 0)
            throw new RuntimeException("Price per day must be a positive number!");

        Car car = new Car(carBrand, carModel, yearProduction, pricePerDay);
        FilterRepository filterRepo = new FilterRepository(new FilterByCar(car),repo.getAll());
        return filterRepo.filterAndSort(new FilterByCar(car),repo.getAll());
    }
}
