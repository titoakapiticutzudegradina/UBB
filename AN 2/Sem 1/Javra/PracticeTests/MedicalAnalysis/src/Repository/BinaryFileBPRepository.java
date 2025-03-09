package Repository;

import Domain.BloodPressure;

import java.io.*;
import java.util.HashMap;

public class BinaryFileBPRepository extends FileRepository<Integer, BloodPressure>{
    public BinaryFileBPRepository(String filename) {
        super(filename);
    }

    //@Override
    protected void readFromFile() {
        try(ObjectInputStream rentalInputStream = new ObjectInputStream(new FileInputStream(filename))) {
            this.map = (HashMap<Integer, BloodPressure>) rentalInputStream.readObject();
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
            rentalOutputStream.reset();
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
