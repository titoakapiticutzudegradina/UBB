package Tests.TestDomain;

import Domain.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestCar {

    private Car car1;
    private Car car2;
    private Car car3;

    @BeforeEach
    public void beforeTests_setUp(){
        car1 = new Car("Audi", "RS5", 2013, 300);
        car2 = new Car("BMW", "M3", 2015, 350);
        car3 = new Car("Mercedes", "AMG", 2017, 400);
    }

    @Test
    public void test_getCarBrand_returnTrue(){
        assert(car1.getCarBrand().equals("Audi"));
        assert(car2.getCarBrand().equals("BMW"));
        assert(car3.getCarBrand().equals("Mercedes"));
    }

    @Test
    public void test_setCarBrand_returnTrue(){
        car2.setCarBrand("Volkswagen");
        assert(car2.getCarBrand().equals("Volkswagen"));
    }

    @Test
    public void test_getCarModel_returnTrue(){
        assert(car1.getCarModel().equals("RS5"));
        assert(car2.getCarModel().equals("M3"));
        assert(car3.getCarModel().equals("AMG"));
    }

    @Test
    public void test_setCarModel_returnTrue(){
        car2.setCarModel("Golf");
        assert(car2.getCarModel().equals("Golf"));
    }

    @Test
    public void test_getYearProduction_returnTrue(){
        assert(car1.getYearProduction() == 2013);
        assert(car2.getYearProduction() == 2015);
        assert(car3.getYearProduction() == 2017);
    }

    @Test
    public void test_setYearProduction_returnTrue(){
        car2.setYearProduction(2019);
        assert(car2.getYearProduction() == 2019);
    }

    @Test
    public void test_getPricePerDay_returnTrue(){
        assert(car1.getPricePerDay() == 300);
        assert(car2.getPricePerDay() == 350);
        assert(car3.getPricePerDay() == 400);
    }

    @Test
    public void test_setPricePerDay_returnTrue(){
        car2.setPricePerDay(500);
        assert(car2.getPricePerDay() == 500);
    }

    @Test
    public void test_equal_returnTrue(){
        Car carToTestAgainst = new Car("Audi", "RS5", 2013, 300);
        assert(car1.equals(carToTestAgainst));
    }
    @Test
    public void test_equal_returnFalse(){
        Car carToTestAgainst = new Car("Audi", "RS5", 2013, 300);
        assert(!car2.equals(carToTestAgainst));
    }
}
