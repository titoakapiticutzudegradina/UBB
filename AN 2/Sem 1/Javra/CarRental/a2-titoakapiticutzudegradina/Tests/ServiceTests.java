//package Tests;
//
//import Domain.Car;
//import Domain.Rental;
//import Repository.FakeRepository;
//import Repository.Repository;
//import Service.Service;
//
//public class ServiceTests {
//    Repository<Integer, Rental> fakeRepo = new FakeRepository();
//    Service service = new Service(fakeRepo);
//
//    public void addRental_ValidInput_RentalAdded() {
//        Car car2 = new Car( "BMW", "X5", 2015, 200);
//        Rental rental2 = new Rental(2, car2, "Alice", "0987654321", 20);
//
//        service.addRental( "BMW", "X5", 2015, 200,2, "Alice", "0987654321", 20);
//
//    }
//}
