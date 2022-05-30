package dao.allInterface;

import dao.MyPair;
import dao.abstractClasses.Animal;
import enums.Direction;

public interface MethodsAnimal {

    MyPair moving();
    int offspring ();
    boolean reproduction (Animal animal);
    Direction movingDirection();
    int numberOfCells ();
    boolean isDead();
    double minusHpPerTurn();
    double plusHpPerTurn(double kgEat);

}
