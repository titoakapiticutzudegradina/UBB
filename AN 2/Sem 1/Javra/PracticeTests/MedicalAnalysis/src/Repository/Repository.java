package Repository;

import Domain.Date;
import Domain.MedicalAnalysis;

import java.util.Iterator;

public interface Repository <IDENTIFIABLE_TYPE, OBJECT extends MedicalAnalysis<Date, String>>{

    void add(IDENTIFIABLE_TYPE identifiable, OBJECT object);
    void delete(IDENTIFIABLE_TYPE identifiable);
    void update(IDENTIFIABLE_TYPE identifiable, OBJECT newObject);
    OBJECT findById(IDENTIFIABLE_TYPE identifiable);
    Iterable<OBJECT> getAll();
    Iterator<OBJECT> iterator();
}
