package glacialExpedition.models.mission;

import glacialExpedition.models.explorers.Explorer;
import glacialExpedition.models.states.State;

import java.util.Collection;

public class MissionImpl implements Mission {

    @Override
    public void explore(State state, Collection<Explorer> explorers) {
        //Итерираме изследователите (Вземаме ги един по един)
        //Итерираме експонатите на конкретната локация - започват да намират експонати
        //Това продължава докато има експонати и докато изследователите имат енергия
        //Ако свършат експонатите - свършва експедицията
        //Ако свърши енергията, вземаме следващия изследовател
        Collection<String> stateExhibits = state.getExhibits();
        for (Explorer currentExplorer : explorers) {
            while (currentExplorer.canSearch() && stateExhibits.iterator().hasNext()) {
                currentExplorer.search();
                String currentExhibit = stateExhibits.iterator().next();
                currentExplorer.getSuitcase().getExhibits().add(currentExhibit);
                stateExhibits.remove(currentExhibit);
            }
        }
    }
}
