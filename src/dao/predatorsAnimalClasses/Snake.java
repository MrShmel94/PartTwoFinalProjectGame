package dao.predatorsAnimalClasses;

import dao.abstractClasses.PredatorAnimal;
import enums.EntityTypes;

import java.util.concurrent.atomic.AtomicInteger;

public class Snake extends PredatorAnimal {

    private static final EntityTypes kind = EntityTypes.SNAKE;
    private static final AtomicInteger countGenerateSnake = new AtomicInteger(0);

    public Snake() {
        super(kind, kind.toString() + countGenerateSnake);
        countGenerateSnake.getAndIncrement();
    }

    public AtomicInteger getCountGenerateSnake() {
        return countGenerateSnake;
    }
}
