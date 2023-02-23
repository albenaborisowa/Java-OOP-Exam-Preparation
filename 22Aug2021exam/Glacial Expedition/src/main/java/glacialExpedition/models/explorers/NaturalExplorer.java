package glacialExpedition.models.explorers;

public class NaturalExplorer extends BaseExplorer {

    private static final double NATURAL_EXPLORER_STARTING_ENERGY = 60.00;
    private static final double NATURAL_EXPLORER_SEARCH_ENERGY = 7.00;

    public NaturalExplorer(String name) {
        super(name, NATURAL_EXPLORER_STARTING_ENERGY);
    }

    @Override
    public void search() {
        setEnergy((Math.max(0, getEnergy() - NATURAL_EXPLORER_SEARCH_ENERGY)));
    }
}
