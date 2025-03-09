package Domain;

import java.io.Serializable;

public class Car implements Identifiable<Integer>, Serializable {

    //Properties
    private Integer carId;
    private String carBrand;
    private String carModel;
    private int yearProduction;
    private double pricePerDay;

    //Constructor
    public Car(String carBrand, String carModel, int yearProduction, double pricePerDay){
        this.carBrand = carBrand;
        this.carModel = carModel;
        this.yearProduction = yearProduction;
        this.pricePerDay = pricePerDay;
    }


    @Override
    public Integer getId(){
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


    @Override
    public void setId(Integer carId){
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
        return("Car: " + carBrand + " " + carModel + "\nYear of production: " + yearProduction + "\nPrice per day: " + pricePerDay);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return carBrand.equals(car.carBrand) && carModel.equals(car.carModel) && yearProduction == car.yearProduction && pricePerDay == car.pricePerDay;
    }
}
