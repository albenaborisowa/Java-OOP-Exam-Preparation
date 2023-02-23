package blueOrigin;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SpaceshipTests {
    private Astronaut gosho;
    private Astronaut peter;
    private Spaceship testShip;

    @Before
    public void setUp() {
        gosho = new Astronaut("Gosho", 12.00);
        peter = new Astronaut("Peter", 10.00);
        testShip = new Spaceship("TestShip", 10);
    }

    @Test
    public void test_CreateShip_Success() {
        assertEquals("TestShip", testShip.getName());
        assertEquals(10, testShip.getCapacity());
        assertEquals(0, testShip.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_Add_ShouldThrow_When_CapacityIs_Full() {
        Spaceship spaceship = new Spaceship("Test", 1);
        spaceship.add(gosho);
        spaceship.add(peter);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_Add_ShouldThrow_When_DuplicateNames() {
        testShip.add(gosho);
        testShip.add(gosho);
    }

    @Test
    public void test_Add_Success() {
        testShip.add(gosho);
        assertEquals(1, testShip.getCount());
        testShip.add(peter);
        assertEquals(2, testShip.getCount());
    }

    @Test
    public void test_Remove_ShouldReturn_True() {
        testShip.add(gosho);
        assertTrue(testShip.remove(gosho.getName()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_CreateShip_ShouldThrow_When_CapacityIs_Negative() {
        Spaceship spaceship = new Spaceship("Test", -10);
    }

    @Test(expected = NullPointerException.class)
    public void test_CreateShip_ShouldThrow_When_NameIs_Null() {
        Spaceship spaceship = new Spaceship(null, 10);
    }

    @Test(expected = NullPointerException.class)
    public void test_CreateShip_ShouldThrow_When_NameIs_WhiteSpace() {
        Spaceship spaceship = new Spaceship("   ", 10);
    }

    @Test(expected = NullPointerException.class)
    public void test_CreateShip_ShouldThrow_When_NameIs_Empty() {
        Spaceship spaceship = new Spaceship("", 10);
    }
}
