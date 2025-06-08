package ru.FertGl.carsapi;


import java.util.Objects;

public class Car {
    private String brand;
    private int year;
    private String color;
    private int price;

    public Car(String brand, int year, String color, int price) {
        this.brand = brand;
        this.year = year;
        this.color = color;
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public int getYear() {
        return year;
    }

    public String getColor() {
        return color;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return year == car.year && price == car.price && Objects.equals(brand, car.brand) && Objects.equals(color, car.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brand, year, color, price);
    }

    @Override
    public String toString() {
        return "brand='" + brand + '\'' +
                ", year=" + year +
                ", color='" + color + '\'' +
                ", price=" + price +
                '}';
    }
}

