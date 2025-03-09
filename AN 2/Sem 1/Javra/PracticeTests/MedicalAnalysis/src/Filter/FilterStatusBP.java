package Filter;

import Domain.BloodPressure;
import Domain.BodyMassIndex;

public class FilterStatusBP implements Filter<BloodPressure> {
    private String status;

    public FilterStatusBP(String status) {
        this.status = status;
    }

    @Override
    public boolean accept(BloodPressure element){
        return element.getStatus().equals(status);
    }
}
