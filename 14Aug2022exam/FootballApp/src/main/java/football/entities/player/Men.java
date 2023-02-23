package football.entities.player;

public class Men extends BasePlayer {
    private static final double MEN_INITIAL_KGS = 85.50;
    private static final int MEN_INCREASE_INITIAL_STRENGTH = 145;

    public Men(String name, String nationality, int strength) {
        super(name, nationality, MEN_INITIAL_KGS, strength);
    }

    @Override
    public void stimulation() {
        setStrength(getStrength() + MEN_INCREASE_INITIAL_STRENGTH);
    }

    // I can only play on NaturalGrass!
}
