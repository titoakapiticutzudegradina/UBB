package Domain;

public class Car {

    //Properties
    private int carId;
    private String carBrand;
    private String carModel;
    private int yearProduction;
    private double pricePerDay;

    //Constructor
    public Car(int carId ,String carBrand, String carModel, int yearProduction, double pricePerDay){
        this.carId = carId;
        this.carBrand = carBrand;
        this.carModel = carModel;
        this.yearProduction = yearProduction;
        this.pricePerDay = pricePerDay;
    }

    //Getters and Setters
    public int getCarId(){
        return carId;
    }
    public String getCarBrand(){
        return carBrand;
    }
    public String getCarModel(){
        return carModel;
    }
    public int getYearProduction(){
        return yearProduction;
    }
    public double getPricePerDay(){
        return pricePerDay;
    }

    public void setCarId(int carId){
        this.carId = carId;
    }
    public void setCarBrand(String carBrand){
        this.carBrand = carBrand;
    }
    public void setCarModel(String carModel){
        this.carModel = carModel;
    }
    public void setYearProduction(int yearProduction){
        this.yearProduction = yearProduction;
    }
    public void setPricePerDay(double pricePerDay){
        this.pricePerDay = pricePerDay;
    }

    @Override
    public String toString(){
        return("Car: " + carId + " " + carBrand + " " + carModel + "\nYear of production: " + yearProduction + "\nPrice per day: " + pricePerDay);
    }
}
