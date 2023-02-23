package christmasRaces.entities.races;

import christmasRaces.entities.drivers.Driver;

import java.util.ArrayList;
import java.util.Collection;

import static christmasRaces.common.ExceptionMessages.*;

public class RaceImpl implements Race {

    private String name;
    private int laps;
    private Collection<Driver> drivers;

    public RaceImpl(String name, int laps) {
        this.setName(name);
        this.setLaps(laps);
        drivers = new ArrayList<>();
    }

    private void setName(String name) {
        if (name == null || name.trim().length() < 5) {
            String exceptionMessage = String.format(INVALID_NAME, name, 5);
            throw new IllegalArgumentException(exceptionMessage);
        }
        this.name = name;
    }

    private void setLaps(int laps) {
        if (laps < 1) {
            String exceptionMessage = String.format(INVALID_NUMBER_OF_LAPS, 1);
            throw new IllegalArgumentException(exceptionMessage);
        }
        this.laps = laps;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getLaps() {
        return laps;
    }

    @Override
    public Collection<Driver> getDrivers() {
        return drivers;
    }

    @Override
    public void addDriver(Driver driver) {
        if (driver == null) {
            throw new IllegalArgumentException(DRIVER_INVALID);
        } else if (!driver.getCanParticipate()) {
            String exceptionMessage = String.format(DRIVER_NOT_PARTICIPATE, driver.getName());
            throw new IllegalArgumentException(exceptionMessage);
        } else if (drivers.contains(driver)) {
            String exceptionMessage = String.format(DRIVER_ALREADY_ADDED, driver.getName(), name);
            throw new IllegalArgumentException(exceptionMessage);
        }
        drivers.add(driver);
    }
}

