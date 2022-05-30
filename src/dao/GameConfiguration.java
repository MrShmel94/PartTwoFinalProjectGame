package dao;

import dao.abstractClasses.HerbivoresAnimal;
import dao.abstractClasses.Plant;
import dao.abstractClasses.PredatorAnimal;
import dao.abstractClasses.Animal;
import dao.factories.HerbivoresAnimalFactory;
import dao.factories.PredatorAnimalFactory;
import enums.EntityTypes;
import enums.Sex;
import javafx.util.Pair;

import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;
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
    private AtomicInteger step;
    private ConcurrentHashMap <String, Long> deadPerTurn = new ConcurrentHashMap<>();
    private ConcurrentHashMap <String, Integer> reproductionsPerTurn = new ConcurrentHashMap<>();
    private ConcurrentHashMap <String, Integer> deadPlantsPerTurn = new ConcurrentHashMap<>();
    private ConcurrentHashMap <String, Integer> reproductionsPlantsPerTurn = new ConcurrentHashMap<>();
    private boolean clear = false;

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

    public GameConfiguration(int width, int height, int percentCreationHerbivores , int percentCreationPredator, int percentCreatePlant, int... params) {
        if (params.length == 0){
            this.turn = 20;
        }else {
            this.turn = params[0];
        }
        this.step = new AtomicInteger(0);
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
                Island island = new Island(this);
                island.setPair(new MyPair(i,j));
                matrix[i][j] = island;
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


    //Метод появления потомства в клетке
    public void birthOffspring(Island island) {
        ConcurrentHashMap<EntityTypes, List<Animal>> values = island.getAllAnimals();
        ConcurrentHashMap<String, Integer> result = new ConcurrentHashMap<>();

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
                    result.merge(en.getIcon(), 1, Integer::sum);
                }
            }
        }
        island.setAllAnimals(values);
        fullInfoCountOffspringAnimalsPerTurn(result);
    }


    // Метод размножения в клетке
    public void reproductionAnimals (Island island){
        ConcurrentHashMap<EntityTypes, List<Animal>> values = island.getAllAnimals();
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
        island.setAllAnimals(values);
    }

    // Метод отнимания здоровья каждый ход, у всех животных на всей клетке.
    public void minusHP (Island island){
        island.getAllAnimals().values().stream().map(Collection::stream).forEach(a -> a.forEach(Animal::minusHpPerTurn));
    }

    public void startFullIslandMinusHp (){
        Arrays.stream(matrix).flatMap(Arrays::stream).forEach(this::minusHP);
    }

    public void startFullIslandGrowsPlant (){
        Arrays.stream(matrix).flatMap(Arrays::stream).forEach(Island::grassGrowth);
    }

    //Метод поедания травоядных
    public void fullHerbivoresEatingInCellQ(Island island) {
        ConcurrentHashMap<EntityTypes, List<Animal>> herbivoresAnimal = island.getAllAnimals().entrySet().stream().filter(a -> a.getKey().getType().equalsIgnoreCase("Herbivores"))
                .collect(Collectors.toMap(Entry::getKey, Entry::getValue, (a, b) -> a, ConcurrentHashMap::new));
        ConcurrentHashMap<EntityTypes, List<Plant>> allPlants = island.getAllPlants();
        CopyOnWriteArrayList<Plant> allPlantsValue = allPlants.values().stream().flatMap(Collection::stream).collect(Collectors.toCollection(CopyOnWriteArrayList::new));
        Collections.shuffle(allPlantsValue);
        CopyOnWriteArrayList<EntityTypes> keySetAllAnimal = new CopyOnWriteArrayList<>(herbivoresAnimal.keySet());
        Collections.shuffle(keySetAllAnimal);
        while (herbivoresAnimal.values().stream().flatMap(Collection::stream).noneMatch(Animal::isChecked)
                //herbivoresAnimal.values().stream().flatMap(Collection::stream).noneMatch(a -> a.getMaxHealPoint() == a.getHP())
        ) {
            if (herbivoresAnimal.values().stream().mapToLong(Collection::size).sum() == 0){
                break;
            }
            for (int i = 0; i < keySetAllAnimal.size(); i++) {
                if (herbivoresAnimal.get(keySetAllAnimal.get(i)).stream().allMatch(Animal::isChecked)){
                    continue;
                }
                if (!fullEating(herbivoresAnimal.get(keySetAllAnimal.get(i)))) {
                    if (i != keySetAllAnimal.size() - 1) {
                        if (AllStatistics.get(keySetAllAnimal.get(i), keySetAllAnimal.get(i + 1))) {
                            for (Animal animalWho : herbivoresAnimal.get(keySetAllAnimal.get(i))) {
                                HerbivoresAnimal whoEat = (HerbivoresAnimal) animalWho;
                                for (Animal animalWhom : herbivoresAnimal.get(keySetAllAnimal.get(i + 1))) {
                                    if (whoEat.getHP() != whoEat.getMaxHealPoint()) {
                                        whoEat.eat(animalWhom);
                                    }
                                }
                            }
                        }
                    }
                    for (Animal animal : herbivoresAnimal.get(keySetAllAnimal.get(i))) {
                        if (allPlantsValue.stream().allMatch(Plant::isDead) || allPlants.isEmpty()) {
                            allPlantsValue.clear();
                            herbivoresAnimal.get(keySetAllAnimal.get(i)).forEach(an -> an.setChecked(true));
                            break;
                        }
                        HerbivoresAnimal whoEat = (HerbivoresAnimal) animal;
                        for (Plant plant : allPlantsValue) {
                            if (plant.isDead()) {
                                allPlantsValue.remove(plant);
                                deadPlantsPerTurn.merge(plant.getType().getIcon(), 1, Integer::sum);
                            }
                            if (whoEat.getHP() != whoEat.getMaxHealPoint()) {
                                whoEat.eat(plant);
                            } else {
                                break;
                            }
                        }
                    }
                }else {
                    herbivoresAnimal.get(keySetAllAnimal.get(i)).forEach(animal -> animal.setChecked(true));
                }
            }
        }
        //getInfoDeadTypeAnimalPerTurn(island).entrySet().forEach(System.out::println);
        fullInfoCountDeadAnimalsPerTurn(getInfoDeadTypeAnimalPerTurn(island));
        herbivoresAnimal = clearingMap(herbivoresAnimal);
        ConcurrentHashMap <EntityTypes, List<Animal>> allMap = island.getAllAnimals();
        herbivoresAnimal.forEach((k, v) -> allMap.merge(k, v, (v1, v2) -> v2));
        allMap.values().stream().flatMap(Collection::stream).forEach(animal -> animal.setChecked(false));
        island.setAllAnimals(clearingMap(allMap));
    }

    public void fullInfoCountDeadAnimalsPerTurn(ConcurrentHashMap<String, Long> values) {
        if (this.clear){
            deadPerTurn.clear();
            values.forEach((k, v) -> deadPerTurn.merge(k , v, Long::sum));
        }else {
            values.forEach((k, v) -> deadPerTurn.merge(k , v, Long::sum));
        }
    }

    public void fullInfoCountOffspringAnimalsPerTurn(ConcurrentHashMap<String, Integer> values) {
        if (this.clear){
            reproductionsPerTurn.clear();
            values.forEach((k, v) -> reproductionsPerTurn.merge(k , v, Integer::sum));
        }else {
            values.forEach((k, v) -> reproductionsPerTurn.merge(k , v, Integer::sum));
        }
    }

    // Полная информация за ход.
    public void fullInfoPerTurn (){
        System.out.println("\nStep - " + step.get() + "; Type Animals - (Dead/Was Born) + Type Plants");
        deadPerTurn.forEach((key, value) -> System.out.print(key + "-(" + value + "/" + reproductionsPerTurn.getOrDefault(key, 0) + ") "));
        deadPlantsPerTurn.forEach((key, value) -> System.out.print(key + "-(" + value + "/" + reproductionsPlantsPerTurn.getOrDefault(key, 0) + ") "));
        reproductionsPerTurn.clear();
        deadPerTurn.clear();
        deadPlantsPerTurn.clear();
        reproductionsPlantsPerTurn.clear();
    }

    //Метод поедания хищниками
    public void fullPredatorsEatingInCell (Island island){
        ConcurrentHashMap<EntityTypes, List<Animal>> valueCell = island.getAllAnimals();
        CopyOnWriteArrayList<EntityTypes>  allTypesAnimalsCell = new CopyOnWriteArrayList<>(new CopyOnWriteArrayList<>(valueCell.keySet()));
        CopyOnWriteArrayList <EntityTypes> shuffleListPredators = new CopyOnWriteArrayList<>(valueCell.keySet().stream().filter(a -> a.getType().equalsIgnoreCase("Predator")).collect(Collectors.toCollection(CopyOnWriteArrayList::new)));
        Collections.shuffle(shuffleListPredators);

        while (!shuffleListPredators.isEmpty()
               // !valueCell.values().stream().flatMap(Collection::stream).filter(a -> a.getKind().getType().equalsIgnoreCase("Predator")).allMatch(Animal::isChecked) ||
                //!valueCell.values().stream().flatMap(Collection::stream).filter(a -> a.getKind().getType().equalsIgnoreCase("Predator")).allMatch(b -> b.getMaxHealPoint() == b.getHP())
        //|| valueCell.values().stream().flatMap(Collection::stream).noneMatch(Animal::isChecked)
        ) {
            for (EntityTypes en : shuffleListPredators) {
                if (valueCell.get(en).stream().noneMatch(Animal::isChecked)){
                    EntityTypes whom = shuffleList(allTypesAnimalsCell, en);
                    if (whom != null) {
                        List<Animal> whoEating = valueCell.get(en);
                        List<Animal> whomEating = valueCell.get(whom);
                        if (whoEating.stream().allMatch(Animal::isChecked) || whomEating.stream().allMatch(Animal::isChecked)){
                            whoEating.forEach(animal -> animal.setChecked(true));
                            shuffleListPredators.remove(en);
                            break;
                        }
                        for (Animal whoEat : whoEating) {
                            if (whoEat.getMaxHealPoint() == whoEat.getHP()) {
                                whoEat.setChecked(true);
                                continue;
                            }
                            if (whomEating.stream().allMatch(Animal::isChecked)){
                                break;
                            }
                            for (Animal animal : whomEating) {
                                if (animal.isChecked()){
                                    continue;
                                }
                                PredatorAnimal predatorAnimal = (PredatorAnimal) whoEat;
                                predatorAnimal.eat(animal);
                                if (predatorAnimal.getHP() == predatorAnimal.getMaxHealPoint()) {
                                    predatorAnimal.setChecked(true);
                                    break;
                                }
                            }
                        }
                    }
                } else {
                    valueCell.get(en).forEach(animal -> animal.setChecked(true));
                    shuffleListPredators.remove(en);
                }
            }
        }
        //getInfoDeadTypeAnimalPerTurn(island).entrySet().forEach(System.out::println);
        fullInfoCountDeadAnimalsPerTurn(getInfoDeadTypeAnimalPerTurn(island));
        valueCell = clearingMap(valueCell);
        valueCell.values().stream().flatMap(Collection::stream).forEach(animal -> animal.setChecked(false));
        island.setAllAnimals(clearingMap(valueCell));
    }

    //Метод удаляет все мертвые объекты и возвращает новую мапу без них.
    public ConcurrentHashMap<EntityTypes, List<Animal>> clearingMap (Map<EntityTypes, List<Animal>> array){
        return array.entrySet().stream().collect(Collectors.toMap(Entry::getKey, b -> b.getValue().stream().filter(a -> !a.isDead()).collect(Collectors.toList()), (a,b) -> Stream.concat(a.stream(), b.stream()).collect(Collectors.toList()), ConcurrentHashMap::new));
    }

    public ConcurrentHashMap<String, Long> getInfoCountReproductionPerTurn (Island island){
        return island.getAllAnimals().entrySet().stream().collect(Collectors.toMap(key -> key.getKey().getIcon(), val -> val.getValue().stream().filter(Animal::isPregnant).count(), Long::sum , ConcurrentHashMap::new));
    }

    public ConcurrentHashMap<String, Long> getInfoDeadTypeAnimalPerTurn (Island island){
        return island.getAllAnimals().entrySet().stream().collect(Collectors.toMap(key -> key.getKey().getIcon(), val -> val.getValue().stream().filter(Animal::isDead).count(), Long::sum , ConcurrentHashMap::new));
    }

    public ConcurrentHashMap <String, Long> getFullIslandInfoReproductionPerTurn(){
       return Arrays.stream(matrix).map(Arrays::stream).flatMap(a -> a.map(this::getInfoCountReproductionPerTurn)).flatMap(a -> a.entrySet().stream()).collect(Collectors.toMap(Entry::getKey, Entry::getValue, Long::sum, ConcurrentHashMap::new));
    }

    public ConcurrentHashMap <String, Long> getFullIslandInfoDeadTypeAnimalPerTurn(){
        return Arrays.stream(matrix).map(Arrays::stream).flatMap(a -> a.map(this::getInfoDeadTypeAnimalPerTurn)).flatMap(a -> a.entrySet().stream()).collect(Collectors.toMap(Entry::getKey, Entry::getValue, Long::sum, ConcurrentHashMap::new));
    }

    public ConcurrentHashMap<EntityTypes, Integer> getInfoTypeAndAmountCell(Island island){
          return Stream.concat(island.getAllAnimals().entrySet().stream(), island.getAllPlants().entrySet().stream()).collect(Collectors.toMap(Entry::getKey, b-> b.getValue().size(), Integer::sum, ConcurrentHashMap::new));
    }

    public String getInfoMaxValueAnimalPerCel (Island island){
        return Stream.concat(island.getAllAnimals().entrySet().stream(), island.getAllPlants().entrySet().stream()).max((en1 , en2) -> en1.getValue().size() > en2.getValue().size() ? 1 : -1).get().getKey().getIcon();
    }

    public String getInfoRandomValueAnimalPerCel (Island island){
        long count = Stream.concat(island.getAllAnimals().keySet().stream(), island.getAllPlants().keySet().stream()).count();
        return Stream.concat(island.getAllAnimals().keySet().stream(), island.getAllPlants().keySet().stream()).skip(ThreadLocalRandom.current().nextLong(1,count)).findFirst().get().getIcon();
    }


    public void startEatingPredatosMethodsPerFullIsland(){
        Arrays.stream(matrix).flatMap(Arrays::stream).forEach(this::fullPredatorsEatingInCell);
    }

    public void startEatingHerbivoresMethodsPerFullIsland(){
        Arrays.stream(matrix).flatMap(Arrays::stream).forEach(this::fullHerbivoresEatingInCellQ);
    }

    public void startReproductionAnimalsMethodsPerFullIsland(){
        Arrays.stream(matrix).flatMap(Arrays::stream).forEach(this::reproductionAnimals);
    }

    public void startBirthOffspringMethodsPerFullIsland(){
        Arrays.stream(matrix).flatMap(Arrays::stream).forEach(this::birthOffspring);
    }

    public void infoStartGame (){
        ConcurrentHashMap <String, Integer> value = Stream.of(Arrays.stream(matrix).map(Arrays::stream)).flatMap(x -> x.flatMap(q -> q.flatMap(a -> getInfoTypeAndAmountCell(a).entrySet().stream()))).collect(Collectors.toMap(key -> key.getKey().getIcon(), Entry::getValue, Integer::sum, ConcurrentHashMap::new));
        System.out.print("Start this game: Step 0 ");
        value.forEach((key, value1) -> System.out.printf("%s-%d pieces ", key, value1));
        System.out.println();
    }

    public void infoStartIsland (){
        System.out.println();
        Arrays.stream(matrix).forEach(a -> {
            Arrays.stream(a).forEach(b -> System.out.print(getInfoRandomValueAnimalPerCel(b)));
            System.out.println();
        });
    }

    public void startAllMovingsAnimal (){
        Arrays.stream(matrix).flatMap(Arrays::stream).forEach(this::movingAnimalPerCell);
    }

    //Метод передвижения животных в ячейке
    public void movingAnimalPerCell (Island island){
        CopyOnWriteArrayList <Animal> allAnimals = island.getAllAnimals().values().stream().flatMap(Collection::stream).collect(Collectors.toCollection(CopyOnWriteArrayList::new));
        for (Animal animal : allAnimals) {
            MyPair pair = animal.moving();
            if (isCollision(pair, island)) {
                MyPair islandPair = island.getPair();
                Island islandSecond = this.getIsland(pair.getX() + islandPair.getX(), pair.getY() + islandPair.getY());
                int countAnimal = Optional.ofNullable(islandSecond.getAllAnimals().get(animal.getKind())).orElse(new ArrayList<>()).size();
                if (countAnimal < AllStatistics.getValueMapMaxLimit(animal.getKind())){
                    if (islandSecond.getAllAnimals().containsKey(animal.getKind())) {
                        islandSecond.getAllAnimals().get(animal.getKind()).add(animal);
                        island.getAllAnimals().get(animal.getKind()).remove(animal);
                    }else {
                        List<Animal> animals = new ArrayList<>();
                        animals.add(animal);
                        islandSecond.getAllAnimals().put(animal.getKind(), animals);
                    }
                }
            }
        }
    }

    //The method checks if the animal can move in the given direction
    public boolean isCollision (MyPair pair, Island island){
        MyPair islandPair = island.getPair();
        if (islandPair.getX() + pair.getX() >= this.getWidth() || islandPair.getX() + pair.getX() <= 0) {
            return false;
        }else if (islandPair.getY() + pair.getY() >= this.getHeight() || islandPair.getY() + pair.getY() <= 0){
            return false;
        }
        return true;
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

    public boolean isStopped() {
        return isStopped;
    }

    public void setStopped(boolean stopped) {
        isStopped = stopped;
    }

    public AtomicInteger getStep() {
        return step;
    }

    public void setStep(AtomicInteger step) {
        this.step = step;
    }

    public boolean isClear() {
        return clear;
    }

    public void setClear(boolean clear) {
        this.clear = clear;
    }

    public ConcurrentHashMap<String, Integer> getReproductionsPlantsPerTurn() {
        return reproductionsPlantsPerTurn;
    }

    public void setReproductionsPlantsPerTurn(ConcurrentHashMap<String, Integer> reproductionsPlantsPerTurn) {
        this.reproductionsPlantsPerTurn = reproductionsPlantsPerTurn;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public long getTurn() {
        return turn;
    }
}
