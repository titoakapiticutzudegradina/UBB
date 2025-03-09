package Repository;

import Domain.Rental;

import java.util.ArrayList;

public class Repository {

    //List of rentals
    private ArrayList<Rental> rentals = new ArrayList<>();

    //Getters
    public ArrayList<Rental> getRentals(){
        return rentals;
    }

    //Add rental to the list
    public void addRental(Rental rental){
        rentals.add(rental);
    }

    //Remove rental from the list
    public void removeRental(Rental rental){
        rentals.remove(rental);
    }

    //Update rental
    public void updateRental(Rental rental, Rental newRental){
        rentals.set(rentals.indexOf(rental), newRental);
    }

    //Get all rentals
    public ArrayList<Rental> getAllRentals(){
        return rentals;
    }

}
