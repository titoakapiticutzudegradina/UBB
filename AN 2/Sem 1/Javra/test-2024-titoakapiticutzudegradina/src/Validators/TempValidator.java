package Validators;

import Domain.Temperature;

public class TempValidator {
    public void validateTemp(Temperature temp){
        if(temp.getDiameter() < 0)
            throw new IllegalArgumentException("Invlid!");
    }
}
