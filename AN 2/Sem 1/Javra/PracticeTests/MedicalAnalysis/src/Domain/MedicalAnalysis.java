package Domain;

public interface MedicalAnalysis <DATE, STATUS>{
    DATE getDate();
    void setDate(DATE date);
    STATUS getStatus();
    void setStatus(STATUS status);

}
