package fairyShop.core;

import fairyShop.models.*;
import fairyShop.repositories.HelperRepository;
import fairyShop.repositories.PresentRepository;
import fairyShop.repositories.Repository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static fairyShop.common.ConstantMessages.*;
import static fairyShop.common.ExceptionMessages.*;

public class ControllerImpl implements Controller {
    private Repository<Helper> helperRepository;
    private Repository<Present> presentRepository;
    private Shop shop;

    public ControllerImpl() {
        this.helperRepository = new HelperRepository();
        this.presentRepository = new PresentRepository();
        this.shop = new ShopImpl();
    }

    @Override
    public String addHelper(String type, String helperName) {
        Helper helper;
        switch (type) {
            case "Happy":
                helper = new Happy(helperName);
                break;
            case "Sleepy":
                helper = new Sleepy(helperName);
                break;
            default:
                throw new IllegalArgumentException(HELPER_TYPE_DOESNT_EXIST);
        }
        helperRepository.add(helper);
        return String.format(ADDED_HELPER, type, helperName);
    }

    @Override
    public String addInstrumentToHelper(String helperName, int power) {
        Helper helper = helperRepository.findByName(helperName);
        if (helper == null) {
            throw new IllegalArgumentException(HELPER_DOESNT_EXIST);
        }
        Instrument instrument = new InstrumentImpl(power);
        helper.addInstrument(instrument);
        return String.format(SUCCESSFULLY_ADDED_INSTRUMENT_TO_HELPER, power, helperName);
    }

    @Override
    public String addPresent(String presentName, int energyRequired) {
        Present present = new PresentImpl(presentName, energyRequired);
        presentRepository.add(present);
        return String.format(SUCCESSFULLY_ADDED_PRESENT, presentName);
    }

    @Override
    public String craftPresent(String presentName) {
        List<Helper> helpersReady = this.helperRepository
                .getModels()
                .stream()
                .filter(helper -> helper.getEnergy() > 50)
                .collect(Collectors.toList());

        if (helpersReady.isEmpty()) {
            throw new IllegalArgumentException(NO_HELPER_READY);
        }

        Present present = this.presentRepository.findByName(presentName);
        Helper currentHelper = helpersReady.stream().iterator().next();
        shop.craft(present, currentHelper);
        long brokenInstruments = currentHelper
                .getInstruments()
                .stream()
                .filter(Instrument::isBroken)
                .count();

        String presentDone = present.isDone() ? "done" : "not done";
        return String.format(PRESENT_DONE, presentName, presentDone)
                + String.format(COUNT_BROKEN_INSTRUMENTS, brokenInstruments);
    }

    @Override
    public String report() {

        long donePresents = presentRepository
                .getModels()
                .stream()
                .filter(Present::isDone)
                .count();

        StringBuilder output = new StringBuilder();
        output.append(String.format("%d presents are done!", donePresents));
        output.append(System.lineSeparator());
        output.append("Helpers info:");

        Collection<Helper> helpers = helperRepository.getModels();

        for (Helper helper : helpers) {
            output.append(System.lineSeparator());
            output.append(String.format("Name: %s", helper.getName()));
            output.append(System.lineSeparator());
            output.append(String.format("Energy: %s", helper.getEnergy()));
            output.append(System.lineSeparator());
            long notBrokenInstruments = helper
                    .getInstruments()
                    .stream()
                    .filter(instrument -> !instrument.isBroken())
                    .count();
            output.append(String.format("Instruments: %d not broken left", notBrokenInstruments));
        }

        return output.toString();
    }
}
