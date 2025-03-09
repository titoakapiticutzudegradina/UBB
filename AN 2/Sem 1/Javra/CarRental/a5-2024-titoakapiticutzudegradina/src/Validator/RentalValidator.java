package Validator;

import Domain.Rental;

public class RentalValidator {

    public void validateRental(Rental rentalToValidate){
        if(rentalToValidate.getId() < 0){
            throw new IllegalArgumentException("The id of a rental must be a positive number.");
        }
        if(rentalToValidate.getNameForRental() == null || rentalToValidate.getNameForRental().isEmpty()){
            throw new IllegalArgumentException("The name of the person renting cannot be empty.");
        }
        if(rentalToValidate.getPhoneNumber() == null || rentalToValidate.getPhoneNumber().isEmpty()){
            throw new IllegalArgumentException("The phone number cannot be empty.");
        }
        if(rentalToValidate.getDaysForRental() < 0){
            throw new IllegalArgumentException("The number of days must be a positive number.");
        }
    }
}
