package dao.herbivoresAnimalsClasses;

import dao.abstractClasses.HerbivoresAnimal;
import enums.EntityTypes;

import java.util.concurrent.atomic.AtomicInteger;

public class Rabbit extends HerbivoresAnimal {

    private static final EntityTypes kind = EntityTypes.RABBIT;
    private static final AtomicInteger countGenerateRabbit = new AtomicInteger(0);

    public Rabbit() {
        super(kind, kind.toString() + countGenerateRabbit);
        countGenerateRabbit.getAndIncrement();
    }

    public AtomicInteger getCountGenerateRabbit() {
        return countGenerateRabbit;
    }
}
