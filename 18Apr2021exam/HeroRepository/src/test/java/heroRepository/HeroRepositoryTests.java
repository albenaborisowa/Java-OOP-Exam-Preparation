package heroRepository;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class HeroRepositoryTests {
    private Hero merlin;
    private Hero arthur;
    private HeroRepository testRepository;

    @Before
    public void setUp() {
        merlin = new Hero("Merlin", 13);
        arthur = new Hero("Arthur", 10);
        testRepository = new HeroRepository();
    }

    @Test
    public void test_GetCount_Success() {
        testRepository.create(arthur);
        assertEquals(1, testRepository.getCount());
    }

    @Test(expected = NullPointerException.class)
    public void test_CreateHero_ShouldThrow_IfNameIs_Null() {
        testRepository.create(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_CreateHero_ShouldThrow_IfSuchName_AlreadyExists() {
        testRepository.create(merlin);
        testRepository.create(merlin);
    }

    @Test
    public void test_SuccessfulAddHero_ShouldReturn_CorrectMessage() {
        Hero hero = new Hero("testHero", 8);
        String expected = "Successfully added hero testHero with level 8";
        String actual = testRepository.create(hero);
        assertEquals(expected, actual);
    }

    @Test(expected = NullPointerException.class)
    public void test_Remove_ShouldThrow_WhenNameIs_Null() {
        testRepository.remove(null);
    }

    @Test(expected = NullPointerException.class)
    public void test_Remove_ShouldThrow_WhenNameIs_WhiteSpace() {
        testRepository.remove("   ");
    }

    @Test(expected = NullPointerException.class)
    public void test_Remove_ShouldThrow_WhenNameIs_Empty() {
        testRepository.remove("");
    }

    @Test
    public void test_Remove_Success_When_NamesAre_Equals() {
        testRepository.create(merlin);
        testRepository.remove("Merlin");
        assertEquals(0, testRepository.getCount());
    }

    @Test
    public void test_getHeroWithHighestLevel_Success() {
        testRepository.create(merlin);
        testRepository.create(arthur);
        assertEquals(merlin, testRepository.getHeroWithHighestLevel());
    }

    @Test
    public void test_GetHero_ShouldReturn_HeroWith_CorrectName() {
        testRepository.create(merlin);
        assertEquals(merlin, testRepository.getHero(merlin.getName()));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void test_GetHeroes_ShouldReturn_UnmodifiableCollection() {
        testRepository.getHeroes().clear();
    }

}
