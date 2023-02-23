package aquarium;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class AquariumTests {
    private Fish golden;
    private Fish shark;
    private Aquarium testAquarium;

    @Before
    public void setUp() {
        golden = new Fish("Golden");
        shark = new Fish("Shark");
        testAquarium = new Aquarium("TestAquarium", 10);
    }

    @Test
    public void test_CreateAquarium_Success() {
        assertEquals("TestAquarium", testAquarium.getName());
        assertEquals(10, testAquarium.getCapacity());
        assertEquals(0, testAquarium.getCount());
    }

    @Test(expected = NullPointerException.class)
    public void test_Create_ShouldThrow_WhenNameIs_Null() {
        Aquarium aquarium = new Aquarium(null, 10);
    }

    @Test(expected = NullPointerException.class)
    public void test_Create_ShouldThrow_WhenNameIs_WhiteSpace() {
        Aquarium aquarium = new Aquarium("   ", 10);
    }

    @Test(expected = NullPointerException.class)
    public void test_Create_ShouldThrow_WhenNameIs_Empty() {
        Aquarium aquarium = new Aquarium("", 10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_Create_ShouldThrow_WhenCapacityIs_negative() {
        Aquarium aquarium = new Aquarium("Test", -10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_AddFish_ShouldThrow_WhenCapacityIs_Full() {
        Aquarium aquarium = new Aquarium("Test", 1);
        aquarium.add(golden);
        aquarium.add(shark);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_Remove_ShouldThrow_WhenNull() {
        testAquarium.remove(null);
    }

    @Test
    public void test_Remove_WhenNamesAre_Equals() {
        testAquarium.add(golden);
        testAquarium.remove(golden.getName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_SellFish_ShouldThrow_When_NoFish_With_SuchName() {
        testAquarium.sellFish(golden.getName());
    }

    @Test
    public void test_SellFish_Success() {
        testAquarium.add(golden);
        testAquarium.sellFish(golden.getName());
        assertFalse(golden.isAvailable());
    }

    @Test
    public void test_Report_ShouldReturn_CorrectMessage() {
        testAquarium.add(golden);
        testAquarium.add(shark);

        String expected = "Fish available at TestAquarium: Golden, Shark";
        assertEquals(expected, testAquarium.report());
    }
}

