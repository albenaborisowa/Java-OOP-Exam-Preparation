package fairyShop.models;

import static fairyShop.common.ExceptionMessages.*;

public class InstrumentImpl implements Instrument {
    private static final int POWER_DECREASE_PER_USE = 10;
    private int power;

    public InstrumentImpl(int power) {
        setPower(power);
    }

    private void setPower(int power) {
        if (power < 0) {
            throw new IllegalArgumentException(INSTRUMENT_POWER_LESS_THAN_ZERO);
        }
        this.power = power;
    }

    @Override
    public int getPower() {
        return power;
    }

    @Override
    public void use() {
        power = Math.max(0, power - POWER_DECREASE_PER_USE);
    }

    @Override
    public boolean isBroken() {
        return power == 0;
    }
}
