package Repository;

import Domain.Producer;
import Domain.Temperature;

import java.util.Iterator;

public interface Repository <LAST_RECORD, OBJECT extends Producer<LAST_RECORD>>{

    void add(LAST_RECORD lr, OBJECT element);         // Add an element to the repository
    void remove(LAST_RECORD lr);                 // Remove an element from the repository
    void update(LAST_RECORD lr, OBJECT element);      // Update an element from the repository
    OBJECT findById(LAST_RECORD lr);                  // Find an element by its id
    Iterable<OBJECT> getAll();                         // Get all elements from the repository
    Iterator<OBJECT> iterator();                       // Get an iterator for the repository
}
