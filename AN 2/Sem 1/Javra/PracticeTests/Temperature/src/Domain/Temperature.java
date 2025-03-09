package Domain;

public class Temperature implements Producer<Double>{
    private Double last_temperature;
    private int diameter;

    public Temperature(Double last_temperature,int diameter) {
        this.last_temperature = last_temperature;
        this.diameter = diameter;
    }

    public int getDiameter() {
        return diameter;
    }

    public void setDiameter(int diameter) {
        this.diameter = diameter;
    }

    @Override
    public Double getLastR(){return last_temperature;}
    @Override
    public void setLastR(Double last_temperature){this.last_temperature = last_temperature;}

    @Override
    public double computePrice(){
        double totalPrice = 9;
        if(diameter < 3)
            totalPrice +=8;
        return totalPrice;
    }

    @Override
    public boolean alerts(){
        if(last_temperature < 10 || last_temperature > 35)
            return true;
        return false;
    }

}
