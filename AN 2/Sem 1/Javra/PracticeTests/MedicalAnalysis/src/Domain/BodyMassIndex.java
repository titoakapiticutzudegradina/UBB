package Domain;

import Domain.Date;

import java.io.Serializable;
import java.util.Objects;

public class BodyMassIndex implements MedicalAnalysis<Date, String>, Comparable<BodyMassIndex>, Serializable {
    private Date date;
    private String status;
    private float bodyMassIndex;

    public BodyMassIndex(Date date,String status, float bodyMassIndex) {
        this.date = date;
        this.status = status;
        this.bodyMassIndex = bodyMassIndex;
    }

    @Override
    public Date getDate(){return date;}
    @Override
    public void setDate(Date date){this.date = date;}

    @Override
    public String getStatus(){return status;}
    @Override
    public void setStatus(String status){this.status = status;}

    public float getBodyMassIndex(){return bodyMassIndex;}
    public void setBodyMassIndex(float bodyMassIndex){this.bodyMassIndex = bodyMassIndex;}

    @Override
    public String toString(){
        return("Date: " + date + "\n" + "Status: " + status + " \n" + "Body Mass Index: " + bodyMassIndex+ "\n\n");
    }

    @Override
    public boolean equals(Object otherBodyMassIndex){
        if(this == otherBodyMassIndex)
            return true;
        if(otherBodyMassIndex == null || getClass() != otherBodyMassIndex.getClass())
            return false;
        BodyMassIndex bodyMassIndexToCompare = (BodyMassIndex) otherBodyMassIndex;
        return Objects.equals(date, bodyMassIndexToCompare.getDate()) && Objects.equals(status, bodyMassIndexToCompare.getStatus()) && Objects.equals(bodyMassIndex, bodyMassIndexToCompare.getBodyMassIndex());
    }

    @Override
    public int compareTo(BodyMassIndex bodyMassIndexToCompare) {
        if (this.date.compareTo(bodyMassIndexToCompare.getDate()) < 0)
            return -1;
        else if (this.date.compareTo(bodyMassIndexToCompare.getDate()) > 0)
            return 1;
        return 0;
    }

}
