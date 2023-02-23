package football;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FootballTeamTests {
    private FootballTeam testFootballTeam;
    private Footballer peter;
    private Footballer gosho;

    @Before
    public void setUp() {
        testFootballTeam = new FootballTeam("SoftUni", 10);
        peter = new Footballer("Peter");
        gosho = new Footballer("Gosho");
    }

    @Test
    public void test_Create_FootballTeam_Success() {
        assertEquals("SoftUni", testFootballTeam.getName());
        assertEquals(10, testFootballTeam.getVacantPositions());
        assertEquals(0, testFootballTeam.getCount());
    }

    @Test(expected = NullPointerException.class)
    public void test_Create_ShouldThrow_When_NameIs_Null() {
        FootballTeam footballTeam = new FootballTeam(null, 10);
    }

    @Test(expected = NullPointerException.class)
    public void test_Create_ShouldThrow_When_NameIs_WhiteSpace() {
        FootballTeam footballTeam = new FootballTeam("    ", 10);
    }

    @Test(expected = NullPointerException.class)
    public void test_Create_ShouldThrow_When_NameIs_Empty() {
        FootballTeam footballTeam = new FootballTeam("", 10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_Create_ShouldThrow_When_VacantPositionIs_Negative() {
        FootballTeam footballTeam = new FootballTeam("SoftUni", -10);
    }

    @Test
    public void test_Add_ShouldAdd_Success() {
        testFootballTeam.addFootballer(peter);
        assertEquals(1, testFootballTeam.getCount());
        testFootballTeam.addFootballer(gosho);
        assertEquals(2, testFootballTeam.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_Add_ShouldThrow_When_VacantPositionsIs_Full() {
        FootballTeam footballTeam = new FootballTeam("SoftUni", 1);
        footballTeam.addFootballer(peter);
        footballTeam.addFootballer(gosho);
    }

    @Test
    public void test_Remove_ShouldRemove_Success() {
        testFootballTeam.addFootballer(peter);
        testFootballTeam.removeFootballer(peter.getName());
        assertEquals(0, testFootballTeam.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_Remove_ShouldNotRemove() {
        testFootballTeam.addFootballer(peter);
        testFootballTeam.removeFootballer(gosho.getName());
        assertEquals(1, testFootballTeam.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_ForSale_ShouldThrow_When_ThereIsNO_SuchFootballer() {
        testFootballTeam.addFootballer(peter);
        testFootballTeam.footballerForSale(gosho.getName());
    }

    @Test
    public void test_ForSale_Success() {
        testFootballTeam.addFootballer(peter);
        testFootballTeam.footballerForSale(peter.getName());
        assertFalse(peter.isActive());
    }

    @Test
    public void test_GetStatistics_Success(){
        FootballTeam footballTeam = new FootballTeam("SoftUni", 2);
        footballTeam.addFootballer(peter);
        footballTeam.addFootballer(gosho);

        String expected = "The footballer Peter, Gosho is in the team SoftUni.";
        assertEquals(expected, footballTeam.getStatistics());
    }

}
