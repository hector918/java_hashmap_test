package org.example; // Make sure this matches your package

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class CarInventoryTest {

    private CarInventory inventory;
    private Car ford1 = new Car("Focus", "Ford", 20000.0);
    private Car toyota1 = new Car("Camry", "Toyota", 25000.0);
    private Car honda1 = new Car("Civic", "Honda", 22000.0);

    @Before
    public void setUp() {
        inventory = new CarInventory();
        inventory.addCar(ford1);
        inventory.addCar(toyota1);
        inventory.addCar(honda1);
    }

    @Test
    public void testAddCar_Success() {
        Car newCar = new Car("Mustang", "Ford", 30000.0);
        inventory.addCar(newCar);
        assertEquals(4, inventory.getCarCount());
        assertEquals(newCar, inventory.getCar("Mustang"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddCar_Null() {
        inventory.addCar(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddCar_DuplicateModel() {
        inventory.addCar(new Car("Focus", "Ford", 21000.0)); // Duplicate model
    }

    @Test
    public void testGetCar_Found() {
        assertEquals(toyota1, inventory.getCar("Camry"));
    }

    @Test
    public void testGetCar_NotFound() {
        assertNull(inventory.getCar("NonExistentModel"));
    }

    @Test
    public void testSellCar_Success() {
        Car soldCar = inventory.sellCar("Camry");
        assertEquals(toyota1, soldCar);
        assertNull(inventory.getCar("Camry"));
        assertEquals(2, inventory.getCarCount());
    }

    @Test
    public void testSellCar_NotFound() {
        Car soldCar = inventory.sellCar("NonExistentModel");
        assertNull(soldCar);
        assertEquals(3, inventory.getCarCount()); 
    }

    @Test
    public void testUpdateCarPrice_Success() {
        inventory.updateCarPrice("Focus", 21000.0);
        assertEquals(21000.0, inventory.getCar("Focus").getPrice(), 0.001); 
    }

    @Test
    public void testUpdateCarPrice_NotFound() {
        inventory.updateCarPrice("NonExistentModel", 21000.0); 
        assertNull(inventory.getCar("NonExistentModel"));
    }

    @Test
    public void testFindCarsByMake_Found() {
        List<Car> fordCars = inventory.findCarsByMake("Ford");
        assertEquals(1, fordCars.size());
        assertEquals(ford1, fordCars.get(0));
    }

    @Test
    public void testFindCarsByMake_NotFound() {
        List<Car> mazdaCars = inventory.findCarsByMake("Mazda");
        assertTrue(mazdaCars.isEmpty());
    }

    @Test
    public void testGetCarCount() {
        assertEquals(3, inventory.getCarCount());
    }
}