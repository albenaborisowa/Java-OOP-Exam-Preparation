package football.entities.supplement;

public class Powdered extends BaseSupplement {
    private static final int POWERED_ENERGY = 120;
    private static final double POWERED_PRICE = 15.00;

    public Powdered() {
        super(POWERED_ENERGY, POWERED_PRICE);
    }
}
