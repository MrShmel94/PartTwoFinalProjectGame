package dao.abstractClasses;

import dao.AllStatistics;
import enums.EntityTypes;

import java.util.concurrent.ThreadLocalRandom;

public abstract class HerbivoresAnimal extends Animal {

    public HerbivoresAnimal(EntityTypes kind, String name) {
        super (kind, name);
    }

    public void eat(Animal animal){
        if(this.getHP() == this.getMaxHealPoint()
                || animal.isChecked()
        ) {
            return;
        }
        int randomNumber = ThreadLocalRandom.current().nextInt(1,101);
        if (AllStatistics.getValue(this.getKind(), animal.getKind()) >= randomNumber) {
            animal.setDead(true);
            animal.setChecked(true);
            this.plusHpPerTurn(animal.getWeight());
        } else {
            animal.setChecked(true);
        }
    }
    public void eat(Plant plant) {
        if (plant.isPoison()){
            this.setDead(true);
            plant.setDead(true);
        }
        if (this.getMaxHealPoint() == this.getHP()){
            return;
        }
        if (this.getMaxHealPoint() - this.getHP() >= plant.getHP()) {
            this.plusHpPerTurn(plant.getHP());
            plant.setHP(0);
            plant.setDead(true);
        }else {
            plant.setHP(plant.getHP() - (this.getMaxHealPoint() - this.getHP()));
            this.plusHpPerTurn(plant.getHP());
        }
    }
}
