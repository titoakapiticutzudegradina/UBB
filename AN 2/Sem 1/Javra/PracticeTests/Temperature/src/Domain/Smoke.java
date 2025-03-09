package Domain;

public class Smoke implements Producer<Double>{
    private Double last_smoke;
    private int length;

    public Smoke(Double last_smoke,int length) {
        this.last_smoke = last_smoke;
        this.length = length;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    @Override
    public Double getLastR(){return last_smoke;}
    @Override
    public void setLastR(Double last_smoke){this.last_smoke = last_smoke;}

    @Override
    public double computePrice(){
        return 25;
    }
    
    @Override
    public boolean alerts(){
        if(last_smoke > 1600)
            return true;
        return false;
    }
}
