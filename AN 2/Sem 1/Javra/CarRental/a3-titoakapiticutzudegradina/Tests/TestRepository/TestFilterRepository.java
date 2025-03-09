package Tests.TestRepository;

import Domain.Car;
import Domain.Rental;
import Filter.FilterByCar;
import Filter.FilterByName;
import Repository.FilterRepository;
import Repository.RentalRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class TestFilterRepository {
    FilterByCar filterByCar;
    FilterByName filterByName;

    @BeforeEach
    public void beforeTests_SetUp(){
        Car car = new Car("Audi", "RS5", 2013,300);
        filterByCar = new FilterByCar(car);
        filterByName = new FilterByName("Ana");
    }

    @Test
    public void test_filterandSort_filterByCar(){
        Car car1 = new Car("Audi", "RS5", 2013,300);
        Car car2 = new Car("BMW", "M3", 2015,400);
        Rental rental1 = new Rental(1, car1,"Mihaiull","0745907832",9);
        Rental rental2 = new Rental(2, car2,"Ana","0745907832",9);
        RentalRepository repo = new RentalRepository();
        repo.add(1,rental1);
        repo.add(2,rental2);
        Iterable<Rental> filteredRentals = List.of(rental1);
        FilterRepository filterRepo = new FilterRepository(filterByCar, repo.getAll());
        Iterable<Rental> result = filterRepo.filterAndSort(filterByCar, repo.getAll());
        assert(filteredRentals.equals(result));
    }

    @Test
    public void test_filterandSort_filterByName(){
        Car car1 = new Car("Audi", "RS5", 2013,300);
        Car car2 = new Car("BMW", "M3", 2015,400);
        Rental rental1 = new Rental(1, car1,"Mihaiull","0745907832",9);
        Rental rental2 = new Rental(2, car2,"Ana","0745907832",9);
        RentalRepository repo = new RentalRepository();
        repo.add(1,rental1);
        repo.add(2,rental2);
        Iterable<Rental> filteredRentals = List.of(rental2);
        FilterRepository filterRepo = new FilterRepository(filterByName, repo.getAll());
        Iterable<Rental> result = filterRepo.filterAndSort(filterByName, repo.getAll());
        assert(filteredRentals.equals(result));
    }
}
