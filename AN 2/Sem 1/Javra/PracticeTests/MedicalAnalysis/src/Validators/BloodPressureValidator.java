package Validators;

import Domain.BloodPressure;

public class BloodPressureValidator {
    public void validateBloodPressure(BloodPressure BPToValidate){
        if(BPToValidate.getStatus() == null || BPToValidate.getStatus().isEmpty()  )
            throw new IllegalArgumentException("The status is invalid!");
        if (BPToValidate.getSystolicValue() < 0)
            throw new IllegalArgumentException("The systolic value is invalid!");
        if (BPToValidate.getDiastolicValue() < 0)
            throw new IllegalArgumentException("The diastolic value is invalid!");
    }
}
