package dao.allInterface;

import dao.abstractClasses.Animal;
import enums.Direction;
import enums.EntityTypes;

public interface MethodsAnimal {

    void moving();
    int offspring ();
    boolean reproduction (Animal animal);
    Direction movingDirection();
    int numberOfCells ();
    boolean isDead();
    double minusHpPerTurn();
    double plusHpPerTurn(double kgEat);

}
