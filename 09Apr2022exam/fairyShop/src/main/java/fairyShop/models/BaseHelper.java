package fairyShop.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import static fairyShop.common.ExceptionMessages.*;

public abstract class BaseHelper implements Helper {
    private static final int ENERGY_DECREASE_WHEN_WORK = 10;
    private String name;
    private int energy;
    private List<Instrument> instruments;

    protected BaseHelper(String name, int energy) {
        setName(name);
        this.energy = energy;
        this.instruments = new LinkedList<>();
    }

    private void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new NullPointerException(HELPER_NAME_NULL_OR_EMPTY);
        }
        this.name = name;
    }

    @Override
    public void work() {
        energy = Math.max(0, energy - ENERGY_DECREASE_WHEN_WORK);
    }

    @Override
    public void addInstrument(Instrument instrument) {
        instruments.add(instrument);
    }

    @Override
    public boolean canWork() {
        return energy > 0;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getEnergy() {
        return energy;
    }

    @Override
    public Collection<Instrument> getInstruments() {
        return instruments;
    }
}
