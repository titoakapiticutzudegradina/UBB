package Repository;

import Domain.Identifiable;

public abstract class FileRepository <IDENTIFIABLE_VALUE, OBJECT extends Identifiable<IDENTIFIABLE_VALUE>> extends MemoryRepository<IDENTIFIABLE_VALUE, OBJECT>{
    protected String filename;

    public FileRepository(String filename) {
        this.filename = filename;
        this.readFromFile();
    }

    abstract void readFromFile();
    abstract void writeToFile();

    @Override
    public void add(IDENTIFIABLE_VALUE id, OBJECT element) {
        super.add(id, element);
        this.writeToFile();
    }

}
