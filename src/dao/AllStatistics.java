package dao;

import enums.EntityTypes;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class AllStatistics {

    private AllStatistics(){}

    private static final Map<EntityTypes, Double> mapFullEating = new HashMap<>();
    private static final Map<EntityTypes, Integer> mapMaxLimit = new HashMap<>();
    private static final Map<EntityTypes, Double> mapWeightAnimals = new HashMap<>();
    private static final Map<EntityTypes, Integer> mapMaxTurnToHungry = new HashMap<>();
    private static final Map<EntityTypes, Integer> mapMaxSpeedPerTurn = new HashMap<>();
    private static final Map<EntityTypes, Map<EntityTypes, Integer>> mapChanceForFood = new HashMap<>();
    private static final Map<EntityTypes,Integer> mapMaxPossibleOffspring = new HashMap<>();


    static {

        mapMaxPossibleOffspring.put(EntityTypes.WOLF, 3);
        mapMaxPossibleOffspring.put(EntityTypes.SNAKE, 5);
        mapMaxPossibleOffspring.put(EntityTypes.FOX, 5);
        mapMaxPossibleOffspring.put(EntityTypes.BEAR, 2);
        mapMaxPossibleOffspring.put(EntityTypes.EAGLE, 5);
        mapMaxPossibleOffspring.put(EntityTypes.HORSE, 7);
        mapMaxPossibleOffspring.put(EntityTypes.DEER, 4);
        mapMaxPossibleOffspring.put(EntityTypes.RABBIT, 10);
        mapMaxPossibleOffspring.put(EntityTypes.HAMSTER, 20);
        mapMaxPossibleOffspring.put(EntityTypes.GOAT, 6);
        mapMaxPossibleOffspring.put(EntityTypes.SHEEP, 8);
        mapMaxPossibleOffspring.put(EntityTypes.BOAR, 7);
        mapMaxPossibleOffspring.put(EntityTypes.BUFFALO, 3);
        mapMaxPossibleOffspring.put(EntityTypes.DUCK, 10);
        mapMaxPossibleOffspring.put(EntityTypes.CATERPILLAR, 25);

        mapFullEating.put(EntityTypes.WOLF, 8.0);
        mapFullEating.put(EntityTypes.SNAKE, 3.0);
        mapFullEating.put(EntityTypes.FOX, 2.0);
        mapFullEating.put(EntityTypes.BEAR, 80.0);
        mapFullEating.put(EntityTypes.EAGLE, 1.0);
        mapFullEating.put(EntityTypes.HORSE, 60.0);
        mapFullEating.put(EntityTypes.DEER, 50.0);
        mapFullEating.put(EntityTypes.RABBIT, 0.45);
        mapFullEating.put(EntityTypes.HAMSTER, 0.01);
        mapFullEating.put(EntityTypes.GOAT, 10.0);
        mapFullEating.put(EntityTypes.SHEEP, 15.0);
        mapFullEating.put(EntityTypes.BOAR, 50.0);
        mapFullEating.put(EntityTypes.BUFFALO, 100.0);
        mapFullEating.put(EntityTypes.DUCK, 0.15);
        mapFullEating.put(EntityTypes.CATERPILLAR, 0.0025);

        mapMaxLimit.put(EntityTypes.WOLF, 30);
        mapMaxLimit.put(EntityTypes.SNAKE, 30);
        mapMaxLimit.put(EntityTypes.FOX, 30);
        mapMaxLimit.put(EntityTypes.BEAR, 5);
        mapMaxLimit.put(EntityTypes.EAGLE, 20);
        mapMaxLimit.put(EntityTypes.HORSE, 20);
        mapMaxLimit.put(EntityTypes.DEER, 20);
        mapMaxLimit.put(EntityTypes.RABBIT, 150);
        mapMaxLimit.put(EntityTypes.HAMSTER, 500);
        mapMaxLimit.put(EntityTypes.GOAT, 140);
        mapMaxLimit.put(EntityTypes.SHEEP, 140);
        mapMaxLimit.put(EntityTypes.BOAR, 50);
        mapMaxLimit.put(EntityTypes.BUFFALO, 10);
        mapMaxLimit.put(EntityTypes.DUCK, 200);
        mapMaxLimit.put(EntityTypes.CATERPILLAR, 1000);

        mapWeightAnimals.put(EntityTypes.WOLF, 50.0);
        mapWeightAnimals.put(EntityTypes.SNAKE, 15.0);
        mapWeightAnimals.put(EntityTypes.FOX, 8.0);
        mapWeightAnimals.put(EntityTypes.BEAR, 500.0);
        mapWeightAnimals.put(EntityTypes.EAGLE, 6.0);
        mapWeightAnimals.put(EntityTypes.HORSE, 400.0);
        mapWeightAnimals.put(EntityTypes.DEER, 300.0);
        mapWeightAnimals.put(EntityTypes.RABBIT, 2.0);
        mapWeightAnimals.put(EntityTypes.HAMSTER, 0.05);
        mapWeightAnimals.put(EntityTypes.GOAT, 60.0);
        mapWeightAnimals.put(EntityTypes.SHEEP, 70.0);
        mapWeightAnimals.put(EntityTypes.BOAR, 400.0);
        mapWeightAnimals.put(EntityTypes.BUFFALO, 700.0);
        mapWeightAnimals.put(EntityTypes.DUCK, 1.0);
        mapWeightAnimals.put(EntityTypes.CATERPILLAR, 0.01);

        mapMaxTurnToHungry.put(EntityTypes.WOLF, 10);
        mapMaxTurnToHungry.put(EntityTypes.SNAKE, 15);
        mapMaxTurnToHungry.put(EntityTypes.FOX, 8);
        mapMaxTurnToHungry.put(EntityTypes.BEAR, 15);
        mapMaxTurnToHungry.put(EntityTypes.EAGLE, 5);
        mapMaxTurnToHungry.put(EntityTypes.HORSE, 5);
        mapMaxTurnToHungry.put(EntityTypes.DEER, 4);
        mapMaxTurnToHungry.put(EntityTypes.RABBIT, 7);
        mapMaxTurnToHungry.put(EntityTypes.HAMSTER, 3);
        mapMaxTurnToHungry.put(EntityTypes.GOAT, 5);
        mapMaxTurnToHungry.put(EntityTypes.SHEEP, 5);
        mapMaxTurnToHungry.put(EntityTypes.BOAR, 8);
        mapMaxTurnToHungry.put(EntityTypes.BUFFALO, 4);
        mapMaxTurnToHungry.put(EntityTypes.DUCK, 4);
        mapMaxTurnToHungry.put(EntityTypes.CATERPILLAR, 5);

        mapMaxSpeedPerTurn.put(EntityTypes.WOLF, 3);
        mapMaxSpeedPerTurn.put(EntityTypes.SNAKE, 1);
        mapMaxSpeedPerTurn.put(EntityTypes.FOX, 3);
        mapMaxSpeedPerTurn.put(EntityTypes.BEAR, 2);
        mapMaxSpeedPerTurn.put(EntityTypes.EAGLE, 4);
        mapMaxSpeedPerTurn.put(EntityTypes.HORSE, 3);
        mapMaxSpeedPerTurn.put(EntityTypes.DEER, 3);
        mapMaxSpeedPerTurn.put(EntityTypes.RABBIT, 3);
        mapMaxSpeedPerTurn.put(EntityTypes.HAMSTER, 1);
        mapMaxSpeedPerTurn.put(EntityTypes.GOAT, 1);
        mapMaxSpeedPerTurn.put(EntityTypes.SHEEP, 1);
        mapMaxSpeedPerTurn.put(EntityTypes.BOAR, 2);
        mapMaxSpeedPerTurn.put(EntityTypes.BUFFALO, 1);
        mapMaxSpeedPerTurn.put(EntityTypes.DUCK, 1);
        mapMaxSpeedPerTurn.put(EntityTypes.CATERPILLAR, 1);

        mapChanceForFood.put(EntityTypes.WOLF, new HashMap<>());
        setMappingEat(mapChanceForFood.get(EntityTypes.WOLF),"Horse 10" , "Deer 15" , "Rabbit 60", "Hamster 80" , "Goat 60", "Sheep 70", "Boar 15", "Buffalo 10", "Duck 40");
        mapChanceForFood.put(EntityTypes.SNAKE, new HashMap<>());
        setMappingEat(mapChanceForFood.get(EntityTypes.SNAKE), "Fox 15" , "Rabbit 20", "Hamster 40" , "Duck 10");
        mapChanceForFood.put(EntityTypes.FOX, new HashMap<>());
        setMappingEat(mapChanceForFood.get(EntityTypes.FOX), "Rabbit 70", "Hamster 90" , "Duck 60", "Caterpillar 40");
        mapChanceForFood.put(EntityTypes.BEAR, new HashMap<>());
        setMappingEat(mapChanceForFood.get(EntityTypes.BEAR),"Snake 80", "Horse 40" , "Deer 80" , "Rabbit 80", "Hamster 90" , "Goat 70", "Sheep 70", "Boar 50", "Buffalo 20", "Duck 10");
        mapChanceForFood.put(EntityTypes.EAGLE, new HashMap<>());
        setMappingEat(mapChanceForFood.get(EntityTypes.EAGLE), "Snake 10", "Rabbit 90" , "Hamster 90", "Duck 80");
        mapChanceForFood.put(EntityTypes.HAMSTER, new HashMap<>());
        setMappingEat(mapChanceForFood.get(EntityTypes.HAMSTER), "Caterpillar 90");
        mapChanceForFood.put(EntityTypes.BOAR, new HashMap<>());
        setMappingEat(mapChanceForFood.get(EntityTypes.BOAR), "Hamster 50" , "Caterpillar 90");
        mapChanceForFood.put(EntityTypes.DUCK, new HashMap<>());
        setMappingEat(mapChanceForFood.get(EntityTypes.DUCK), "Caterpillar 90");
    }

    //We give the user only a copy of the map so that nothing can be changed in the main map.
    public Map<EntityTypes,Double> getMapFullEating (){
        return new HashMap<>(mapFullEating);
    }

    public Map<EntityTypes,Integer> getMapMaxPossibleOffspring (){
        return new HashMap<>(mapMaxPossibleOffspring);
    }

    public Map<EntityTypes,Integer> getMapMaxLimit (){
        return new HashMap<>(mapMaxLimit);
    }

    public Map<EntityTypes,Double> getMapWeightAnimals (){
        return new HashMap<>(mapWeightAnimals);
    }

    public Map<EntityTypes,Integer> getMapMaxTurnToHungry (){
        return new HashMap<>(mapMaxTurnToHungry);
    }

    public Map<EntityTypes,Integer> getMapMaxSpeedPerTurn (){
        return new HashMap<>(mapMaxSpeedPerTurn);
    }

    public Map<EntityTypes, Map <EntityTypes, Integer>> getValue (){
        return new HashMap<EntityTypes, Map<EntityTypes,Integer>>(mapChanceForFood);
    }

    //We give the user only data on one object, so as not to give the whole collection.
    public static double getValueMapFullEating (EntityTypes animal){
        return mapFullEating.get(animal);
    }

    public static int getValueMapMaxLimit (EntityTypes animal){
        return mapMaxLimit.get(animal);
    }

    public static int getValueMapMaxOffSpring (EntityTypes animal){
        return mapMaxPossibleOffspring.get(animal);
    }

    public static double getValueMapWeightAnimals (EntityTypes animal){
        return mapWeightAnimals.get(animal);
    }

    public static int getValueMapMaxTurnToHungry (EntityTypes animal){
        return mapMaxTurnToHungry.get(animal);
    }

    public static int getValueMapMaxSpeedPerTurn (EntityTypes animal){
        return mapMaxSpeedPerTurn.get(animal);
    }

    public static int getValue (EntityTypes whoEating, EntityTypes whomEating){
        return mapChanceForFood.get(whoEating).getOrDefault(whomEating, -1);
    }

    public static boolean get (EntityTypes who, EntityTypes whom){
        //return mapChanceForFood.get(who).getOrDefault(whom, 0) != 0;
        return Optional.ofNullable(mapChanceForFood.get(who)).isPresent() && mapChanceForFood.get(who).getOrDefault(whom, 0) != 0;
    }
    //This method is needed in order to quickly add values to eat map.
    private static void setMappingEat (Map <EntityTypes, Integer> value, String ... s){
        String [] array;
        for (String a : s){
            array = a.split(" ");
            value.put(EntityTypes.valueOf(array[0].toUpperCase()), Integer.parseInt(array[1]));
        }
    }
}
