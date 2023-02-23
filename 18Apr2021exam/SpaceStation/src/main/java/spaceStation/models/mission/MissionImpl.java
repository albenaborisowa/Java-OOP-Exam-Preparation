package spaceStation.models.mission;

import spaceStation.models.astronauts.Astronaut;
import spaceStation.models.planets.Planet;

import java.util.Collection;

public class MissionImpl implements Mission {
    @Override
    public void explore(Planet planet, Collection<Astronaut> astronauts) {
        Collection<String> items = planet.getItems();

        for (Astronaut currentAstronaut : astronauts) {
            while (currentAstronaut.canBreath() && items.iterator().hasNext()) {
                currentAstronaut.breath();
                String currentItem = items.iterator().next();
                currentAstronaut.getBag().getItems().add(currentItem);
                items.remove(currentItem);
            }
        }
    }
}
