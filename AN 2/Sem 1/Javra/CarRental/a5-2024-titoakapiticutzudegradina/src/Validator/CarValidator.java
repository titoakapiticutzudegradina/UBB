package Validator;

import Domain.Car;

public class CarValidator {

    public void validateCar(Car carToValidate){
        if(carToValidate.getCarBrand() == null || carToValidate.getCarBrand().isEmpty()){
            throw new IllegalArgumentException("The car brand cannot be empty.");
        }
        if(carToValidate.getCarModel() == null || carToValidate.getCarModel().isEmpty()){
            throw new IllegalArgumentException("The car model cannot be empty.");
        }
        if(carToValidate.getYearProduction() < 1886 || carToValidate.getYearProduction() > 2024){
            throw new IllegalArgumentException("The year of production must be between 1886 and 2024.");
        }
        if(carToValidate.getPricePerDay() == 0){
            throw new IllegalArgumentException("The price per day must be a positive number.");
        }
    }
}
