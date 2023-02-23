package football.entities.player;

public class Women extends BasePlayer {
    private static final double WOMEN_INITIAL_KGS = 60.00;
    private static final int WOMEN_INCREASE_INITIAL_STRENGTH = 115;

    public Women(String name, String nationality, int strength) {
        super(name, nationality, WOMEN_INITIAL_KGS, strength);
    }

    @Override
    public void stimulation() {
        setStrength(getStrength() + WOMEN_INCREASE_INITIAL_STRENGTH);
    }

    // I can only play on ArtificialTurf!
}
