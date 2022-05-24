package dao.abstractClasses;

import dao.AllStatistics;
import dao.allInterface.MethodsAnimal;
import enums.Direction;
import enums.EntityTypes;
import enums.Sex;

import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Animal implements MethodsAnimal {

    private final EntityTypes kind;
    private final String type;
    private final String name;
    private final double weight;
    private boolean isDead;
    private final Sex sex;
    private final int speed;
    private boolean isPregnant;
    private boolean isChecked;
    private final double maxHealPoint;
    private double HP;
    private final double pointPerTurn;

    public Animal (EntityTypes kind, String name){
        if (ThreadLocalRandom.current().nextInt(1,3) == 1){
            this.sex = Sex.MALE;
        }else{
            this.sex = Sex.FEMALE;
        }
        this.kind = kind;
        this.name = name;
        this.isChecked = false;
        this.isDead = false;
        this.isPregnant = false;
        this.type = kind.getType();
        this.weight = AllStatistics.getValueMapWeightAnimals(kind);
        this.speed = AllStatistics.getValueMapMaxSpeedPerTurn(kind);
        this.maxHealPoint = AllStatistics.getValueMapFullEating(kind);
        this.HP = maxHealPoint;
        this.pointPerTurn = maxHealPoint / AllStatistics.getValueMapMaxTurnToHungry(kind);
    }

    @Override
    public double minusHpPerTurn(){
        this.HP-= pointPerTurn;
        if (HP <= 0){
            setDead(true);
        }
        return HP;
    }

    @Override
    public double plusHpPerTurn(double kgEat){
        HP+=kgEat;
        if (HP > maxHealPoint){
            HP = maxHealPoint;
        }
        return HP;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public Sex getSex() {
        return sex;
    }

    public double getHP() {
        return HP;
    }

    public boolean isPregnant() {
        return isPregnant;
    }

    public void setPregnant(boolean pregnant) {
        isPregnant = pregnant;
    }

    public EntityTypes getKind() {
        return kind;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public double getWeight() {
        return weight;
    }

    @Override
    public boolean isDead() {
        return isDead;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }

    public int getSpeed() {
        return speed;
    }

    public double getMaxHealPoint() {
        return maxHealPoint;
    }

    public double getPointPerTurn() {
        return pointPerTurn;
    }

    @Override
    public int numberOfCells() {
        return ThreadLocalRandom.current().nextInt(1,AllStatistics.getValueMapMaxSpeedPerTurn(this.getKind()) + 1);
    }

    @Override
    public Direction movingDirection() {
        return Direction.values()[ThreadLocalRandom.current().nextInt(0,5)];
    }

    @Override
    public void moving(){

    }

    //Not used
    @Override
    public boolean reproduction (Animal animal){
        if (animal.sex != this.sex){
            if (animal.sex == Sex.FEMALE && !animal.isPregnant()){
                animal.setPregnant(true);
                return true;
            }else if(this.sex == Sex.FEMALE && !this.isPregnant){
                this.setPregnant(true);
                return true;
            }
        }
        return false;
    }

    @Override
    public int offspring (){
        return ThreadLocalRandom.current().nextInt(1,(AllStatistics.getValueMapMaxOffSpring(this.kind) + 1));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return Double.compare(animal.weight, weight) == 0 && isDead == animal.isDead && speed == animal.speed && isPregnant == animal.isPregnant && Double.compare(animal.maxHealPoint, maxHealPoint) == 0 && Double.compare(animal.HP, HP) == 0 && Double.compare(animal.pointPerTurn, pointPerTurn) == 0 && kind == animal.kind && type.equals(animal.type) && name.equals(animal.name) && sex == animal.sex;
    }

    @Override
    public int hashCode() {
        return Objects.hash(kind, type, name, weight, isDead, sex, speed, isPregnant, maxHealPoint, HP, pointPerTurn);
    }

    @Override
    public String toString() {
        return "kind=" + kind +
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", weight=" + weight +
                ", isDead=" + isDead +
                ", sex=" + sex +
                ", speed=" + speed +
                ", isPregnant=" + isPregnant +
                ", maxHealPoint=" + maxHealPoint +
                ", HP=" + HP +
                ", pointPerTurn=" + pointPerTurn +
                '}';
    }
}
