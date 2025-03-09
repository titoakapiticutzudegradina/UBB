package Domain;

public interface Producer <LAST_RECORDING>{
    LAST_RECORDING getLastR();
    void setLastR(LAST_RECORDING last_recording);

    public double computePrice();
    public boolean alerts();

}
