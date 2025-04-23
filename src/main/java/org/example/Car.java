package org.example; 
import java.util.Objects;

public class Car {
    private String model;
    private String make;
    private double price; 

    public Car(String model, String make, double price) {
        this.model = model;
        this.make = make;
        this.price = price;
    }

    public String getModel() {
        return model;
    }

    public String getMake() {
        return make;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Car{model='" + model + "', make='" + make + "', price=" + price + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Double.compare(car.price, price) == 0 && Objects.equals(model, car.model) && Objects.equals(make, car.make);
    }

    @Override
    public int hashCode() {
        return Objects.hash(model, make, price);
    }

    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );


    }
}