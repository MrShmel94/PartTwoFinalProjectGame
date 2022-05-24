package enums;

public enum EntityTypes {
    WOLF("Predator"),
    SNAKE ("Predator"),
    FOX ("Predator"),
    BEAR ("Predator"),
    EAGLE ("Predator"),
    HORSE ("Herbivores"),
    DEER ("Herbivores"),
    RABBIT ("Herbivores"),
    HAMSTER ("Herbivores"),
    GOAT ("Herbivores"),
    SHEEP ("Herbivores"),
    KANGAROO ("Herbivores"),
    COW ("Herbivores"),
    DUCK ("Herbivores"),
    CATERPILLAR ("Herbivores"),
    GRASS ("Plant"),
    BERRIES ("Plant");

    private String type;

    EntityTypes(String type) {
        this.type = type;
    }

    public String getType(){
        return type;
    }
}
