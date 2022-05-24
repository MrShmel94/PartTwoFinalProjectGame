package dao.herbivoresAnimalsClasses;

import dao.abstractClasses.HerbivoresAnimal;
import enums.EntityTypes;

import java.util.concurrent.atomic.AtomicInteger;

public class Duck extends HerbivoresAnimal {

    private static final EntityTypes kind = EntityTypes.DUCK;
    private static final AtomicInteger countGenerateDuck = new AtomicInteger(0);

    public Duck() {
        super(kind, kind.toString() + countGenerateDuck);
        countGenerateDuck.getAndIncrement();
    }

    public AtomicInteger getCountGenerateDuck() {
        return countGenerateDuck;
    }
}
