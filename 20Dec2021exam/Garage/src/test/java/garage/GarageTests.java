package garage;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class GarageTests {
    private Car audi;
    private Car bmw;
    private Garage testGarage;

    @Before
    public void setUp() {
        audi = new Car("Audi", 250, 30000.00);
        bmw = new Car("BMW", 200, 35000.00);
        testGarage = new Garage();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void test_Collection_ShouldThrow_IfTrue_ToModifiedCollection(){
        testGarage.addCar(bmw);
        testGarage.getCars().remove(bmw);
    }

    @Test
    public void test_getCount_Success(){
        testGarage.addCar(audi);
        assertEquals(1, testGarage.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_AddCar_ShouldThrow_IfNull(){
        testGarage.addCar(null);
    }

    @Test
    public void test_FindAllCars_WithMaxSpeed_Above(){
        testGarage.addCar(audi);
        testGarage.addCar(bmw);
        Garage garage = new Garage();
        garage.addCar(audi);
        assertEquals(garage.getCars(), testGarage.findAllCarsWithMaxSpeedAbove(210));
    }

    @Test
    public void test_FindAllCars_ByBrand(){
        testGarage.addCar(audi);
        testGarage.addCar(bmw);
        Garage garage = new Garage();
        garage.addCar(audi);
        assertEquals(garage.getCars(), testGarage.findAllCarsByBrand("Audi"));
    }

    @Test
    public void test_Get_TheMostExpensiveCar(){
        testGarage.addCar(audi);
        testGarage.addCar(bmw);
        assertEquals(bmw, testGarage.getTheMostExpensiveCar());
    }
}