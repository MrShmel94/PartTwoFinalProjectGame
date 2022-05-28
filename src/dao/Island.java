package dao;

import dao.abstractClasses.Animal;
import dao.abstractClasses.Plant;
import dao.factories.HerbivoresAnimalFactory;
import dao.factories.PlantsFactory;
import dao.factories.PredatorAnimalFactory;
import enums.EntityTypes;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class Island {

    private ConcurrentHashMap <EntityTypes, List<Animal>> allAnimals;
    private ConcurrentHashMap<EntityTypes,List<Plant>> allPlants;
    private final GameConfiguration GC;

    public Island(GameConfiguration gc) {
        this.GC = gc;
        this.allAnimals = generateCellAnimals();
        this.allPlants = generateCellPlants();
    }

    //Random creation of all kinds of animals.
    public ConcurrentHashMap<EntityTypes,List<Animal>> generateCellAnimals (){
        ConcurrentHashMap <EntityTypes, List<Animal>> valueCell = new ConcurrentHashMap<>();
        List<Animal> resultAnimal;
        for (EntityTypes en : EntityTypes.values()) {
            int random = ThreadLocalRandom.current().nextInt(1, 101);
            if (en.getType().equalsIgnoreCase("Predator")) {
                if (random < GC.getPercentCreationPredator()) {
                    resultAnimal = new ArrayList<>();
                    int randomAmount = ThreadLocalRandom.current().nextInt(1, AllStatistics.getValueMapMaxLimit(en) + 1);
                    for (int i = 0; i < randomAmount; i++) {
                        resultAnimal.add(PredatorAnimalFactory.types(en));
                    }
                    valueCell.put(en, resultAnimal);
                }
            } else if (en.getType().equalsIgnoreCase("Herbivores")){
                if (random < GC.getPercentCreationHerbivores()) {
                    resultAnimal = new ArrayList<>();
                    int randomAmount = ThreadLocalRandom.current().nextInt(1, AllStatistics.getValueMapMaxLimit(en) + 1);
                    for (int i = 0; i < randomAmount; i++) {
                        resultAnimal.add(HerbivoresAnimalFactory.types(en));
                    }
                    valueCell.put(en, resultAnimal);
                }
            }
        }
        return valueCell;
    }

    //Random creation of all kinds of plants.
    public ConcurrentHashMap<EntityTypes,List<Plant>> generateCellPlants (){
        ConcurrentHashMap <EntityTypes, List<Plant>> valueCell = new ConcurrentHashMap<>();
        CopyOnWriteArrayList<Plant> resultPlant;
        for (EntityTypes en : EntityTypes.values()) {
            int random = ThreadLocalRandom.current().nextInt(1, 101);
            if (en.getType().equalsIgnoreCase("Plant")){
                if (random < GC.getPercentCreatePlant()){
                    resultPlant = new CopyOnWriteArrayList<>();
                    int randomAmount = ThreadLocalRandom.current().nextInt(1, Plant.maxValuePerCell + 1);
                    for (int i = 0; i < randomAmount; i++) {
                        resultPlant.add(PlantsFactory.types(en));
                    }
                    valueCell.put(en,resultPlant);
                }
            }
        }
        return valueCell;
    }

    public void grassGrowth (){
        ConcurrentHashMap<EntityTypes, List<Plant>> newValue = new ConcurrentHashMap<>();
        CopyOnWriteArrayList<Plant> resultPlant = new CopyOnWriteArrayList<>();
        ConcurrentHashMap <String, Integer> plants = GC.getReproductionsPlantsPerTurn();
        for (EntityTypes en : Arrays.stream(EntityTypes.values()).filter(en -> en.getType().equalsIgnoreCase("Plant")).collect(Collectors.toCollection(CopyOnWriteArrayList::new))){
            int count = Optional.ofNullable(this.getAllPlants().get(en)).orElse(new ArrayList<>()).size();
            if (count != 0){
                int random = ThreadLocalRandom.current().nextInt(1, 101);
                if (random < GC.getPercentCreatePlant()){
                    int rand = Plant.maxValuePerCell - count;
                    if(rand <= 1){
                        rand = 2;
                    }
                    int randomAmount = ThreadLocalRandom.current().nextInt(1, rand);
                    plants.merge(en.getIcon(),randomAmount, Integer::sum);
                    for (int i = 0; i < randomAmount; i++) {
                        resultPlant.add(PlantsFactory.types(en));
                    }
                    newValue.put(en,resultPlant);
                }
            }else {
                int random = ThreadLocalRandom.current().nextInt(1, 101);
                if (random < GC.getPercentCreatePlant()){
                    int randomAmount = ThreadLocalRandom.current().nextInt(1, (Plant.maxValuePerCell + 1));
                    plants.merge(en.getIcon(),randomAmount, Integer::sum);
                    for (int i = 0; i < randomAmount; i++) {
                        resultPlant.add(PlantsFactory.types(en));
                    }
                    newValue.put(en,resultPlant);
                }
            }
        }
        this.setAllPlants(newValue);
        GC.setReproductionsPlantsPerTurn(plants);
    }


    public ConcurrentHashMap<EntityTypes, List<Animal>> getAllAnimals() {
        return allAnimals;
    }

    public void setAllAnimals(ConcurrentHashMap<EntityTypes, List<Animal>> allAnimals) {
        this.allAnimals = allAnimals;
    }

    public ConcurrentHashMap<EntityTypes, List<Plant>> getAllPlants() {
        return allPlants;
    }

    public void setAllPlants(ConcurrentHashMap<EntityTypes, List<Plant>> allPlants) {
        this.allPlants = allPlants;
    }


}
