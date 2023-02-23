package computers;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ComputerManagerTests {
    private Computer comp1;
    private Computer comp2;
    private ComputerManager testManager;

    @Before
    public void setUp() {
        comp1 = new Computer("Apple", "XX", 1230.30);
        comp2 = new Computer("Exo", "XXX", 1330.30);
        testManager = new ComputerManager();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void test_getComputers_ShouldReturn_UnmodifiableCollection() {
        testManager.getComputers().clear();
    }

    @Test
    public void test_GetCount_ShouldReturn_CorrectNumber() {
        assertEquals(0, testManager.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_AddComputer_ShouldThrow_WhenComputer_AlreadyExists() {
        testManager.addComputer(comp1);
        testManager.addComputer(comp1);
    }

    @Test
    public void test_AddComputer_Success() {
        testManager.addComputer(comp1);
        assertEquals(1, testManager.getCount());
        testManager.addComputer(comp2);
        assertEquals(2, testManager.getCount());
    }

    @Test
    public void test_RemoveComputer_Success() {
        testManager.addComputer(comp1);
        testManager.removeComputer(comp1.getManufacturer(), comp1.getModel());
        assertEquals(0, testManager.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_GetComputer_ShouldThrow_When_ManufacturerIs_Null() {
        testManager.getComputer(null, "XXX");
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_GetComputer_ShouldThrow_When_ModelIs_Null() {
        testManager.getComputer("Test", null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_GetComputer_ShouldThrow_When_NoSuch_Manufacturer(){
        testManager.addComputer(comp1);
        testManager.getComputer("Test", "XX");
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_GetComputer_ShouldThrow_When_NoSuch_Model(){
        testManager.addComputer(comp1);
        testManager.getComputer("Apple", "XXXX");
    }

    @Test
    public void test_GetComputersByManufacturer_Success(){
        ComputerManager computerManager = new ComputerManager();
        computerManager.addComputer(comp1);
        computerManager.addComputer(comp2);
        testManager.addComputer(comp1);
        assertEquals(testManager.getComputers(), computerManager.getComputersByManufacturer("Apple"));
    }
}