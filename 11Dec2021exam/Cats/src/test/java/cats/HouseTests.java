package cats;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class HouseTests {

    private Cat tobi;
    private Cat timi;
    private House testHouse;

    @Before
    public void setUp() {
        tobi = new Cat("Tobi");
        timi = new Cat("Timi");
        testHouse = new House("TestHouse", 10);
    }

    @Test
    public void test_CreateHouse_Success() {
        assertEquals("TestHouse", testHouse.getName());
        assertEquals(10, testHouse.getCapacity());
        assertEquals(0, testHouse.getCount());
    }

    @Test(expected = NullPointerException.class)
    public void test_CreateHouse_ShouldThrow_IfNameIs_Null() {
        House house = new House(null, 10);
    }

    @Test(expected = NullPointerException.class)
    public void test_CreateHouse_ShouldThrow_IfNameIs_WhiteSpace() {
        House house = new House("   ", 10);
    }

    @Test(expected = NullPointerException.class)
    public void test_CreateHouse_ShouldThrow_IfNameIs_Empty() {
        House house = new House("", 10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_CreateHouse_ShouldThrow_CapacityIs_Negative() {
        House house = new House("TestHouse", -10);
    }

    @Test
    public void test_GetCapacity_Success() {
        assertEquals(10, testHouse.getCapacity());
    }

    @Test
    public void test_Add_ShouldAdd_Success() {
        testHouse.addCat(timi);
        assertEquals(1, testHouse.getCount());
        testHouse.addCat(tobi);
        assertEquals(2, testHouse.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_Add_ShouldThrow_IfCapacityIs_Full() {
        House house = new House("Test", 1);
        house.addCat(tobi);
        house.addCat(timi);
    }

    @Test
    public void test_Remove_ShouldRemove_Success() {
        testHouse.addCat(timi);
        testHouse.removeCat(timi.getName());
        assertEquals(0, testHouse.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_Remove_ShouldThrow_IfNoSuchName() {
        testHouse.addCat(timi);
        testHouse.removeCat(tobi.getName());
        assertEquals(1, testHouse.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_ForSale_ShouldThrow_IfThereIsNO_SuchName() {
        testHouse.addCat(timi);
        testHouse.catForSale(tobi.getName());
    }

    @Test
    public void test_ForSale_Success() {
        testHouse.addCat(timi);
        testHouse.catForSale(timi.getName());
       assertFalse(timi.isHungry());
    }

    @Test
    public void test_GetStatistics_Success() {
        testHouse.addCat(timi);
        testHouse.addCat(tobi);
        String expected = "The cat Timi, Tobi is in the house TestHouse!";
        assertEquals(expected, testHouse.statistics());
    }
}
