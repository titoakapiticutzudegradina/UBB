package Service;

import Domain.Car;
import Domain.Rental;
import Repository.Repository;

import java.util.ArrayList;

public class Service {

    //Initialise the repository
    private Repository repo;

    //Constructor
    public Service(Repository repo){
        this.repo = repo;
    }

    //Add rental
    public void addRental(int carId, String carBrand, String carModel, int yearProduction, double pricePerDay, String name, String phoneNumber, int days){
        Car car = new Car(carId, carBrand, carModel, yearProduction, pricePerDay);
        repo.addRental(new Rental(car, name, phoneNumber, days));
    }

    //Remove rental
    public void removeRental(int carId, String name){
        for (Rental rental: repo.getRentals()){
            if (rental.getCar().getCarId() == carId && rental.getNameForRental().equals(name)){
                repo.removeRental(rental);
                break;
            }
        }
    }

    //Update rental
    public void updateRental(int carId, String oldName, int newCarId, String newCarBrand, String newCarModel, int newYearProduction, double newPricePerDay, String newName, String newPhoneNumber, int newDays){
        Car newCar = new Car(newCarId, newCarBrand, newCarModel, newYearProduction, newPricePerDay);
        Rental newRental = new Rental(newCar, newName, newPhoneNumber, newDays);
        for (Rental rental: repo.getRentals()){
            if (rental.getCar().getCarId() == carId && rental.getNameForRental().equals(oldName)){
                repo.updateRental(rental, newRental);
            }
        }
    }

    //Get the list of all rentals
    public ArrayList<Rental> getAllRentals(){
        return repo.getAllRentals();
    }
}
