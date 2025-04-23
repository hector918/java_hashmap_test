package org.example; 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CarInventory {
    private int carCount = 0;
    private HashMap<String, List<Car>> carList;
    public CarInventory() {
        this.carList = new HashMap<String, List<Car>>();
    }

    public void addCar(Car car) {
        if(car == null) throw new IllegalArgumentException("car can not be null.");

        // usually same model can have multiple cars
        if(this.carList.containsKey(car.getModel())) throw new IllegalArgumentException("you can not have duplicate model.");

        this.carList.computeIfAbsent(car.getModel(), k -> new ArrayList<>()).add(car);
        carCount++;
    }

    public Car getCar(String model) {
        if(model == null) throw new IllegalArgumentException("model can not be null.");
        if(!carList.containsKey(model) || carList.get(model).isEmpty()) return null;
        return carList.get(model).get(0);
    }

    public Car sellCar(String model) {
        if(model == null) throw new IllegalArgumentException("model can not be null.");

        if(!carList.containsKey(model) || carList.get(model).isEmpty()){
            return null;
        }else{
            Car car = carList.get(model).remove(0);
            this.carCount--;
            return car;
        }
    }

    public void updateCarPrice(String model, double newPrice) {
        if(model == null) throw new IllegalArgumentException("model can not be null.");
        if(!carList.containsKey(model) || carList.get(model).isEmpty()){

        }else{
            this.carList.get(model).get(0).setPrice(newPrice);
        }

    }

    public List<Car> findCarsByMake(String make) {
        if(make == null) throw new IllegalArgumentException("make can not be null.");
        if(make.isEmpty()) throw new IllegalArgumentException("make can not be empty.");

        List<Car> ret = new ArrayList<>();
        for(List<Car> list : this.carList.values()){
            for(Car car : list){
                if(car.getMake() == make){
                    ret.add(car);
                }
            }
        }
        return ret;
    }

    public int getCarCount() {
        return carCount;
    }

    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );


    }
}