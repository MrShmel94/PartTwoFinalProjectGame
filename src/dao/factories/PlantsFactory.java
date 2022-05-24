package dao.factories;

import dao.abstractClasses.HerbivoresAnimal;
import dao.abstractClasses.Plant;
import dao.herbivoresAnimalsClasses.*;
import dao.plantClasses.Berries;
import dao.plantClasses.Plants;
import enums.EntityTypes;

public class PlantsFactory {

    public static Plant types (EntityTypes types){
        Plant plant = null;

        switch (types){
            case GRASS -> plant = new Plants();
            case BERRIES -> plant = new Berries();
        }
        return plant;
    }
}
