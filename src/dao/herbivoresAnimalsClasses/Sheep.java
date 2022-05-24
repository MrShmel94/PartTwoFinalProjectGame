package dao.herbivoresAnimalsClasses;

import dao.abstractClasses.HerbivoresAnimal;
import enums.EntityTypes;

import java.util.concurrent.atomic.AtomicInteger;

public class Sheep extends HerbivoresAnimal {

    private static final EntityTypes kind = EntityTypes.SHEEP;
    private static final AtomicInteger countGenerateSheep = new AtomicInteger(0);

    public Sheep() {
        super(kind, kind.toString() + countGenerateSheep);
        countGenerateSheep.getAndIncrement();
    }

    public AtomicInteger getCountGenerateSheep() {
        return countGenerateSheep;
    }
}
