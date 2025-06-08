package ru.FertGl.carsapi.model;

import ru.FertGl.carsapi.Car;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

    public List<Car> filter(String brand, String color) {
        try (Connection connection = DriverManager.getConnection(PATH, USER_NAME, PASS)) {
            Statement statement = connection.createStatement();
            List<Car> cars = new ArrayList<>();

            String sqlQuery = """
                    
                    SELECT * FROM Car
                    WHERE brand = ? AND color = ?;
                    
                    """;

            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
                preparedStatement.setString(1, brand);
                preparedStatement.setString(2, color);
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    cars.add(new Car(resultSet.getString(2),
                            resultSet.getInt(3), resultSet.getString(4),
                            resultSet.getInt(5)));
                }
            }
            return cars;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public List<Car> filterBrand(String brand) {
        try (Connection connection = DriverManager.getConnection(PATH, USER_NAME, PASS)) {
            Statement statement = connection.createStatement();
            List<Car> cars = new ArrayList<>();

            String sqlQuery = """
                    
                    SELECT * FROM Car
                    WHERE brand = ?;
                    
                    """;

            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
                preparedStatement.setString(1, brand);
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    cars.add(new Car(resultSet.getString(2),
                            resultSet.getInt(3), resultSet.getString(4),
                            resultSet.getInt(5)));
                }
            }
            return cars;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public List<Car> filterColor(String color) {
        try (Connection connection = DriverManager.getConnection(PATH, USER_NAME, PASS)) {
            Statement statement = connection.createStatement();
            List<Car> cars = new ArrayList<>();

            String sqlQuery = """
                    
                    SELECT * FROM Car
                    WHERE color = ?;
                    
                    """;

            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
                preparedStatement.setString(1, color);
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    cars.add(new Car(resultSet.getString(2),
                            resultSet.getInt(3), resultSet.getString(4),
                            resultSet.getInt(5)));
                }
            }
            return cars;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public static List<Car> sort(String by, String order) {
        try (Connection connection = DriverManager.getConnection(PATH, USER_NAME, PASS)) {
            Statement statement = connection.createStatement();
            List<Car> cars = new ArrayList<>();;


            if (order.equalsIgnoreCase("ASC") & by.equalsIgnoreCase("price")) {

                String sqlQuery = """
                        
                        SELECT * FROM Car
                        ORDER BY Price ASC;
                        
                        """;


                ResultSet resultSet = statement.executeQuery(sqlQuery);

                while (resultSet.next()) {
                    cars.add(new Car(resultSet.getString(2),
                            resultSet.getInt(3), resultSet.getString(4),
                            resultSet.getInt(5)));
                }

            } else if (order.equalsIgnoreCase("DESC") & by.equalsIgnoreCase("price")) {
                String sqlQuery = """
                        
                        SELECT * FROM Car
                        ORDER BY Price DESC;
                        
                        """;

                ResultSet resultSet = statement.executeQuery(sqlQuery);

                while (resultSet.next()) {
                    cars.add(new Car(resultSet.getString(2),
                            resultSet.getInt(3), resultSet.getString(4),
                            resultSet.getInt(5)));
                }

            } else if (order.equalsIgnoreCase("ASC") & by.equalsIgnoreCase("year")) {
                String sqlQuery = """
                        
                        SELECT * FROM Car
                        ORDER BY Year ASC;
                        
                        """;

                ResultSet resultSet = statement.executeQuery(sqlQuery);

                while (resultSet.next()) {
                    cars.add(new Car(resultSet.getString(2),
                            resultSet.getInt(3), resultSet.getString(4),
                            resultSet.getInt(5)));
                }
            } else if (order.equalsIgnoreCase("DESC") & by.equalsIgnoreCase("year")) {
                String sqlQuery = """
                        
                        SELECT * FROM Car
                        ORDER BY Year DESC;
                        
                        """;

                ResultSet resultSet = statement.executeQuery(sqlQuery);

                while (resultSet.next()) {
                    cars.add(new Car(resultSet.getString(2),
                            resultSet.getInt(3), resultSet.getString(4),
                            resultSet.getInt(5)));
                }
            }
            return cars;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}