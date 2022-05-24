package dao.predatorsAnimalClasses;

import dao.abstractClasses.PredatorAnimal;
import enums.EntityTypes;

import java.util.concurrent.atomic.AtomicInteger;

public class Bear extends PredatorAnimal {

    private static final EntityTypes kind = EntityTypes.BEAR;
    private static final AtomicInteger countGenerateBear = new AtomicInteger(0);

    public Bear() {
        super(kind, kind.toString() + countGenerateBear);
        countGenerateBear.getAndIncrement();
    }

    public AtomicInteger getCountGenerateBear() {
        return countGenerateBear;
    }
}
