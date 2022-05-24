package dao;

import dao.abstractClasses.Animal;
import dao.abstractClasses.HerbivoresAnimal;
import dao.abstractClasses.Plant;
import dao.abstractClasses.PredatorAnimal;
import dao.herbivoresAnimalsClasses.Cow;
import enums.EntityTypes;
import enums.Sex;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Game extends GameConfiguration {

    private static final GameConfiguration game =  new GameConfiguration(3,5,5, 100,100, 100);

    private final String WOLF = "\uD83D\uDC3A";
    public final String SNAKE = "\uD83D\uDC0D";
    public final String FOX = "\uD83E\uDD8A";
    public final String BEAR = "\uD83D\uDC3B";
    public final String EAGLE = "\uD83E\uDD85";
    public final String HORSE = "\uD83D\uDC0E";
    public final String DEER = "\uD83E\uDD8C";
    public final String RABBIT = "\uD83D\uDC07";
    public final String HAMSTER = "\uD83D\uDC39";
    public final String GOAT = "\uD83D\uDC10";
    public final String SHEEP = "\uD83D\uDC11";
    public final String KANGAROO = "\uD83E\uDD98";
    public final String COW = "\uD83D\uDC2E";
    public final String DUCK = "\uD83E\uDD86";
    public final String CATERPILLAR = "\uD83D\uDC1B";
    public final String GRASS = "\uD83C\uDF3F";
    public final String BERRIES  = "\uD83C\uDF3A";

    public static void main(String[] args) {
        startGame();
        //Island island = game.getIsland(0,0);
//        Map<EntityTypes, List<Animal>> spraw1 = island.getAllAnimals();
//        spraw1.values().stream().map(Collection::stream).forEach(a -> a.forEach(Animal::minusHpPerTurn));
        //spraw1.values().stream().flatMap(Collection::stream).filter(a -> a.getKind().getType().equalsIgnoreCase("Herbivores")).forEach(System.out::println);
       // game.fullHerbivoresEatingInCell(game.getIsland(0,0));


//
//        for (Map.Entry<EntityTypes, List<Animal>> en : island.getAllAnimals().entrySet()){
//            System.out.println(en.getKey() + "   " + en.getValue().stream().filter(animal -> animal.getSex() == Sex.FEMALE).count());
//            System.out.println(en.getKey() + "   " + en.getValue().stream().filter(animal -> animal.getSex() == Sex.FEMALE).filter(animal -> !animal.isPregnant()).count());
//            System.out.println(en.getKey() + "   " + en.getValue().stream().filter(Animal::isPregnant).count());
//            System.out.println();
//        }
        //game.fullEatingInCell(1,1,new Island(game).generateCellAnimals());
        //System.out.println(new Island(game).getAllAnimals());
//        ConcurrentHashMap<EntityTypes, List<Animal>> spraw1 = new Island(game).generateCellAnimals();
//        spraw1.values().stream().map(Collection::stream).forEach(a -> a.forEach(Animal::minusHpPerTurn));
//        ConcurrentHashMap<EntityTypes, List<Animal>> spraw2 = new Island(game).generateCellAnimals();
//        spraw2.values().stream().map(Collection::stream).forEach(a -> a.forEach(Animal::minusHpPerTurn));
//        //spraw.values().forEach(System.out::println);
//        game.fullEatingInCell(1,1, spraw1);
//        System.out.println();
//        game.fullEatingInCell(0,0, spraw2);


//        Cow cow = new Cow();
//        Cow cow1 = new Cow();
//        Bear bear = new Bear();
//        System.out.println(wolf.movingDirection());
       // game.generateCellPlants().entrySet().stream().filter(a-> a.getKey().equals(EntityTypes.BERRIES)).map(Map.Entry::getValue).map(Collection::stream).map(a -> a.filter(Plant::isPoison)).forEach(a -> a.forEach(System.out::println));
        //System.out.println(game.getInfoTypeAndAmountCell(3, 3));
    }

    public static void startGame (){
        game.drawGame();
        game.infoStartGame();
    }
}
