package ru.FertGl.carsapi.model;

import ru.FertGl.carsapi.Car;

import java.sql.*;
import java.util.HashMap;

public class CarModel {
    private static final String PATH = "jdbc:mysql://127.0.0.1:3306/Car";
    private static final String USER_NAME = "root";
    private static final String PASS = "Gleb01012003";

    public Car add(Car car) {
        try (Connection connection = DriverManager.getConnection(PATH, USER_NAME, PASS)) {
            Statement statement = connection.createStatement();

            String sqlQuery = """
                    
                    INSERT INTO Car (Brand, Year, Color, Price)
                    VALUES (?,?,?,?);
                    
                    """;

            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
                preparedStatement.setString(1, car.getBrand());
                preparedStatement.setInt(2, car.getYear());
                preparedStatement.setString(3, car.getColor());
                preparedStatement.setInt(4, car.getPrice());
                preparedStatement.executeUpdate();
                return car;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return car;
    }

    public HashMap<Integer, Car> get() {
        try {
            Connection connection = DriverManager.getConnection(PATH, USER_NAME, PASS);
            Statement statement = connection.createStatement();

            String sqlQuery = "SELECT * FROM Car";
            HashMap<Integer, Car> cars = new HashMap<>();

            ResultSet resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()) {
                cars.put(resultSet.getInt(1), new Car(resultSet.getString(2),
                        resultSet.getInt(3), resultSet.getString(4),
                        resultSet.getInt(5)));
            }
            return cars;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Car getById(Integer id) {
        try {
            Connection connection = DriverManager.getConnection(PATH, USER_NAME, PASS);
            Statement statement = connection.createStatement();

            String sqlQuery = " SELECT * FROM Car " +
                    "WHERE id = " + id + ";";
            Car car = null;
            ResultSet resultSet = statement.executeQuery(sqlQuery);

            while (resultSet.next()) {
                car = new Car(resultSet.getString(2),
                        resultSet.getInt(3), resultSet.getString(4),
                        resultSet.getInt(5));
            }
            return car;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Car update(Integer id, Car car) {
        try (Connection connection = DriverManager.getConnection(PATH, USER_NAME, PASS)) {
            Statement statement = connection.createStatement();

            String sqlQuery = """
                    
                    UPDATE Car
                    SET Brand = ?, Year = ?, Color = ?, Price = ?
                    WHERE id = ?;
                    
                    """;

            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
                preparedStatement.setString(1, car.getBrand());
                preparedStatement.setInt(2, car.getYear());
                preparedStatement.setString(3, car.getColor());
                preparedStatement.setInt(4, car.getPrice());
                preparedStatement.setInt(5, id);
                preparedStatement.executeUpdate();
                return car;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Boolean update(Integer id, String newColor) {
        try (Connection connection = DriverManager.getConnection(PATH, USER_NAME, PASS)) {
            Statement statement = connection.createStatement();

            String sqlQuery = """
                    
                    UPDATE Car
                    SET Color = ?
                    WHERE id = ?;
                    
                    """;

            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
                preparedStatement.setString(1, newColor);
                preparedStatement.setInt(2, id);
                preparedStatement.executeUpdate();
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Boolean delete(Integer id) {
        try (Connection connection = DriverManager.getConnection(PATH, USER_NAME, PASS)) {
            Statement statement = connection.createStatement();

            String sqlQuery = """
                    
                    DELETE FROM Car
                    WHERE id = ?;
                    
                    """;

            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
                preparedStatement.setInt(1, id);
                preparedStatement.executeUpdate();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
