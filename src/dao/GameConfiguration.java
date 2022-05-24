package dao;

import dao.abstractClasses.HerbivoresAnimal;
import dao.abstractClasses.Plant;
import dao.abstractClasses.PredatorAnimal;
import dao.abstractClasses.Animal;
import dao.factories.HerbivoresAnimalFactory;
import dao.factories.PredatorAnimalFactory;
import enums.EntityTypes;
import enums.Sex;

import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class GameConfiguration
        //extends Application
{

    private boolean isStopped;
    private long turn;
    private int width;
    private int height;
    private Island[][] matrix;
    private int percentCreationPredator;
    private int percentCreationHerbivores;
    private int percentCreatePlant;

    //private final Island island = new Island(this);

    public GameConfiguration(){}

//    @Override
//    public void start(Stage stage) throws Exception {
//        Group group = new Group();
//        Scene scene = new Scene(group, 400, 300);
//
//        stage.setScene(scene);
//        stage.setTitle("Game");
//        stage.show();
//    }

    public GameConfiguration(long turn, int width, int height, int percentCreationHerbivores , int percentCreationPredator, int percentCreatePlant) {
        this.turn = turn;
        this.width = width;
        this.height = height;
        this.matrix = new Island[width][height];
        this.isStopped = false;
        this.percentCreationPredator = percentCreationPredator;
        this.percentCreationHerbivores = percentCreationHerbivores;
        this.percentCreatePlant = percentCreatePlant;
    }


    //Метод создание поля и заполнения объектами
    public void drawGame(){
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = new Island(this);
                //this.reproductionAnimals(matrix[i][j]);
                //this.fullHerbivoresEatingInCell(matrix[i][j]);
            }
        }
    }

    //Метод возвращает животного, которого может скушать определенный хищник(передан в параметрах).
    private EntityTypes shuffleList (List<EntityTypes> value, EntityTypes whoAnimal){
        List<EntityTypes> et = new ArrayList<>(value);
        Collections.shuffle(et);
        return et.stream().filter(a -> AllStatistics.get(whoAnimal, a)).findFirst().orElse(null);
    }


    //Метод проверяет все ли в листе - мертвые.
    private boolean fullIsDead (List<Animal> value){
        return value.stream().allMatch(Animal::isDead);
    }

    //метод проверяет хочет ли кто-то в листе кушать
    private boolean fullEating (List<Animal> value){
        return value.stream().noneMatch(a -> a.getHP() != a.getMaxHealPoint());
    }


    public Map<EntityTypes, List<Animal>> movingCell () {

        return null;
    }

    //Метод появления потомства в клетке
    public Map<EntityTypes, Integer> birthOffspring(Island island) {
        Map<EntityTypes, List<Animal>> values = island.getAllAnimals();
        Map<EntityTypes, Integer> result = new HashMap<>();

        for (EntityTypes en : values.keySet()) {
            List<Animal> femaleAnimalPregnant = values.get(en).stream().filter(a -> a.getSex() == Sex.FEMALE).filter(Animal::isPregnant).toList();
            for (Animal animal : femaleAnimalPregnant) {
                for (int i = 0; i < animal.offspring(); i++) {
                    if (values.get(en).size() >= AllStatistics.getValueMapMaxLimit(en)) {
                        animal.setPregnant(false);
                        continue;
                    }
                    if (en.getType().equalsIgnoreCase("Predator")) {
                        values.get(en).add(PredatorAnimalFactory.types(en));
                    } else {
                        values.get(en).add(HerbivoresAnimalFactory.types(en));
                    }
                    animal.setPregnant(false);
                    result.merge(en, 1, Integer::sum);
                }
            }
        }
        return result;
    }


    // Метод размножения в клетке
    public void reproductionAnimals (Island island){
        Map<EntityTypes, List<Animal>> values = island.getAllAnimals();
        for (EntityTypes en : values.keySet()){
            List<Animal> maleAnimal = values.get(en).stream().filter(a -> a.getSex() == Sex.MALE).toList();
            List<Animal> femaleAnimal = values.get(en).stream().filter(a -> a.getSex() == Sex.FEMALE).filter(b -> !b.isPregnant()).toList();

            for (Animal value : femaleAnimal) {
                if (ThreadLocalRandom.current().nextInt(1, 3) == 2) {
                    for (Animal animal : maleAnimal) {
                        if (!animal.isChecked()) {
                            animal.setChecked(true);
                            value.setPregnant(true);
                            break;
                        }
                    }
                }
            }
        }
    }

    // Метод поедания травоядных животных, в каждой клетке.
    public void fullHerbivoresEatingInCell (Island island) {
        ConcurrentHashMap <EntityTypes, List<Animal>> herbivoresAnimal = island.getAllAnimals().entrySet().stream().filter(a -> a.getKey().getType().equalsIgnoreCase("Herbivores"))
                .collect(Collectors.toMap(Entry::getKey, Entry::getValue, (a, b) -> a , ConcurrentHashMap::new));
        ConcurrentHashMap <EntityTypes, List<Plant>> allPlants = island.getAllPlants();
        CopyOnWriteArrayList <Plant> allPlantsValue = allPlants.values().stream().flatMap(Collection::stream).collect(Collectors.toCollection(CopyOnWriteArrayList::new));
        while (!herbivoresAnimal.values().stream().flatMap(Collection::stream).allMatch(a -> a.getMaxHealPoint() == a.getHP())
                || allPlantsValue.stream().allMatch(Plant::isDead)) {
            for (EntityTypes en : herbivoresAnimal.keySet()) {
                if (!fullEating(herbivoresAnimal.get(en))) {
                    for (Animal animal : herbivoresAnimal.get(en)) {
                        if (allPlantsValue.stream().allMatch(Plant::isDead) || allPlants.isEmpty()) {
                            break;
                        }
                        HerbivoresAnimal whoEat = (HerbivoresAnimal) animal;
                        for (Plant plant : allPlantsValue) {
                            if (plant.isDead()){
                                allPlantsValue.remove(plant);
                            }
                            if (whoEat.getHP() != whoEat.getMaxHealPoint()) {
                                whoEat.eat(plant);
                            } else {
                                break;
                            }
                        }
                    }
                }
            }
        }
    }

    // Метод поедания хищниками в клетке, (x,y - координаты клетки, лист array - это лист животных которые приходят в клетку)
    public void fullPredatorsEatingInCell (int x, int y, Map<EntityTypes, List<Animal>> array){
        Map<EntityTypes, List<Animal>> valueCell = matrix [x][y].getAllAnimals();
        Map<EntityTypes, List<Animal>> allMaps = Stream.of(valueCell, array).flatMap(map -> map.entrySet().stream()).collect(Collectors.toMap(Entry::getKey, Entry::getValue, (v1, v2) -> Stream.concat(v1.stream(), v2.stream()).collect(Collectors.toList())));
        List<EntityTypes>  allTypesAnimalsCell = new ArrayList<>(allMaps.keySet().stream().toList());
        List <EntityTypes> shuffleListPredators = new ArrayList<>(allMaps.keySet().stream().filter(a -> a.getType().equalsIgnoreCase("Predator")).toList());
        Collections.shuffle(shuffleListPredators);
        //|| allMaps.values().stream().flatMap(Collection::stream).filter(a -> a.getKind().getType().equalsIgnoreCase("Herbivores")).allMatch(Animal::isDead)
        //|| allMaps.values().stream().flatMap(Collection::stream).allMatch(Animal::isChecked)) {
        while (!allMaps.values().stream().flatMap(Collection::stream).filter(a -> a.getKind().getType().equalsIgnoreCase("Predator")).allMatch(b -> b.getMaxHealPoint() == b.getHP())) {
            for (EntityTypes en : shuffleListPredators) {
                if (!fullEating(allMaps.get(en))) {
                    EntityTypes whom = shuffleList(allTypesAnimalsCell, en);
                    if (whom != null) {
                        List<Animal> whoEating = allMaps.get(en);
                        List<Animal> whomEating = allMaps.get(whom);
                        for (Animal whoEat : whoEating) {
                            if (whoEat.getMaxHealPoint() == whoEat.getHP()) {
                                whoEat.setChecked(true);
                                continue;
                            }
                            for (Animal animal : whomEating) {
                                PredatorAnimal predatorAnimal = (PredatorAnimal) whoEat;
                                predatorAnimal.eat(animal);
                                if (predatorAnimal.getHP() == predatorAnimal.getMaxHealPoint()) {
                                    break;
                                }
                            }
                        }
                    }
                }
            }

        }
        allMaps = clearingMap(allMaps);
        matrix[x][y].setAllAnimals(clearingMap(allMaps));
    }

    //Метод удаляет все мертвые объекты и возвращает новую мапу без них.
    private Map<EntityTypes, List<Animal>> clearingMap (Map<EntityTypes, List<Animal>> array){
        return array.entrySet().stream().collect(Collectors.toMap(Entry::getKey, b -> b.getValue().stream().filter(a -> !a.isDead()).collect(Collectors.toList())));
    }

    private ConcurrentHashMap<String, Long> getInfoCountReproductionPerTurn (Island island){
        return island.getAllAnimals().entrySet().stream().collect(Collectors.toMap(key -> key.getKey().getIcon(), val -> val.getValue().stream().filter(Animal::isPregnant).count(), Long::sum , ConcurrentHashMap::new));
    }

    private ConcurrentHashMap<String, Long> getInfoDeadTypeAnimalPerTurn (Island island){
        return island.getAllAnimals().entrySet().stream().collect(Collectors.toMap(key -> key.getKey().getIcon(), val -> val.getValue().stream().filter(Animal::isDead).count(), Long::sum , ConcurrentHashMap::new));
    }

    public ConcurrentHashMap <String, Long> getFullIslandInfoReproductionPerTurn(){
       return Arrays.stream(matrix).map(Arrays::stream).flatMap(a -> a.map(this::getInfoCountReproductionPerTurn)).flatMap(a -> a.entrySet().stream()).collect(Collectors.toMap(Entry::getKey, Entry::getValue, Long::sum, ConcurrentHashMap::new));
    }

    public ConcurrentHashMap <String, Long> getFullIslandInfoDeadTypeAnimalPerTurn(){
        return Arrays.stream(matrix).map(Arrays::stream).flatMap(a -> a.map(this::getInfoDeadTypeAnimalPerTurn)).flatMap(a -> a.entrySet().stream()).collect(Collectors.toMap(Entry::getKey, Entry::getValue, Long::sum, ConcurrentHashMap::new));
    }

