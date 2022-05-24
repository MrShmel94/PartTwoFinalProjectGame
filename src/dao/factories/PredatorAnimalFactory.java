package dao.factories;

import dao.abstractClasses.PredatorAnimal;
import dao.predatorsAnimalClasses.*;
import enums.EntityTypes;


public class PredatorAnimalFactory {

    public static PredatorAnimal types (EntityTypes types){
        PredatorAnimal animal = null;

        switch (types){
            case WOLF -> animal = new Wolf();
            case FOX -> animal = new Fox();
            case SNAKE -> animal = new Snake();
            case BEAR -> animal = new Bear();
            case EAGLE -> animal = new Eagle();
        }
        return animal;
    }
}
