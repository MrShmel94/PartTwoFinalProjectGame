package dao.herbivoresAnimalsClasses;

import dao.abstractClasses.HerbivoresAnimal;
import enums.EntityTypes;

import java.util.concurrent.atomic.AtomicInteger;

public class Horse extends HerbivoresAnimal {

    private static final EntityTypes kind = EntityTypes.HORSE;
    private static final AtomicInteger countGenerateHorse = new AtomicInteger(0);

    public Horse() {
        super(kind, kind.toString() + countGenerateHorse);
        countGenerateHorse.getAndIncrement();
    }

    public AtomicInteger getCountGenerateHorse() {
        return countGenerateHorse;
    }
}
