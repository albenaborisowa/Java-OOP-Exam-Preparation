package farmville;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class FarmvilleTests {

    private Animal lion;
    private Animal chicken;
    private Farm testFarm;

    @Before
    public void setUp() {
        lion = new Animal("lion", 10.00);
        chicken = new Animal("chicken", 1.00);
        testFarm = new Farm("SoftUni", 15);
    }

    @Test
    public void test_ShouldCreate_Farm_Success() {
        Farm farm = new Farm("SoftUni", 15);
        assertEquals("SoftUni", farm.getName());
        assertEquals(15, farm.getCapacity());
        assertEquals(0, farm.getCount());
    }

    @Test(expected = NullPointerException.class)
    public void test_Create_ShouldThrow_When_NameIs_Null() {
        new Farm(null, 15);
    }

    @Test(expected = NullPointerException.class)
    public void test_Create_ShouldThrow_When_NameIs_WhiteSpace() {
        new Farm("    ", 15);
    }

    @Test(expected = NullPointerException.class)
    public void test_Create_ShouldThrow_When_NameIs_Empty() {
        new Farm("", 15);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_Create_ShouldThrow_When_CapacityIs_Negative() {
        new Farm("SoftUni", -15);
    }
    // Тестове за add метода:
    //1. Add success
    //2. Add exception -> capacity too low (2, 2)
    //3. Add exception -> animal exists

    @Test
    public void test_Add_ShouldAdd_Success() {
        testFarm.add(lion);
        assertEquals(1, testFarm.getCount());
        testFarm.add(chicken);
        assertEquals(2, testFarm.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_Add_ShouldThrow_When_NoCapacity() {
        Farm farm = new Farm("SoftUni", 1);
        farm.add(lion);
        farm.add(chicken);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_Add_ShouldThrow_When_Animal_AlreadyExists() {
        testFarm.add(lion);
        testFarm.add(lion);
    }

    // Тестове за remove метода
    //1. remove -> don't remove and return false
    //2. remove - remove and return true

    @Test
    public void test_Remove_ShouldNot_Remove(){
        testFarm.add(lion);
        assertFalse(testFarm.remove(chicken.getType()));
        assertEquals(1, testFarm.getCount());
    }

    @Test
    public void test_Remove_Should_Remove(){
        testFarm.add(lion);
        assertTrue(testFarm.remove(lion.getType()));
        assertEquals(0, testFarm.getCount());
    }
}
