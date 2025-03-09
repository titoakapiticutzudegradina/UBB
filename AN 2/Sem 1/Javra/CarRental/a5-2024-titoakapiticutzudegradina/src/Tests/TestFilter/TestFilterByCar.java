package Tests.TestFilter;

import Domain.Car;
import Domain.Rental;
import Filter.FilterByCar;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestFilterByCar {
    private FilterByCar filterByCar;

    @BeforeEach
    public void beforeTests_SetUp(){
        Car filtercar = new Car(1,"Audi", "RS5", 2013,300);
        filterByCar = new FilterByCar(filtercar);
    }

    @Test
    public void test_accept_returnTrue(){
        Car car = new Car(1,"Audi", "RS5", 2013,300);
        Rental rental = new Rental (1, car, "Mihaiull","0745802146", 5);
        assert(filterByCar.accept(rental));
    }

}
