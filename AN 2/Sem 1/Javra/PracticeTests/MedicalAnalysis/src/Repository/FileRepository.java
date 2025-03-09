package Repository;


import Domain.Date;
import Domain.MedicalAnalysis;

public abstract class FileRepository <IDENTIFIABLE_VALUE, OBJECT extends MedicalAnalysis<Date, String>> extends MedicalAnalysisRepository<IDENTIFIABLE_VALUE, OBJECT>{
    protected String filename;

    public FileRepository(String filename) {
        this.filename = filename;
        //this.readFromFile();
    }

    //abstract void readFromFile();
    abstract void writeToFile();

    @Override
    public void add(IDENTIFIABLE_VALUE id, OBJECT element) {
        super.add(id, element);
        this.writeToFile();
    }

}
