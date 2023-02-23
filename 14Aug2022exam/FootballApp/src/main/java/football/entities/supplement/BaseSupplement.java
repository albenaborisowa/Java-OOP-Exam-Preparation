package football.entities.supplement;

public abstract class BaseSupplement implements Supplement {
    private int energy;
    private double price;

    protected BaseSupplement(int energy, double price) {
        setEnergy(energy);
        setPrice(price);
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public int getEnergy() {
        return energy;
    }

    @Override
    public double getPrice() {
        return price;
    }
}
