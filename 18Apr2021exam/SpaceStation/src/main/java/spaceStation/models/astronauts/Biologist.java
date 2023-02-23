package spaceStation.models.astronauts;

public class Biologist extends BaseAstronaut {
    private static final double INITIAL_OXYGEN = 70;
    private static final double DECREASED_OXYGEN_PER_BREATH = 5;

    public Biologist(String name) {
        super(name, INITIAL_OXYGEN);
    }

    @Override
    public void breath() {
        setOxygen(Math.max(0, getOxygen() - DECREASED_OXYGEN_PER_BREATH));
    }
}
