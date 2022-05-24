package dao.abstractClasses;

import dao.AllStatistics;
import enums.EntityTypes;
import enums.Direction;

import java.util.concurrent.ThreadLocalRandom;

public abstract class  PredatorAnimal extends Animal {


    public PredatorAnimal(EntityTypes kind, String name) {
        super(kind, name);
    }

    public void eat(Animal animal) {
        if(this.getKind() == animal.getKind()
                || this.getHP() == this.getMaxHealPoint()
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

}
