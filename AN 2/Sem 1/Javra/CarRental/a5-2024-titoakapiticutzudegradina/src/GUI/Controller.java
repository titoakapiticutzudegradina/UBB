package GUI;

import Domain.Car;
import Domain.Rental;
import Repository.Repository;
import Service.Service;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public class Controller {
    Service service;
    ObservableList<Rental> rentalsList;
    ObservableList<Car> carsList;

    @FXML
    private Button addCarButton;

    @FXML
    private Button addRentalButton;

    @FXML
    private TextField carBrandTextField;

    @FXML
    private TextField carIdRentalTextField;

    @FXML
    private TextField carIdTextField;

    @FXML
    private TextField carModelTextField;

    @FXML
    private ListView<Car> carsListView;

    @FXML
    private TextField daysForRentalTextField;

    @FXML
    private Button deleteCarButton;

    @FXML
    private Button deleteRentalButton;

    @FXML
    private Button findByIdCarButton;

    @FXML
    private Button findByIdRentalButton;

    @FXML
    private TextField nameForRentalTextField;

    @FXML
    private TextField phoneNumberTextField;

    @FXML
    private TextField pricePerDayTextField;

    @FXML
    private TextField rentalIdTextField;

    @FXML
    private ListView<Rental> rentalsListView;

    @FXML
    private Button updateRentalButton;

    @FXML
    private TextField yearProductionTextField;

    public Controller(Service service) {
        this.service = service;
    }

    public void initialize() {
        ArrayList<Car> cars = (ArrayList<Car>) this.service.getAllCars();
        ArrayList<Rental> rentals = (ArrayList<Rental>) this.service.getAllRentals();
        carsList = FXCollections.observableArrayList(cars);
        rentalsList = FXCollections.observableArrayList(rentals);
        rentalsListView.setItems(rentalsList);
        carsListView.setItems(carsList);
    }

    void resetObservableList(){
        ArrayList<Car> cars = (ArrayList<Car>) this.service.getAllCars();
        ArrayList<Rental> rentals = (ArrayList<Rental>) this.service.getAllRentals();
        carsList = FXCollections.observableArrayList(cars);
        rentalsList = FXCollections.observableArrayList(rentals);
        rentalsListView.setItems(rentalsList);
        carsListView.setItems(carsList);
    }


    void buttonAddCarHandler(ActionEvent event){
        Integer id = Integer.parseInt(carIdTextField.getText());
        String carBrand = carBrandTextField.getText();
        String carModel = carModelTextField.getText();
        Integer yearProduction = Integer.parseInt(yearProductionTextField.getText());
        double price = Double.parseDouble(pricePerDayTextField.getText());
        service.addCar(id,carBrand,carModel,yearProduction,price);
        resetObservableList();
    }

    void buttonAddRentalHandler(ActionEvent event){
        Integer id = Integer.parseInt(rentalIdTextField.getText());
        Integer carId = Integer.parseInt(carIdRentalTextField.getText());
        String name = nameForRentalTextField.getText();
        String phoneNumber = phoneNumberTextField.getText();
        Integer days = Integer.parseInt(daysForRentalTextField.getText());
        Car car = new Car (carId, "mock","mock",0,0);
        Rental rental = new Rental(id,car,name,phoneNumber,days);
        rental.setCar(service.findCarById(carId));
        service.addRental(carId, car.getCarBrand(),car.getCarModel(),car.getYearProduction(),car.getPricePerDay(),id,name,phoneNumber,days);
        resetObservableList();
    }

    void deleteCarHandler(ActionEvent event){
        Integer id = Integer.parseInt(carIdTextField.getText());
        service.removeCar(id);
        resetObservableList();
    }

    void deleteRentalHandler(ActionEvent event){
        Integer id = Integer.parseInt(rentalIdTextField.getText());
        service.removeRental(id);
        resetObservableList();
    }

    void updateCarHandler(ActionEvent event){
        Integer id = Integer.parseInt(carIdTextField.getText());
        String carBrand = carBrandTextField.getText();
        String carModel = carModelTextField.getText();
        Integer yearProduction = Integer.parseInt(yearProductionTextField.getText());
        double price = Double.parseDouble(pricePerDayTextField.getText());
        service.updateCar(id,carBrand,carModel,yearProduction,price);
        resetObservableList();
    }

    void updateRentalHandler(ActionEvent event){
        Integer id = Integer.parseInt(rentalIdTextField.getText());
        Integer carId = Integer.parseInt(carIdRentalTextField.getText());
        String name = nameForRentalTextField.getText();
        String phoneNumber = phoneNumberTextField.getText();
        Integer days = Integer.parseInt(daysForRentalTextField.getText());
        Car car = new Car (carId, "mock","mock",0,0);
        Rental rental = new Rental(id,car,name,phoneNumber,days);
        rental.setCar(service.findCarById(carId));
        service.updateRental(id,carId,car.getCarBrand(),car.getCarModel(),car.getYearProduction(),car.getPricePerDay(),name,phoneNumber,days);
        resetObservableList();
    }

    void findByIdCarHandler(ActionEvent event){
        Integer id = Integer.parseInt(carIdTextField.getText());
        Car car = service.findCarById(id);
        //carsList.clear();
        //carsList.add(car);
    }

    void findByIdRentalHandler(ActionEvent event){
        Integer id = Integer.parseInt(rentalIdTextField.getText());
        Rental rental = service.findRentalById(id);
        //rentalsList.clear();
        //rentalsList.add(rental);
    }


}
