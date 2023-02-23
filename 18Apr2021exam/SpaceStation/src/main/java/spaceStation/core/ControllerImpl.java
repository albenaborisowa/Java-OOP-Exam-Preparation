package spaceStation.core;

import spaceStation.models.astronauts.Astronaut;
import spaceStation.models.astronauts.Biologist;
import spaceStation.models.astronauts.Geodesist;
import spaceStation.models.astronauts.Meteorologist;
import spaceStation.models.mission.Mission;
import spaceStation.models.mission.MissionImpl;
import spaceStation.models.planets.Planet;
import spaceStation.models.planets.PlanetImpl;
import spaceStation.repositories.AstronautRepository;
import spaceStation.repositories.PlanetRepository;
import spaceStation.repositories.Repository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static spaceStation.common.ConstantMessages.*;
import static spaceStation.common.ExceptionMessages.*;

public class ControllerImpl implements Controller {
    private Repository<Astronaut> astronautRepository;
    private Repository<Planet> planetRepository;
    private int exploredPlanets;

    public ControllerImpl() {
        this.astronautRepository = new AstronautRepository();
        this.planetRepository = new PlanetRepository();
    }

    @Override
    public String addAstronaut(String type, String astronautName) {
        Astronaut astronaut;
        switch (type) {
            case "Biologist":
                astronaut = new Biologist(astronautName);
                break;
            case "Geodesist":
                astronaut = new Geodesist(astronautName);
                break;
            case "Meteorologist":
                astronaut = new Meteorologist(astronautName);
                break;
            default:
                throw new IllegalArgumentException(ASTRONAUT_INVALID_TYPE);
        }
        astronautRepository.add(astronaut);
        return String.format(ASTRONAUT_ADDED, type, astronautName);
    }

    @Override
    public String addPlanet(String planetName, String... items) {
        Planet planet = new PlanetImpl(planetName);
        for (String item : items) {
            planet.getItems().add(item);
        }
        planetRepository.add(planet);
        return String.format(PLANET_ADDED, planetName);
    }

    @Override
    public String retireAstronaut(String astronautName) {
        Astronaut astronaut = astronautRepository.findByName(astronautName);
        if (astronaut == null) {
            throw new IllegalArgumentException(String.format(ASTRONAUT_DOES_NOT_EXIST, astronautName));
        }
        astronautRepository.remove(astronaut);
        return String.format(ASTRONAUT_RETIRED, astronautName);
    }

    @Override
    public String explorePlanet(String planetName) {
        List<Astronaut> astronautsReady = astronautRepository
                .getModels()
                .stream()
                .filter(astronaut -> astronaut.getOxygen() > 60)
                .collect(Collectors.toList());

        if (astronautsReady.isEmpty()) {
            throw new IllegalArgumentException(PLANET_ASTRONAUTS_DOES_NOT_EXISTS);
        }

        Planet planetToExplore = planetRepository.findByName(planetName);
        Mission mission = new MissionImpl();
        mission.explore(planetToExplore, astronautsReady);
        long deadAstronauts = astronautsReady
                .stream()
                .filter(astronaut -> astronaut.getOxygen() == 0)
                .count();
        exploredPlanets++;
        return String.format(PLANET_EXPLORED, planetName, deadAstronauts);
    }

    @Override
    public String report() {
        StringBuilder output = new StringBuilder();

        output.append(String.format(REPORT_PLANET_EXPLORED, exploredPlanets));
        output.append(System.lineSeparator());
        output.append(REPORT_ASTRONAUT_INFO);

        Collection<Astronaut> astronauts = astronautRepository.getModels();
        for (Astronaut astronaut : astronauts) {
            output.append(System.lineSeparator());
            output.append(String.format(REPORT_ASTRONAUT_NAME, astronaut.getName()));
            output.append(System.lineSeparator());
            output.append(String.format(REPORT_ASTRONAUT_OXYGEN, astronaut.getOxygen()));
            output.append(System.lineSeparator());
            if (astronaut.getBag().getItems().isEmpty()) {
                output.append(String.format(REPORT_ASTRONAUT_BAG_ITEMS, "none"));
            } else {
                output.append(String.format(REPORT_ASTRONAUT_BAG_ITEMS,
                        String.join(REPORT_ASTRONAUT_BAG_ITEMS_DELIMITER,
                                astronaut.getBag().getItems())));
            }
        }
        return output.toString();
    }
}
