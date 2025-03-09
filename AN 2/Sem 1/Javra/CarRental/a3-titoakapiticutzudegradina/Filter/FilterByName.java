package Filter;

import Domain.Rental;

//Filter method that gets the rentals with the same name
public class FilterByName implements Filter<Rental>{
    private String name;

    public FilterByName(String name) {
        this.name = name;
    }

    @Override
    public boolean accept(Rental element){
        return element.getNameForRental().equals(name);
    }
}
