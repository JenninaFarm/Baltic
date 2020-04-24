package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class Save {

    private static boolean [] research = ResearchButton.getResearchBooleans();
    private static boolean [][] farmBought = FarmButton.getBoughtArray();
    private static float [] multipliers = MoneyButton.getMultipliers();
    private static boolean [] farmLocks = MapButton.getFarmLocks();
    private static int [] lastTimeClicked = MoneyButton.getLastTimeClicked();
    private static boolean [] coinAdded = MapScreen.getCoinAdded();
    private static int [] workerAmount = FarmScreen.getWorkerAmountArray();
    private static int [] maxAmount = MoneyButton.getMaxAmount();
    private static float [][] farmActorY = FarmScreen.getFarmActorYArray();

    private static Preferences prefs = Gdx.app.getPreferences("baltic_savefile");

    private static int farmAmount = 4;
    private static int researchAmount = 19;

    public static void saveVariables() {

        prefs.putInteger("money", Main.getMoney());
        prefs.putInteger("balticSituation", Main.getBalticSituation());
        prefs.putBoolean("tutorial1", Tutorial.tutorial);

        for(int i=0; i<farmAmount; i++) {
            prefs.putFloat("multiplier" + i, multipliers[i]);
        }

        for(int i=0; i<researchAmount; i++) {
            prefs.putBoolean("research" + i, research[i]);
        }

        for(int i=0; i<farmAmount; i++) {
            for(int j=0; j<researchAmount; j++) {
                prefs.putBoolean("farmbought" + i + j, farmBought[i][j]);
            }
        }

        for(int i=0; i<farmAmount; i++) {
            prefs.putBoolean("farmlock" + i, farmLocks[i]);
        }

        for(int i=0; i<farmAmount; i++) {
            prefs.putInteger("lastTimeClicked" + i, lastTimeClicked[i]);
        }

        for(int i=0; i<farmAmount; i++) {
            prefs.putBoolean("coinAdded" + i, coinAdded[i]);
        }

        for(int i=0; i<farmAmount; i++) {
            prefs.putInteger("workerAmount" + i, workerAmount[i]);
        }

        for(int i=0; i<farmAmount; i++) {
            prefs.putInteger("maxAmount" + i, maxAmount[i]);
        }

        for(int i=0; i<farmAmount; i++) {
            for (int j = 0; j < researchAmount; j++) {
                prefs.putFloat("farmActorY" + i + j, farmActorY[i][j]);
            }
        }
        prefs.flush();
    }

    public static void loadVariables() {

        Main.setMoney(prefs.getInteger("money", 6000000));
        Main.setGameBegan(prefs.getBoolean("gameBegan"));
        Main.setBalticSituation(prefs.getInteger("balticSituation"));
        Tutorial.tutorial = prefs.getBoolean("tutorial1", true);

        for(int i=0; i<farmAmount; i++) {
            multipliers[i] = prefs.getFloat("multiplier" + i, 4);
        }
        MoneyButton.setMultipliers(multipliers);

        for(int i=0; i<researchAmount; i++) {
            research[i] = prefs.getBoolean("research" + i);
        }
        ResearchScreen.setBooleanArray(research);
        FarmButton.setResearched(research);

        for(int i=0; i<farmAmount; i++) {
            for(int j=0; j<researchAmount; j++) {
                farmBought[i][j] = prefs.getBoolean("farmbought" + i + j);
            }
        }
        FarmButton.setBoughtArray(farmBought);

        for(int i=0; i<farmAmount; i++) {
            farmLocks[i] = prefs.getBoolean("farmlock" + i);
        }
        MapScreen.setFarmLocksArray(farmLocks);

        for(int i=0; i<farmAmount; i++) {
            lastTimeClicked[i] = prefs.getInteger("lastTimeClicked" + i);
        }
        MoneyButton.setLastTimeClicked(lastTimeClicked);

        for(int i=0; i<farmAmount; i++) {
            coinAdded[i] = prefs.getBoolean("coinAdded" + i);
        }
        MapScreen.setCoinAdded(coinAdded);

        for(int i=0; i<farmAmount; i++) {
            workerAmount[i] = prefs.getInteger("workerAmount" + i);
        }
        FarmScreen.setWorkerAmountArray(workerAmount);

        for(int i=0; i<farmAmount; i++) {
            maxAmount[i] = prefs.getInteger("maxAmount" + i);
        }
        MoneyButton.setMaxAmount(maxAmount);

        for(int i=0; i<farmAmount; i++) {
            for(int j=0; j<researchAmount; j++) {
                farmActorY[i][j] = prefs.getFloat("farmActorY" + i + j);
            }
        }
        FarmScreen.setFarmActorYArray(farmActorY);
    }

    static void newGame() {

        prefs.putInteger("money", 6000000);
        prefs.flush();
        Main.setMoney(prefs.getInteger("money"));

        prefs.putBoolean("gameBegan", false);
        prefs.flush();
        Main.setGameBegan(prefs.getBoolean("gameBegan"));

        prefs.putInteger("balticSituation", 0);
        prefs.flush();
        Main.setBalticSituation(prefs.getInteger("balticSituation"));

        for(int i=0; i<farmAmount; i++) {
            if(i<5) {
                prefs.putFloat("multiplier" + i, 4);
            } else {
                prefs.putFloat("multiplier" + i, 10);
            }

            prefs.flush();
            multipliers[i] = prefs.getFloat("multiplier" + i);
        }
        MoneyButton.setMultipliers(multipliers);

        for(int i=0; i<researchAmount; i++) {
            prefs.putBoolean("research" + i, false);
            prefs.flush();
            research[i] = prefs.getBoolean("research" + i);
        }
        ResearchScreen.setBooleanArray(research);

        for(int i=0; i<farmAmount; i++) {
            for(int j=0; j<researchAmount; j++) {
                prefs.putBoolean("farmbought" + i + j, false);
                prefs.flush();
                farmBought[i][j] = prefs.getBoolean("farmbought" + i + j);
            }
        }
        FarmButton.setBoughtArray(farmBought);

        for(int i=0; i<farmAmount; i++) {
            prefs.putBoolean("farmlock" + i, false);
            prefs.flush();
            farmLocks[i] = prefs.getBoolean("farmlock" + i);
        }
        MapScreen.setFarmLocksArray(farmLocks);

        for(int i=0; i<farmAmount; i++) {
            prefs.putInteger("lastTimeClicked" + i, Utils.getCurrentTimeInSeconds());
            prefs.flush();
            lastTimeClicked[i] = prefs.getInteger("lastTimeClicked" + i);
        }
        MoneyButton.setLastTimeClicked(lastTimeClicked);

        for(int i=0; i<farmAmount; i++) {
            prefs.putBoolean("coinAdded" + i, false);
            prefs.flush();
            coinAdded[i] = prefs.getBoolean("coinAdded" + i);
        }
        MapScreen.setCoinAdded(coinAdded);

        for(int i=0; i<farmAmount; i++) {
            prefs.putInteger("workerAmount" + i, 0);
            prefs.flush();
            workerAmount[i] = prefs.getInteger("workerAmount" + i);
        }
        FarmScreen.setWorkerAmountArray(workerAmount);

        for(int i=0; i<farmAmount; i++) {
            if(i < 5) {
                prefs.putInteger("maxAmount" + i, 0);
            } else {
                prefs.putInteger("maxAmount" + i, 5000);
            }
            prefs.flush();
            maxAmount[i] = prefs.getInteger("maxAmount" + i);
        }
        MoneyButton.setMaxAmount(maxAmount);

        for(int i=0; i<farmAmount; i++) {
            for (int j = 0; j < researchAmount; j++) {
                prefs.putFloat("farmActorY" + i + j, (283 - j * 60));
                farmActorY[i][j] = prefs.getFloat("farmActorY" + i + j);
            }
        }
        FarmScreen.setFarmActorYArray(farmActorY);
    }

    public static boolean getTutorial() {
        return Tutorial.tutorial;
    }

    public static void saveStartingTime() {
        prefs.putInteger("startingTime", Main.getStartingTime());
        prefs.putBoolean("gameBegan", true);
        prefs.flush();
    }
}
