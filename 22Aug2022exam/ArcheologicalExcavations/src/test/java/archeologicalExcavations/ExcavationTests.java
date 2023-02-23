package archeologicalExcavations;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ExcavationTests {

    private Archaeologist peter;
    private Archaeologist gosho;
    private Excavation testExcavation;

    @Before
    public void setUp() {
        peter = new Archaeologist("Peter", 2.00);
        gosho = new Archaeologist("Gosho", 3.00);
        testExcavation = new Excavation("SoftUni", 10);
    }

    @Test
    public void test_Create_ShouldCreate_Excavation_Success() {
        assertEquals("SoftUni", testExcavation.getName());
        assertEquals(10, testExcavation.getCapacity());
        assertEquals(0, testExcavation.getCount());
    }

    @Test(expected = NullPointerException.class)
    public void test_Create_ShouldThrow_When_NameIs_Null() {
        Excavation excavation = new Excavation(null, 10);
    }

    @Test(expected = NullPointerException.class)
    public void test_Create_ShouldThrow_When_NameIs_WhiteSpace() {
        Excavation excavation = new Excavation("    ", 10);
    }

    @Test(expected = NullPointerException.class)
    public void test_Create_ShouldThrow_When_NameIs_Empty() {
        Excavation excavation = new Excavation("", 10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_Create_ShouldThrow_When_CapacityIs_Negative() {
        Excavation excavation = new Excavation("SoftUni", -10);
    }

    @Test
    public void test_Add_ShouldAdd_Success() {
        testExcavation.addArchaeologist(peter);
        assertEquals(1, testExcavation.getCount());
        testExcavation.addArchaeologist(gosho);
        assertEquals(2, testExcavation.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_Add_ShouldThrow_When_NoCapacity() {
        Excavation excavation = new Excavation("SoftUni", 1);
        excavation.addArchaeologist(peter);
        excavation.addArchaeologist(gosho);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_Add_ShouldThrow_When_Duplicate_Archeologist() {
        testExcavation.addArchaeologist(gosho);
        testExcavation.addArchaeologist(gosho);
    }

    @Test
    public void test_Remove_ShouldRemove_Success() {
        testExcavation.addArchaeologist(gosho);
        assertTrue(testExcavation.removeArchaeologist(gosho.getName()));
        assertEquals(0, testExcavation.getCount());
    }

    @Test
    public void test_Remove_ShouldNot_Remove() {
        testExcavation.addArchaeologist(gosho);
        assertFalse(testExcavation.removeArchaeologist(peter.getName()));
        assertEquals(1, testExcavation.getCount());
    }
}
