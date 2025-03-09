package Repository;

import Domain.Car;
import Domain.Rental;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;

public class DataBaseRentalRepository extends RentalRepository{
    public String URL;

    public DataBaseRentalRepository(String repoPath){
        this.URL = "jdbc:sqlite:" + repoPath;
        readFromDataBase();
    }

    public void readFromDataBase(){
        try (Connection connection = DriverManager.getConnection(URL)){
            //System.out.println("Connected to the database");
            Class.forName("org.sqlite.JDBC");
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Rental;");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt(1);
                int carId = resultSet.getInt(2);
                String clientName = resultSet.getString(3);
                String clientPhoneNumber = resultSet.getString(4);
                int rentedHours = resultSet.getInt(5);
                Car car = new Car(carId,"mock", "mock", 0, 0);
                Rental rental = new Rental(id, car, clientName, clientPhoneNumber, rentedHours);
                //We need the actual car from the car table
                DataBaseCarRepository carRepository = new DataBaseCarRepository("data/car.sqlite");
                for(Car actualCar : carRepository.getAll()){
                    if(actualCar.getId().equals(carId)){
                        rental.setCar(actualCar);
                        break;
                    }
                }
                this.map.put(id, rental);
            }
        } catch (SQLException e){
            throw new RuntimeException(e);
        } //catch (ClassNotFoundException e) {
        catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        // throw new RuntimeException(e);
        //}
    }

    @Override
    public void add(Integer identifiable, Rental rental){
        super.add(identifiable, rental);

        try(Connection connection = DriverManager.getConnection(URL)){
            PreparedStatement statement = connection.prepareStatement("INSERT INTO Rental VALUES (?,?,?,?,?);");
            statement.setInt(1, rental.getId());
            statement.setInt(2, rental.getCar().getId());
            statement.setString(3, rental.getNameForRental());
            statement.setString(4, rental.getPhoneNumber());
            statement.setInt(5, rental.getDaysForRental());
            statement.executeUpdate();
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
