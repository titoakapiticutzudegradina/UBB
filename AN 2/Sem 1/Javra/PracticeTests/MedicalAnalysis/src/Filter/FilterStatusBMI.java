package Filter;

import Domain.BodyMassIndex;

public class FilterStatusBMI implements Filter<BodyMassIndex>{
    private String status;

    public FilterStatusBMI(String status) {
        this.status = status;
    }

    @Override
    public boolean accept(BodyMassIndex element){
        return element.getStatus().equals(status);
    }
}
