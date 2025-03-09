package Repository;

import Domain.Identifiable;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public abstract class FileRepository <IDENTIFIABLE_VALUE, OBJECT extends Identifiable<IDENTIFIABLE_VALUE>> extends MemoryRepository<IDENTIFIABLE_VALUE, OBJECT>{
    protected String filename;

    public FileRepository(String filename) throws ParserConfigurationException, IOException, SAXException {
        this.filename = filename;
        this.readFromFile();
    }

    abstract void readFromFile() throws ParserConfigurationException, IOException, SAXException;
    abstract void writeToFile();

    @Override
    public void add(IDENTIFIABLE_VALUE id, OBJECT element) {
        super.add(id, element);
        this.writeToFile();
    }

}