//    //Information about the number and types of animals in a particular cell
//    private Map<EntityTypes, Integer> getInfoTypeAndAmountCell(int x , int y){
//        Map<EntityTypes,List<Animal>> value = matrix[x][y].getAllAnimals();
//        if (value == null){
//            return null;
//        }
//        return value.entrySet().stream().collect(Collectors.toMap(Entry::getKey, b-> b.getValue().size()));
//    }

//    private ConcurrentHashMap<EntityTypes, Integer> getInfoTypeAndAmountCell(Island island){
//        return island.getAllAnimals().entrySet().stream().collect(Collectors.toMap(Entry::getKey, b-> b.getValue().size()));
//    }

//    public String getInfoMaxValueAnimalPerCel (Island island){
//        island.getAllAnimals().values().stream().filter(a -> Math::max)
//    }


    public void infoStartGame (){
        ConcurrentHashMap <String, Integer> value = Arrays.stream(matrix).map(Arrays::stream).flatMap(a -> a.map(Island::getAllAnimals)).flatMap(a -> a.entrySet().stream()).collect(Collectors.toMap(key -> key.getKey().getIcon(), size -> size.getValue().size(), Integer::sum, ConcurrentHashMap::new));
        value.entrySet().forEach(System.out::println);
    }

    //The method checks if the animal can move in the given direction
    public boolean isCollision (){
        return false;
    }

    public int getPercentCreationPredator() {
        return percentCreationPredator;
    }

    public int getPercentCreationHerbivores() {
        return percentCreationHerbivores;
    }

    public int getPercentCreatePlant() {
        return percentCreatePlant;
    }

    public Island getIsland (int x, int y) {
        return matrix[x][y];
    }
}
