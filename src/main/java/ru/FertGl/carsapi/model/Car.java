package ru.FertGl.carsapi.model;


public class Car {
    private String brand;
    private long year;
    private String color;
    private int price;
    private int id = 0;

    public Car(String brand, long year, String color, int price) {
        this.id++;
        this.brand = brand;
        this.year = year;
        this.color = color;
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public long getYear() {
        return year;
    }

    public String getColor() {
        return color;
    }

    public int getPrice() {
        return price;
    }
}

