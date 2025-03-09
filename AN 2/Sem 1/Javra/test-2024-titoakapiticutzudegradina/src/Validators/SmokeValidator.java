package Validators;

import Domain.Smoke;

public class SmokeValidator {
    public void validateSmoke(Smoke smoke){
        if(smoke.getLength() < 0){
            throw new IllegalArgumentException("Invalid!");
        }
    }
}
