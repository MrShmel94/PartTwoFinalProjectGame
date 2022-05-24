package dao.herbivoresAnimalsClasses;

import dao.abstractClasses.HerbivoresAnimal;
import enums.EntityTypes;

import java.util.concurrent.atomic.AtomicInteger;

public class Hamster extends HerbivoresAnimal {

    private static final EntityTypes kind = EntityTypes.HAMSTER;
    private static final AtomicInteger countGenerateHamster = new AtomicInteger(0);

    public Hamster() {
        super(kind, kind.toString() + countGenerateHamster);
        countGenerateHamster.getAndIncrement();
    }

    public AtomicInteger getCountGenerateHamster() {
        return countGenerateHamster;
    }

}
