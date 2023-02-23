package petStore;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class PetStoreTests {
    private static final String SPECIE = "Dog";
    private static final int MAX_WEIGHT = 30;
    private static final double PRICE = 100.00;

    private PetStore petStore;
    private Animal animal;

    @Before
    public void setup() {
        this.petStore = new PetStore();
        this.animal = new Animal(SPECIE, MAX_WEIGHT, PRICE);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void test_GetAnimals_ShouldReturn_UnmodifiableList() {
        List<Animal> animals = petStore.getAnimals();
        animals.remove(1);
    }

    @Test
    public void test_GetCount_ShouldReturn_Zero_WhenEmpty_And_One_When_SingleAnimalAdded() {
        assertEquals(0, petStore.getCount());
        petStore.addAnimal(animal);
        assertEquals(1, petStore.getCount());
    }

    @Test
    public void test_findAllAnimals_WithMaxKilograms_ShouldReturn_EmptyList_When_NoSuchAnimals() {
        petStore.addAnimal(animal);
        List<Animal> animals = petStore.findAllAnimalsWithMaxKilograms(MAX_WEIGHT + 10);
        assertTrue(animals.isEmpty());
    }

    @Test
    public void test_findAllAnimals_WithMaxKilograms_ShouldReturn_OnlyThoseHeavier() {
        petStore.addAnimal(animal);
        petStore.addAnimal(new Animal("test", MAX_WEIGHT - 2, PRICE));
        List<Animal> animals = petStore.findAllAnimalsWithMaxKilograms(MAX_WEIGHT - 1);
        assertEquals(1, animals.size());
        assertEquals(animal.getSpecie(), animals.get(0).getSpecie());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_AddAnimal_ShouldThrow_WhenAnimal_Is_Null() {
        petStore.addAnimal(null);
    }

    @Test
    public void test_AddAnimal_ShouldIncrease_Count() {
        petStore.addAnimal(animal);
        assertEquals(1, petStore.getCount());
    }

    @Test
    public void test_GetTheMostExpensiveAnimal_ShouldReturn_Null_WhenEmpty() {
        Animal animal = petStore.getTheMostExpensiveAnimal();
        assertNull(animal);
    }

    @Test
    public void test_GetTheMostExpensiveAnimal_ShouldReturn_TheMostExpensiveOne() {
        petStore.addAnimal(animal);
        petStore.addAnimal(new Animal(SPECIE, MAX_WEIGHT, PRICE - 10));
        Animal actualAnimal = petStore.getTheMostExpensiveAnimal();
        assertEquals(animal.getPrice(), actualAnimal.getPrice(), 0.00);
    }

    @Test
    public void test_findAllAnimalBySpecie_ShouldReturn_EmptyList_When_NoAnimals() {
        List<Animal> animals = petStore.findAllAnimalBySpecie(SPECIE);
        assertTrue(animals.isEmpty());
    }

    @Test
    public void test_findAllAnimalBySpecie_ShouldReturn_OnlyTheRequired_SPECIE() {
        petStore.addAnimal(animal);
        petStore.addAnimal(new Animal("Goat", MAX_WEIGHT, PRICE));
        List<Animal> animals = petStore.findAllAnimalBySpecie(SPECIE);
        assertEquals(1, animals.size());
        assertEquals(SPECIE, animals.get(0).getSpecie());
    }
}

