package dao.herbivoresAnimalsClasses;

import dao.abstractClasses.HerbivoresAnimal;
import enums.EntityTypes;

import java.util.concurrent.atomic.AtomicInteger;

public class Caterpillar extends HerbivoresAnimal {

    private static final EntityTypes kind = EntityTypes.CATERPILLAR;
    private static final AtomicInteger countGenerateCaterpillar = new AtomicInteger(0);

    public Caterpillar() {
        super(kind, kind.name() + countGenerateCaterpillar);
        countGenerateCaterpillar.getAndIncrement();
    }

    public AtomicInteger getCountGenerateCaterpillar() {
        return countGenerateCaterpillar;
    }
}
