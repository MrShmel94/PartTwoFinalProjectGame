package dao;

import dao.abstractClasses.Plant;
import enums.EntityTypes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.*;

public class Game extends GameConfiguration {

    // Создание игры, хар-ки поля, проценты создания животных, остальное опционально, кол-во ходов.
    private static final GameConfiguration game =  new GameConfiguration(20,100, 100,100, 100, 10);

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        // Стартовая информация и инициализация острова.
        startGame();

        ScheduledExecutorService executorServiceStat = Executors.newScheduledThreadPool(1);
        ScheduledExecutorService executorServiceLive = Executors.newScheduledThreadPool(40);


        Runnable task1 = () -> {
            game.getStep().incrementAndGet();
            game.startFullIslandGrowsPlant();
            game.fullInfoPerTurn();
            game.infoStartIsland();
        };

        Runnable task2 = () -> {
            game.startFullIslandMinusHp();
            game.startAllMovingsAnimal();
            game.startEatingPredatosMethodsPerFullIsland();
            game.startEatingHerbivoresMethodsPerFullIsland();
            game.startReproductionAnimalsMethodsPerFullIsland();
            game.startBirthOffspringMethodsPerFullIsland();
        };

        executorServiceLive.scheduleAtFixedRate(task2, 0, 200, TimeUnit.MILLISECONDS);
        executorServiceStat.scheduleWithFixedDelay(task1, 4000, 4500, TimeUnit.MILLISECONDS);

        while (!game.isStopped()) {
            if (game.getStep().get() == game.getTurn()) {
                game.setStopped(true);
            }
        }
        executorServiceLive.shutdown();
        executorServiceStat.shutdown();
    }

    public static void startGame (){
        game.drawGame();
        game.infoStartGame();
        game.infoStartIsland();
    }


}
