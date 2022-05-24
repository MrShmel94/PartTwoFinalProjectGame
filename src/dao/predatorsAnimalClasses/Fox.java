package dao.predatorsAnimalClasses;

import dao.abstractClasses.PredatorAnimal;
import enums.EntityTypes;

import java.util.concurrent.atomic.AtomicInteger;

public class Fox extends PredatorAnimal {

    private static final EntityTypes kind = EntityTypes.FOX;
    private static final AtomicInteger countGenerateFox = new AtomicInteger(0);

    public Fox() {
        super(kind, kind.toString() + countGenerateFox);
        countGenerateFox.getAndIncrement();
    }

    public AtomicInteger getCountGenerateFox() {
        return countGenerateFox;
    }
}
