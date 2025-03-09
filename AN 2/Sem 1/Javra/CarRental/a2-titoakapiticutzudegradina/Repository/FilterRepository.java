package Repository;

import Domain.Rental;
import Filter.Filter;

import java.util.ArrayList;

public class FilterRepository extends MemoryRepository<Integer, Rental>{
    private Filter<Rental> filter;
    private Iterable<Rental> rentals = new ArrayList<>();

    public FilterRepository(Filter<Rental> filter, Iterable<Rental> rentals) {
        this.filter = filter;
        this.rentals = rentals;
    }

   public Iterable<Rental> filterAndSort(Filter<Rental> filter, Iterable<Rental> rentals) {
       ArrayList<Rental> filteredRentals = new ArrayList<>();
         for (Rental rental : rentals) {
              if (filter.accept(rental)) {
                filteredRentals.add(rental);
              }
         }
       return filteredRentals;
    }
}
