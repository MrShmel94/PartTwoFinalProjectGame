package dao.factories;

import dao.abstractClasses.HerbivoresAnimal;
import dao.herbivoresAnimalsClasses.*;
import enums.EntityTypes;

public class HerbivoresAnimalFactory {

    public static HerbivoresAnimal types (EntityTypes types){

        HerbivoresAnimal animal = null;

        switch (types){
            case HORSE-> animal = new Horse();
            case DEER -> animal = new Deer();
            case RABBIT -> animal = new Rabbit();
            case HAMSTER -> animal = new Hamster();
            case GOAT -> animal = new Goat();
            case SHEEP -> animal = new Sheep();
            case BOAR -> animal = new Kangaroo();
            case BUFFALO -> animal = new Cow();
            case DUCK -> animal = new Duck();
            case CATERPILLAR -> animal = new Caterpillar();
        }
        return animal;
    }
}
