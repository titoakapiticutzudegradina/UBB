package Domain;

public class Rental implements Identifiable<Integer> {

    //Properties
    private Integer rentalId;
    private Car car;
    private String nameForRental;
    private String phoneNumber;
    private int daysForRental;

    //Constructor
    public Rental(Integer rentalId,Car car, String nameForRental, String phoneNumber, int daysForRental){
        this.rentalId = rentalId;
        this.car = car;
        this.nameForRental = nameForRental;
        this.phoneNumber = phoneNumber;
        this.daysForRental = daysForRental;
    }

    //Getters and Setters
    @Override
    public Integer getId(){
        return rentalId;
    }
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

    @Override
    public void setId(Integer rentalId){
        this.rentalId = rentalId;
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
        return("Rental: " + rentalId + " Name: " + nameForRental + ", Phone number: " + phoneNumber + ", Days: " + daysForRental + "\n" + car.toString() +"\n\n");
    }
}
