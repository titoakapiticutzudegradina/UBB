package Tests.TestRepository;

import Domain.Car;
import Domain.Rental;
import Repository.RentalRepository;
import Tests.FakeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestRentalRepository {
    private RentalRepository rentalRepository;
    private Rental rental1;
    private Rental rental2;
    private Rental rental3;

    @BeforeEach
    public void beforeTest_SetUp(){
        rentalRepository = new RentalRepository();

        Car car1 = new Car("Audi", "RS5", 2013, 300);
        Car car2 = new Car("BMW", "M3", 2015, 350);
        Car car3 = new Car("Mercedes", "AMG", 2017, 400);

        rental1 = new Rental(1, car1, "Mihai", "0745812046", 5);
        rental2 = new Rental(2, car2, "Ana", "0746968899", 3);
        rental3 = new Rental(3, car3, "Simona", "0770560685", 2);

        rentalRepository.add(1, rental1);
        rentalRepository.add(2, rental2);
        rentalRepository.add(3, rental3);
    }

    @Test
    public void test_add_returnTrue(){
        Car car = new Car("Volkswagen", "Golf", 2019, 50);
        Rental rental = new Rental(4, car, "Cristian", "0747942470", 7);
        rentalRepository.add(4,rental);
        assert(rentalRepository.findById(4) == rental);
    }

    @Test
    public void test_remove_null(){
        rentalRepository.remove(1);
        assert(rentalRepository.findById(1) == null);
    }

    @Test
    public void test_update_updatedRental(){
        Car car = new Car("Volkswagen", "Golf", 2019, 50);
        Rental updatedRental = new Rental(4, car, "Cristian", "0747942470", 7);
        rentalRepository.update(1,updatedRental);
        assert(rentalRepository.findById(1) == updatedRental);
    }

    @Test
    public void test_findById_returnTrue(){
        assert(rentalRepository.findById(1) == rental1);
    }

    @Test
    public void test_findById_returnFalse(){
        Car car = new Car("Volkswagen", "Golf", 2019, 50);
        Rental searchRental = new Rental(4, car, "Cristian", "0747942470", 7);
        assert(rentalRepository.findById(4) != searchRental);
    }

    @Test
    public void test_getAll_returnAll(){
        Iterable<Rental>  expectedResults = List.of(rental1, rental2, rental3);
        Iterator<Rental> iterator = expectedResults.iterator();
        Iterable<Rental> realResults = rentalRepository.getAll();
        for(Rental rental : realResults){
            assertEquals(rental, iterator.next());
        }
    }

    //FakeRepositoryTest
    @Test
    public void test_delete_emptyRepo_throwsException(){
        FakeRepository fakeRepository = new FakeRepository();
        fakeRepository.delete_should_throw_exception = true;
        try {
            fakeRepository.delete();
            assert false;
        }
        catch(Exception e){}
    }
}
