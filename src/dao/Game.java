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

    public static void main(String[] args) throws InterruptedException {
        // Стартовая информация и инициализация острова.
        startGame();

        ExecutorService executorService = Executors.newCachedThreadPool();

            while (!game.isStopped()) {
            if (game.getStep().get() == game.getTurn()) {
                game.setStopped(true);
            }
            game.startFullIslandMinusHp();
            game.getStep().incrementAndGet();
            game.startAllMovingsAnimal();
            game.startEatingPredatosMethodsPerFullIsland();
            game.startEatingHerbivoresMethodsPerFullIsland();
            game.startReproductionAnimalsMethodsPerFullIsland();
            game.startBirthOffspringMethodsPerFullIsland();
            game.startFullIslandGrowsPlant();
            game.fullInfoPerTurn();
            game.infoStartIsland();
        }

        executorService.shutdown();

    }

    public static void startGame (){
        game.drawGame();
        game.infoStartGame();
        game.infoStartIsland();
    }
}
