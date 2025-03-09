package Filter;

import Domain.Car;
import Domain.Rental;


//Filter method that gets the rentals with the same car
public class FilterByCar implements Filter<Rental>{
    private Car car;

    public FilterByCar(Car car) {
        this.car = car;
    }

    @Override
    public boolean accept(Rental element){
        return element.getCar().equals(car);
    }
}
