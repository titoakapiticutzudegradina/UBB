package Domain;

public class Rental {

    //Properties
    private Car car;
    private String nameForRental;
    private String phoneNumber;
    private int daysForRental;

    //Constructor
    public Rental(Car car, String nameForRental, String phoneNumber, int daysForRental){
        this.car = car;
        this.nameForRental = nameForRental;
        this.phoneNumber = phoneNumber;
        this.daysForRental = daysForRental;
    }

    //Getters and Setters
    public Car getCar(){
        return car;
    }
    public String getNameForRental(){
        return nameForRental;
    }
    public String getPhoneNumber(){
        return phoneNumber;
    }
    public int getDaysForRental(){
        return daysForRental;
    }

    public void setCar(Car car){
        this.car = car;
    }
    public void setNameForRental(String nameForRental){
        this.nameForRental = nameForRental;
    }
    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }
    public void setDaysForRental(int daysForRental){
        this.daysForRental = daysForRental;
    }

    @Override
    public String toString(){
        return("Rental: \n" + car.toString() + "\n" + "Name: " + nameForRental + ", Phone number: " + phoneNumber + ", Days: " + daysForRental + "\n\n");
    }
}
