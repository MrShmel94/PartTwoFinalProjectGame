package dao.plantClasses;

import dao.abstractClasses.Plant;
import enums.EntityTypes;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

public class Berries extends Plant {

    private final static String name = null;
    private static final AtomicInteger countGenerateBerries = new AtomicInteger(0);

    public Berries() {
        super(EntityTypes.BERRIES, 3);
        this.setName(this.getType().name() + countGenerateBerries);
        countGenerateBerries.getAndIncrement();
        if (10 > ThreadLocalRandom.current().nextInt(1,101)){
            setPoison(true);
        }
    }


}
