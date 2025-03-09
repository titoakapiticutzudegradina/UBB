package Validators;

import Domain.Date;

public class DateValidator {

    public void validateDate(Date dateToValidate){
        if(dateToValidate.getDay() < 1 || dateToValidate.getDay() > 31 ){
            throw new IllegalArgumentException("The day is invalid!");
        }
        if(dateToValidate.getMonth() <1 || dateToValidate.getMonth() > 12){
            throw new IllegalArgumentException("The month is invalid!");
        }
        if(dateToValidate.getYear() < 1800 || dateToValidate.getYear() > 2100){
            throw new IllegalArgumentException("The year is invalid!");
        }

    }

}
