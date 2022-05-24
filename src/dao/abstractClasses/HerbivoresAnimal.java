package dao.abstractClasses;

import dao.AllStatistics;
import enums.EntityTypes;
import enums.Direction;

import java.util.concurrent.ThreadLocalRandom;

public abstract class HerbivoresAnimal extends Animal {

    public HerbivoresAnimal(EntityTypes kind, String name) {
        super (kind, name);
    }

    public void eat(Plant plant) {
        if (this.getMaxHealPoint() == this.getHP()){
            return;
        }
        if (this.getMaxHealPoint() - this.getHP() >= plant.getHP()) {
            this.plusHpPerTurn(plant.getHP());
            plant.setDead(true);
        }else {
            plant.setHP(plant.getHP() - (this.getMaxHealPoint() - this.getHP()));
            this.plusHpPerTurn(plant.getHP());
        }
    }
}
