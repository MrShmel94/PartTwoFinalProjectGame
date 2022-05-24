package dao.predatorsAnimalClasses;

import dao.abstractClasses.PredatorAnimal;
import enums.EntityTypes;

import java.util.concurrent.atomic.AtomicInteger;

public class Eagle extends PredatorAnimal {

    private static final EntityTypes kind = EntityTypes.EAGLE;
    private static final AtomicInteger countGenerateEagle = new AtomicInteger(0);

    public Eagle() {
        super(kind, kind.toString() + countGenerateEagle);
        countGenerateEagle.getAndIncrement();
    }

    public AtomicInteger getCountGenerateEagle() {
        return countGenerateEagle;
    }
}
