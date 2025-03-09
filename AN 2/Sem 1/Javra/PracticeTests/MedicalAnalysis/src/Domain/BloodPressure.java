package Domain;

import Domain.Date;

import java.io.Serializable;
import java.util.Objects;

public class BloodPressure implements MedicalAnalysis<Date, String>, Comparable<BloodPressure>, Serializable {
    private Date date;
    private String status;
    private int systolicValue;
    private int diastolicValue;

    public BloodPressure(Date date,String status, int systolicValue, int diastolicValue) {
        this.date = date;
        this.status = status;
        this.systolicValue = systolicValue;
        this.diastolicValue = diastolicValue;
    }

    @Override
    public Date getDate(){return date;}
    @Override
    public void setDate(Date date){this.date = date;}

    @Override
    public String getStatus(){return status;}
    @Override
    public void setStatus(String status){this.status = status;}

    public int getSystolicValue(){return systolicValue;}
    public void setSystolicValue(int systolicValue){this.systolicValue = systolicValue;}

    public int getDiastolicValue(){return diastolicValue;}
    public void setDiastolicValue(int diastolicValue){this.diastolicValue = diastolicValue;}

    @Override
    public String toString(){
        return("Date: " + date + "\n" + "Status: " + status + "\n"+"Systolic value: "+ systolicValue+ " Diastolic value: "+ diastolicValue+ "\n\n");
    }

    @Override
    public boolean equals(Object otherBloodPressure) {
        if (this == otherBloodPressure)
            return true;
        if (otherBloodPressure == null || getClass() != otherBloodPressure.getClass())
            return false;
        BloodPressure bloodPressureToCompare = (BloodPressure) otherBloodPressure;
        return Objects.equals(date, bloodPressureToCompare.getDate()) && Objects.equals(status, bloodPressureToCompare.getStatus()) && Objects.equals(systolicValue, bloodPressureToCompare.getSystolicValue()) && Objects.equals(diastolicValue, bloodPressureToCompare.getDiastolicValue());
    }

    @Override
    public int compareTo(BloodPressure bloodPressureToCompare) {
        if (this.date.compareTo(bloodPressureToCompare.getDate()) < 0)
            return -1;
        else if (this.date.compareTo(bloodPressureToCompare.getDate()) > 0)
            return 1;
        return 0;
    }
}
