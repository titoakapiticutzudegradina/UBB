package Tests.TestFilter;

import Domain.Car;
import Domain.Rental;
import Filter.FilterByName;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class TestFilterByName {
    private FilterByName filterByName;

    @BeforeEach
    public void beforeTests_SetUp(){
        filterByName = new FilterByName("Mihaiull");
    }

    @Test
    public void test_accept_returnTrue(){
        Car car = new Car("Audi", "RS5", 2013,300);
        Rental rental = new Rental(1, car, "Mihaiull","0745812046", 5);
        assert(filterByName.accept(rental));
    }

}
