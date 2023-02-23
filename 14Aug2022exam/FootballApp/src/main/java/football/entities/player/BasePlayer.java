package football.entities.player;

import static football.common.ExceptionMessages.*;
import static football.util.StringUtils.nullOrEmpty;

public abstract class BasePlayer implements Player {
    private String name;
    private String nationality;
    private double kg;
    private int strength;

    protected BasePlayer(String name, String nationality, double kg, int strength) {
        setName(name);
        setNationality(nationality);
        this.kg = kg;
        setStrength(strength);
    }

    @Override
    public void setName(String name) {
        nullOrEmpty(name, PLAYER_NAME_NULL_OR_EMPTY);
        this.name = name;
    }

    private void setNationality(String nationality) {
        nullOrEmpty(nationality, PLAYER_NATIONALITY_NULL_OR_EMPTY);
        this.nationality = nationality;
    }

    protected void setStrength(int strength) {
        if (strength <= 0) {
            throw new IllegalArgumentException(PLAYER_STRENGTH_BELOW_OR_EQUAL_ZERO);
        }
        this.strength = strength;
    }

    @Override
    public double getKg() {
        return kg;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getStrength() {
        return strength;
    }
}
