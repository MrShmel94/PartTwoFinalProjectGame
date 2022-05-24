package dao.predatorsAnimalClasses;

import dao.abstractClasses.PredatorAnimal;
import enums.EntityTypes;

import java.util.concurrent.atomic.AtomicInteger;

public class Wolf extends PredatorAnimal {

    private static final EntityTypes kind = EntityTypes.WOLF;
    private static final AtomicInteger countGenerateWolf = new AtomicInteger(0);

    public Wolf() {
        super(kind, kind.toString() + countGenerateWolf);
        countGenerateWolf.getAndIncrement();
    }

    public AtomicInteger getCountGenerateWolf() {
        return countGenerateWolf;
    }
}
