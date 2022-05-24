package dao.herbivoresAnimalsClasses;

import dao.abstractClasses.HerbivoresAnimal;
import enums.EntityTypes;

import java.util.concurrent.atomic.AtomicInteger;

public class Deer extends HerbivoresAnimal {

    private static final EntityTypes kind = EntityTypes.DEER;
    private static final AtomicInteger countGenerateDeer = new AtomicInteger(0);

    public Deer() {
        super(kind, kind.toString() + countGenerateDeer);
        countGenerateDeer.getAndIncrement();
    }

    public AtomicInteger getCountGenerateDeer() {
        return countGenerateDeer;
    }
}
