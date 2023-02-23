package aquarium.entities.fish;

public class SaltwaterFish extends BaseFish {
    private static final int INITIAL_SIZE = 5;
    private static final int INCREASING_SIZE_WHEN_EAT = 2;

    public SaltwaterFish(String name, String species, double price) {
        super(name, species, price);
        setSize(INITIAL_SIZE);
    }

    @Override
    public void eat() {
        int newSize = getSize() + INCREASING_SIZE_WHEN_EAT;
        setSize(newSize);
    }
}
