package dao;

import dao.abstractClasses.Plant;
import enums.EntityTypes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.*;

public class Game extends GameConfiguration {

    private static final GameConfiguration game =  new GameConfiguration(3,20,100, 100,100, 100);

    public static void main(String[] args) throws InterruptedException {
        startGame();
        Island island = game.getIsland(0,0);
        while (!game.isStopped()) {
            if (game.getStep().get() == 20){
                game.setStopped(true);
            }
            game.startFullIslandMinusHp();
            game.getStep().incrementAndGet();
            game.startEatingPredatosMethodsPerFullIsland();
            game.startEatingHerbivoresMethodsPerFullIsland();
            game.startReproductionAnimalsMethodsPerFullIsland();
            game.startBirthOffspringMethodsPerFullIsland();
            game.startFullIslandGrowsPlant();
            game.fullInfoPerTurn();
            game.infoStartIsland();
            Thread.sleep(500);
        }

    }

    public static void startGame (){
        game.drawGame();
        game.infoStartGame();
        game.infoStartIsland();
    }
}
