package dao.plantClasses;

import dao.abstractClasses.Plant;
import enums.EntityTypes;

import java.util.concurrent.atomic.AtomicInteger;

public class Plants extends Plant {

    private final static String name = null;
    private static final AtomicInteger countGeneratePlant = new AtomicInteger(0);

    public Plants() {
        super(EntityTypes.GRASS, 1);
        this.setName(this.getType().name() + countGeneratePlant);
        countGeneratePlant.getAndIncrement();
    }
}
