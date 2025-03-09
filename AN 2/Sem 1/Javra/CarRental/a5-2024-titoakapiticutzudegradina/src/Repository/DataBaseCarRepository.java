package Repository;

import Domain.Car;

import java.sql.*;

public class DataBaseCarRepository extends CarRepository {
    public String URL;

    public DataBaseCarRepository(String repoPath) {
        this.URL = "jdbc:sqlite" + repoPath;
        readFromDataBase();
    }

    public void readFromDataBase(){
        try(Connection connection = DriverManager.getConnection(URL)){
            //Class.forName("org.sqlite.JDBC");
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Car;");
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                int id = resultSet.getInt(1);
                String carBrand = resultSet.getString(2);
                String carModel = resultSet.getString(3);
                int yearProduction = resultSet.getInt(4);
                double pricePerDay = resultSet.getDouble(5);
                Car car = new Car(id, carBrand, carModel, yearProduction, pricePerDay);
                this.map.put(id, car);
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }// catch (ClassNotFoundException e) {
        //    throw new RuntimeException(e);
        //}
    }

    @Override
    public void add(Integer identifiable, Car car){
        super.add(identifiable, car);

        try(Connection connection = DriverManager.getConnection(URL)){
            PreparedStatement statement = connection.prepareStatement("INSERT INTO Car VALUES (?,?,?,?,?);");
            statement.setInt(1,car.getId());
            statement.setString(2, car.getCarBrand());
            statement.setString(3, car.getCarModel());
            statement.setInt(4, car.getYearProduction());
            statement.setDouble(5, car.getPricePerDay());
            statement.executeUpdate();

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
