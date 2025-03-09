package Validators;

import Domain.BodyMassIndex;

public class BodyMassIndexValidator {
    public void validateBodyMassIndex(BodyMassIndex BMIToValidate){
        if(BMIToValidate.getStatus() == null || BMIToValidate.getStatus().isEmpty() )
            throw new IllegalArgumentException("The status is invalid!");
        if(BMIToValidate.getBodyMassIndex() <0)
            throw new IllegalArgumentException("The body mass index is invalid!");
    }
}
