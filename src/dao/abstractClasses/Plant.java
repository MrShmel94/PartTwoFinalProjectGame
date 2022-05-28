package dao.abstractClasses;

import enums.EntityTypes;

import java.util.Objects;

public abstract class Plant{

    private EntityTypes type;
    private int weight;
    private String name;
    private boolean isDead;
    private double HP;
    public static int maxValuePerCell = 200;
    private boolean isPoison;

    public Plant(EntityTypes type, int weight) {
        this.type = type;
        this.isPoison = false;
        this.isDead = false;
        this.weight = weight;
        this.HP = weight;
    }

    public EntityTypes getType() {
        return type;
    }

    public double getHP() {
        return HP;
    }

    public void setHP(double HP) {
        this.HP = HP;
    }

    public void setType(EntityTypes type) {
        this.type = type;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDead() {
        return isDead;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }

    public int getMaxValuePerCell() {
        return maxValuePerCell;
    }

    public boolean isPoison() {
        return isPoison;
    }

    public void setPoison(boolean poison) {
        isPoison = poison;
    }

    @Override
    public String toString() {
        return "Plant{" +
                "type=" + type +
                ", weight=" + weight +
                ", name='" + name + '\'' +
                ", isDead=" + isDead +
                ", maxValuePerCell=" + maxValuePerCell +
                ", isPoison=" + isPoison +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Plant plant = (Plant) o;
        return weight == plant.weight && isDead == plant.isDead && maxValuePerCell == plant.maxValuePerCell && isPoison == plant.isPoison && type == plant.type && name.equals(plant.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, weight, name, isDead, maxValuePerCell, isPoison);
    }
}
