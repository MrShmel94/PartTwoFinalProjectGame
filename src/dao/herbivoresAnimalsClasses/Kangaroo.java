package dao.herbivoresAnimalsClasses;

import dao.abstractClasses.HerbivoresAnimal;
import enums.EntityTypes;

import java.util.concurrent.atomic.AtomicInteger;

public class Kangaroo extends HerbivoresAnimal {

    private static final EntityTypes kind = EntityTypes.BOAR;
    private static final AtomicInteger countGenerateKangaroo = new AtomicInteger(0);

    public Kangaroo() {
        super(kind, kind.toString() + countGenerateKangaroo);
        countGenerateKangaroo.getAndIncrement();
    }

    public AtomicInteger getCountGenerateKangaroo() {
        return countGenerateKangaroo;
    }
}
