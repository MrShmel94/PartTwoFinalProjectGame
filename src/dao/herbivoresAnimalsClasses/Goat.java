package dao.herbivoresAnimalsClasses;

import dao.abstractClasses.HerbivoresAnimal;
import enums.EntityTypes;

import java.util.concurrent.atomic.AtomicInteger;

public class Goat extends HerbivoresAnimal {

    private static final EntityTypes kind = EntityTypes.GOAT;
    private static final AtomicInteger countGenerateGoat = new AtomicInteger(0);

    public Goat() {
        super(kind, kind.toString() + countGenerateGoat);
        countGenerateGoat.getAndIncrement();
    }

    public AtomicInteger getCountGenerateGoat() {
        return countGenerateGoat;
    }
}
