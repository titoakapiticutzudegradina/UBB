package Repository;

import Domain.BodyMassIndex;

import java.io.*;
import java.util.HashMap;

public class BinaryFileBMIRepository extends FileRepository<Integer, BodyMassIndex>{
    public BinaryFileBMIRepository(String filename) {
        super(filename);
    }

    //@Override
    protected void readFromFile() {
        try(ObjectInputStream rentalInputStream = new ObjectInputStream(new FileInputStream(filename))) {
            this.map = (HashMap<Integer, BodyMassIndex>) rentalInputStream.readObject();
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
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
