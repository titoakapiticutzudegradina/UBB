package Tests.TestDomain;

import Domain.Car;
import Domain.Rental;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestRental {
    private Rental rental1;
    private Rental rental2;
    private Rental rental3;

    @BeforeEach
    public void beforeTests_SetUp(){
        Car car1 = new Car("Audi", "RS5", 2013, 300);
        Car car2 = new Car("BMW", "M3", 2015, 350);
        Car car3 = new Car("Mercedes", "AMG", 2017, 400);

        rental1 = new Rental(1, car1, "Mihai", "0745812046", 5);
        rental2 = new Rental(2, car2, "Ana", "0746968899", 3);
        rental3 = new Rental(3, car3, "Simona", "0770560685", 2);
    }

    @Test
    public void test_getID_returnTrue(){
        assert(rental1.getId() == 1);
        assert(rental2.getId() == 2);
        assert(rental3.getId() == 3);
    }

    @Test
    public void test_setID_returnTrue(){
        rental2.setId(4);
        assert(rental2.getId() == 4);
    }

    @Test
    public void test_getCar_returnTrue(){
        assert(rental1.getCar().equals(new Car("Audi", "RS5", 2013, 300)));
        assert(rental2.getCar().equals(new Car("BMW", "M3", 2015, 350)));
        assert(rental3.getCar().equals(new Car("Mercedes", "AMG", 2017, 400)));
    }

    @Test
    public void test_setCar_returnTrue(){
        Car car = new Car("Volkswagen", "Golf", 2019, 50);
        rental2.setCar(car);
        assert(rental2.getCar().equals(car));
    }

    @Test
    public void test_getNameForRental_returnTrue(){
        assert(rental1.getNameForRental().equals("Mihai"));
        assert(rental2.getNameForRental().equals("Ana"));
        assert(rental3.getNameForRental().equals("Simona"));
    }

    @Test
    public void test_setNameForRental_returnTrue(){
        rental2.setNameForRental("Mihai");
        assert(rental2.getNameForRental().equals("Mihai"));
    }

    @Test
    public void test_getPhoneNumber_returnTrue(){
        assert(rental1.getPhoneNumber().equals("0745812046"));
        assert(rental2.getPhoneNumber().equals("0746968899"));
        assert(rental3.getPhoneNumber().equals("0770560685"));
    }

    @Test
    public void test_setPhoneNumber_returnTrue(){
        rental2.setPhoneNumber("0745812046");
        assert(rental2.getPhoneNumber().equals("0745812046"));
    }

    @Test
    public void test_getDaysForRental_returnTrue(){
        assert(rental1.getDaysForRental() == 5);
        assert(rental2.getDaysForRental() == 3);
        assert(rental3.getDaysForRental() == 2);
    }

    @Test
    public void test_setDaysForRental_returnTrue(){
        rental2.setDaysForRental(4);
        assert(rental2.getDaysForRental() == 4);
    }
}
