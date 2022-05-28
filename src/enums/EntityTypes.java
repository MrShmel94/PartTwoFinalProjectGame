package enums;

public enum EntityTypes {
    WOLF("Predator", "\uD83D\uDC3A"),
    SNAKE ("Predator", "\uD83D\uDC0D"),
    FOX ("Predator", "\uD83E\uDD8A"),
    BEAR ("Predator", "\uD83D\uDC3B"),
    EAGLE ("Predator", "\uD83E\uDD85"),
    HORSE ("Herbivores", "\uD83D\uDC0E"),
    DEER ("Herbivores", "\uD83E\uDD8C"),
    RABBIT ("Herbivores", "\uD83D\uDC07"),
    HAMSTER ("Herbivores", "\uD83D\uDC39"),
    GOAT ("Herbivores", "\uD83D\uDC10"),
    SHEEP ("Herbivores", "\uD83D\uDC11"),
    BOAR("Herbivores","\uD83D\uDC17"),
    BUFFALO("Herbivores" , "\uD83D\uDC03"),
    DUCK ("Herbivores", "\uD83E\uDD86"),
    CATERPILLAR ("Herbivores" , "\uD83D\uDC1B"),
    GRASS ("Plant" ,"\uD83C\uDF3F"),
    BERRIES ("Plant" , "\uD83C\uDF3A");

    private String type;
    private String icon;

    EntityTypes(String type, String icon) {
        this.type = type;
        this.icon = icon;
    }

    public String getType(){
        return type;
    }

    public String getIcon(){
        return icon;
    }
}
