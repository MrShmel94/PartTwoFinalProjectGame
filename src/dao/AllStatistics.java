package dao;

import enums.EntityTypes;

import java.util.HashMap;
import java.util.Map;

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
        mapMaxPossibleOffspring.put(EntityTypes.KANGAROO, 7);
        mapMaxPossibleOffspring.put(EntityTypes.COW, 3);
        mapMaxPossibleOffspring.put(EntityTypes.DUCK, 10);
        mapMaxPossibleOffspring.put(EntityTypes.CATERPILLAR, 25);

        mapFullEating.put(EntityTypes.WOLF, 8.0);
        mapFullEating.put(EntityTypes.SNAKE, 0.3);
        mapFullEating.put(EntityTypes.FOX, 1.0);
        mapFullEating.put(EntityTypes.BEAR, 38.0);
        mapFullEating.put(EntityTypes.EAGLE, 1.0);
        mapFullEating.put(EntityTypes.HORSE, 45.0);
        mapFullEating.put(EntityTypes.DEER, 26.0);
        mapFullEating.put(EntityTypes.RABBIT, 0.45);
        mapFullEating.put(EntityTypes.HAMSTER, 0.0075);
        mapFullEating.put(EntityTypes.GOAT, 10.0);
        mapFullEating.put(EntityTypes.SHEEP, 7.0);
        mapFullEating.put(EntityTypes.KANGAROO, 7.0);
        mapFullEating.put(EntityTypes.COW, 53.0);
        mapFullEating.put(EntityTypes.DUCK, 0.15);
        mapFullEating.put(EntityTypes.CATERPILLAR, 0.0025);

        mapMaxLimit.put(EntityTypes.WOLF, 30);
        mapMaxLimit.put(EntityTypes.SNAKE, 123);
        mapMaxLimit.put(EntityTypes.FOX, 50);
        mapMaxLimit.put(EntityTypes.BEAR, 7);
        mapMaxLimit.put(EntityTypes.EAGLE, 166);
        mapMaxLimit.put(EntityTypes.HORSE, 23);
        mapMaxLimit.put(EntityTypes.DEER, 41);
        mapMaxLimit.put(EntityTypes.RABBIT, 750);
        mapMaxLimit.put(EntityTypes.HAMSTER, 10_000);
        mapMaxLimit.put(EntityTypes.GOAT, 107);
        mapMaxLimit.put(EntityTypes.SHEEP, 156);
        mapMaxLimit.put(EntityTypes.KANGAROO, 149);
        mapMaxLimit.put(EntityTypes.COW, 20);
        mapMaxLimit.put(EntityTypes.DUCK, 500);
        mapMaxLimit.put(EntityTypes.CATERPILLAR, 10_000);

        mapWeightAnimals.put(EntityTypes.WOLF, 50.0);
        mapWeightAnimals.put(EntityTypes.SNAKE, 2.0);
        mapWeightAnimals.put(EntityTypes.FOX, 4.0);
        mapWeightAnimals.put(EntityTypes.BEAR, 250.0);
        mapWeightAnimals.put(EntityTypes.EAGLE, 6.0);
        mapWeightAnimals.put(EntityTypes.HORSE, 300.0);
        mapWeightAnimals.put(EntityTypes.DEER, 170.0);
        mapWeightAnimals.put(EntityTypes.RABBIT, 3.0);
        mapWeightAnimals.put(EntityTypes.HAMSTER, 0.03);
        mapWeightAnimals.put(EntityTypes.GOAT, 65.0);
        mapWeightAnimals.put(EntityTypes.SHEEP, 45.0);
        mapWeightAnimals.put(EntityTypes.KANGAROO, 47.0);
        mapWeightAnimals.put(EntityTypes.COW, 350.0);
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
        mapMaxTurnToHungry.put(EntityTypes.KANGAROO, 8);
        mapMaxTurnToHungry.put(EntityTypes.COW, 4);
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
        mapMaxSpeedPerTurn.put(EntityTypes.KANGAROO, 2);
        mapMaxSpeedPerTurn.put(EntityTypes.COW, 1);
        mapMaxSpeedPerTurn.put(EntityTypes.DUCK, 1);
        mapMaxSpeedPerTurn.put(EntityTypes.CATERPILLAR, 1);

        mapChanceForFood.put(EntityTypes.WOLF, new HashMap<>());
        setMappingEat(mapChanceForFood.get(EntityTypes.WOLF),"Snake 10", "Fox 10", "Eagle 10", "Horse 30" , "Deer 40" , "Rabbit 70", "Hamster 90" , "Goat 60", "Sheep 70", "Kangaroo 20", "Cow 30", "Duck 80");
        mapChanceForFood.put(EntityTypes.SNAKE, new HashMap<>());
        setMappingEat(mapChanceForFood.get(EntityTypes.SNAKE), "Rabbit 50", "Hamster 90" , "Duck 50", "Caterpillar 90");
        mapChanceForFood.put(EntityTypes.FOX, new HashMap<>());
        setMappingEat(mapChanceForFood.get(EntityTypes.FOX),"Snake 20", "Eagle 10", "Deer 5" , "Rabbit 70", "Hamster 90" , "Goat 20", "Sheep 20", "Kangaroo 5", "Duck 80");
        mapChanceForFood.put(EntityTypes.BEAR, new HashMap<>());
        setMappingEat(mapChanceForFood.get(EntityTypes.BEAR),"Wolf 20", "Snake 30", "Fox 20", "Eagle 30", "Horse 70" , "Deer 80" , "Rabbit 80", "Hamster 90" , "Goat 70", "Sheep 70", "Kangaroo 60", "Cow 75", "Duck 80");
        mapChanceForFood.put(EntityTypes.EAGLE, new HashMap<>());
        setMappingEat(mapChanceForFood.get(EntityTypes.EAGLE), "Snake 50", "Rabbit 90" , "Hamster 90", "Duck 85", "Caterpillar 20");
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
        return mapChanceForFood.get(who).getOrDefault(whom, 0) != 0;
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
