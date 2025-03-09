package Tests.TestService;

import Domain.Car;
import Domain.Rental;
import Repository.CarRepository;
import Repository.RentalRepository;
import Service.Service;
import Validator.CarValidator;
import Validator.RentalValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.List;

public class TestService {
    Service service;

    @BeforeEach
    public void beforeTests_SetUp(){
        RentalRepository repo = new RentalRepository();
        CarRepository carRepo = new CarRepository();
        CarValidator carValidator = new CarValidator();
        RentalValidator rentalValidator = new RentalValidator();
        Car car1 = new Car(1,"Audi", "RS5", 2013,300);
        Car car2 = new Car(2,"BMW", "M3", 2015,400);
        Car car3 = new Car(3,"Mercedes", "AMG", 2017,500);
        Rental rental1 = new Rental(1, car1,"Mihaiull","0745907832",9);
        Rental rental2 = new Rental(2, car2,"Ana","0745907832",9);
        Rental rental3 = new Rental(3, car3,"Simona","0745907832",9);
        repo.add(1,rental1);
        repo.add(2,rental2);
        repo.add(3,rental3);
        service = new Service(repo,carRepo, carValidator, rentalValidator);
    }

    @Test
    public void test_addRental_returnTrue(){
        Car car = new Car(1,"Audi", "RS7", 2020,250);
        Rental rentalAdded = new Rental(4, car,"Mihaiull","0745907832",8);
        service.addRental(2,"Audi", "RS7", 2020,250, 4,"Mihaiull", "0745907832", 8);
        assert(service.findRentalById(4).equals(rentalAdded));
    }

    @Test
    public void test_removeRental_returnTrue(){
        service.removeRental(1);
        assert(service.findRentalById(1) == null);
    }

    @Test
    public void test_updateRental_returnTrue(){
        Car car = new Car(1,"Audi", "RS7", 2020,250);
        Rental rentalUpdated = new Rental(1, car,"Mihaiull","0745907832",8);
        service.updateRental(1,1, "Audi", "RS7", 2020,250, "Mihaiull", "0745907832", 8);
        assert(service.findRentalById(1).equals(rentalUpdated));
    }

    @Test
    public void test_getAllRentals_returnAllRentals(){
        Car car1 = new Car(1,"Audi", "RS5", 2013,300);
        Car car2 = new Car(2,"BMW", "M3", 2015,400);
        Car car3 = new Car(3,"Mercedes", "AMG", 2017,500);
        Rental rental1 = new Rental(1, car1,"Mihaiull","0745907832",9);
        Rental rental2 = new Rental(2, car2,"Ana","0745907832",9);
        Rental rental3 = new Rental(3, car3,"Simona","0745907832",9);
        Iterable<Rental> expectedRentals = List.of(rental1,rental2,rental3);
        Iterable<Rental> actualRentals = service.getAllRentals();
        Iterator<Rental> actualRentalsIterator = actualRentals.iterator();
        for (Rental expectedRental: expectedRentals) {
            assert(expectedRental.equals(actualRentalsIterator.next()));
        }
    }

    @Test
    public void test_findRentalById_returnRental(){
        Car car = new Car(1,"Audi", "RS5", 2013,300);
        Rental rental = new Rental(1, car,"Mihaiull","0745907832",9);
        assert(service.findRentalById(1).equals(rental));
    }

    @Test
    public void test_filterRentalsByCar_returnFilteredRentals(){
        Car car = new Car(1,"Audi", "RS5", 2013,300);
        Rental rental = new Rental(1, car,"Mihaiull","0745907832",9);
        Iterable<Rental> expectedRentals = List.of(rental);
        Iterable<Rental> actualRentals = service.filterByCar(1,"Audi", "RS5", 2013,300);
        Iterator<Rental> actualRentalsIterator = actualRentals.iterator();
        for (Rental expectedRental: expectedRentals) {
            assert(expectedRental.equals(actualRentalsIterator.next()));
        }
    }

    @Test
    public void test_filterRentalsByName_returnFilteredRentals(){
        Car car = new Car(1,"Audi", "RS5", 2013,300);
        Rental rental = new Rental(1, car,"Mihaiull","0745907832",9);
        Iterable<Rental> expectedRentals = List.of(rental);
        Iterable<Rental> actualRentals = service.filterByName("Mihaiull");
        Iterator<Rental> actualRentalsIterator = actualRentals.iterator();
        for (Rental expectedRental: expectedRentals) {
            assert(expectedRental.equals(actualRentalsIterator.next()));
        }
    }
}
