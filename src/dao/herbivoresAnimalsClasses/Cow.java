package dao.herbivoresAnimalsClasses;

import dao.abstractClasses.HerbivoresAnimal;
import enums.EntityTypes;

import java.util.concurrent.atomic.AtomicInteger;

public class Cow extends HerbivoresAnimal {

    private static final EntityTypes kind = EntityTypes.BUFFALO;
    private static final AtomicInteger countGenerateCow = new AtomicInteger(0);

    public Cow() {
        super(kind, kind.toString() + countGenerateCow);
        countGenerateCow.getAndIncrement();
    }

    public AtomicInteger getCountGenerateCow() {
        return countGenerateCow;
    }
}
