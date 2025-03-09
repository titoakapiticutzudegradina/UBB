package Repository;

import Domain.Identifiable;

import java.util.Iterator;

public interface Repository <IDENTIFIABLE_TYPE, OBJECT extends Identifiable<IDENTIFIABLE_TYPE>>{

    void add(IDENTIFIABLE_TYPE id, OBJECT element);         // Add an element to the repository
    void remove(IDENTIFIABLE_TYPE id);                 // Remove an element from the repository
    void update(IDENTIFIABLE_TYPE id, OBJECT element);      // Update an element from the repository
    OBJECT findById(IDENTIFIABLE_TYPE id);                  // Find an element by its id
    Iterable<OBJECT> getAll();                         // Get all elements from the repository
    Iterator<OBJECT> iterator();                       // Get an iterator for the repository
}
