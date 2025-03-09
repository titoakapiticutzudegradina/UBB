package Repository;

import Domain.Rental;
import org.xml.sax.SAXException;


import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.HashMap;

public class BinaryFileRentalRepository extends FileRepository<Integer, Rental> {
    public BinaryFileRentalRepository(String filename) throws ParserConfigurationException, IOException, SAXException {
        super(filename);
    }

    @Override
    protected void readFromFile() {
        try(ObjectInputStream rentalInputStream = new ObjectInputStream(new FileInputStream(filename))) {
            this.map = (HashMap<Integer, Rental>) rentalInputStream.readObject();
        }
        catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
        catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void writeToFile() {
        try(ObjectOutputStream rentalOutputStream = new ObjectOutputStream(new FileOutputStream(filename))) {
            rentalOutputStream.writeObject(map);
        }
        catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
