package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class Save {

    private static boolean [] research = ResearchButton.getResearchBooleans();
    private static boolean [][] farmAvailable = FarmButton.getAvailableBooleans();
    private static boolean [][] farmBought = FarmButton.getBoughtBooleans();
    private static float [] multipliers = MoneyButton.getMultipliers();
    private static boolean [] farmLocks = MapButton.getFarmLocks();
    private static int [] lastTimeClicked = MoneyButton.getLastTimeClicked();
    private static boolean [] coinAdded = MapScreen.getCoinAdded();
    private static int [] workerAmount = FarmScreen.getWorkerAmountArray();

    private static Preferences prefs = Gdx.app.getPreferences("baltic_savefile");

    private static int farmAmount = 4;
    private static int researchAmount = 19;

    public static void saveVariables() {

        prefs.putInteger("money", Main.getMoney());

        for(int i=0; i<farmAmount; i++) {
            prefs.putFloat("multiplier" + i, multipliers[i]);
        }

        for(int i=0; i<researchAmount; i++) {
            prefs.putBoolean("research" + i, research[i]);
        }

        for(int i=0; i<farmAmount; i++) {
            for(int j=0; j<researchAmount; j++) {
                prefs.putBoolean("farmavailable" + i + j, farmAvailable[i][j]);
            }
        }

        for(int i=0; i<farmAmount; i++) {
            for(int j=0; j<researchAmount; j++) {
                prefs.putBoolean("farmbought" + i + j, farmBought[i][j]);
            }
        }

        for(int i=0; i<farmAmount; i++) {
            prefs.putBoolean("farmlock" + i, farmLocks[i]);
        }

        //not working
        for(int i=0; i<farmAmount; i++) {
            prefs.putInteger("lastTimeClicked" + i, lastTimeClicked[i]);
        }

        for(int i=0; i<farmAmount; i++) {
            prefs.putBoolean("coinAdded" + i, coinAdded[i]);
        }

        for(int i=0; i<farmAmount; i++) {
            prefs.putInteger("workerAmount" + i, workerAmount[i]);
        }

        prefs.flush();
    }

    public static void loadVariables() {

        Main.setMoney(prefs.getInteger("money", 600000));
        Main.setGameBegan(prefs.getBoolean("gameBegan"));
        System.out.println(prefs.getInteger("startingTime"));

        for(int i=0; i<farmAmount; i++) {
            multipliers[i] = prefs.getFloat("multiplier" + i, 4);
        }
        MapScreen.setSavedMultipliers(multipliers);

        for(int i=0; i<researchAmount; i++) {
            research[i] = prefs.getBoolean("research" + i);
        }
        ResearchScreen.setBooleanArray(research);

        for(int i=0; i<farmAmount; i++) {
            for(int j=0; j<researchAmount; j++) {
                farmAvailable[i][j] = prefs.getBoolean("farmavailable" + i + j);
            }
        }
        FarmScreen.setAvailableArray(farmAvailable);

        for(int i=0; i<farmAmount; i++) {
            for(int j=0; j<researchAmount; j++) {
                farmBought[i][j] = prefs.getBoolean("farmbought" + i + j);
            }
        }
        FarmScreen.setBoughtArray(farmBought);

        for(int i=0; i<farmAmount; i++) {
            farmLocks[i] = prefs.getBoolean("farmlock" + i);
        }
        MapScreen.setFarmLocksArray(farmLocks);

        //not working
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
    }

    public static void newGame() {

        prefs.putInteger("money", 600000);
        prefs.flush();
        Main.setMoney(prefs.getInteger("money"));

        prefs.putBoolean("gameBegan", false);
        prefs.flush();
        Main.setGameBegan(prefs.getBoolean("gameBegan"));

        for(int i=0; i<farmAmount; i++) {
            prefs.putFloat("multiplier" + i, 4);
            prefs.flush();
            multipliers[i] = prefs.getFloat("multiplier" + i);
        }
        MapScreen.setSavedMultipliers(multipliers);

        for(int i=0; i<researchAmount; i++) {
            prefs.putBoolean("research" + i, false);
            prefs.flush();
            research[i] = prefs.getBoolean("research" + i);
        }
        ResearchScreen.setBooleanArray(research);

        for(int i=0; i<farmAmount; i++) {
            for(int j=0; j<researchAmount; j++) {
                prefs.putBoolean("farmavailable" + i + j, false);
                prefs.flush();
                farmAvailable[i][j] = prefs.getBoolean("farmavailable" + i + j);
            }
        }
        FarmScreen.setAvailableArray(farmAvailable);

        for(int i=0; i<farmAmount; i++) {
            for(int j=0; j<researchAmount; j++) {
                prefs.putBoolean("farmbought" + i + j, false);
                prefs.flush();
                farmBought[i][j] = prefs.getBoolean("farmbought" + i + j);
            }
        }
        FarmScreen.setBoughtArray(farmBought);

        for(int i=0; i<farmAmount; i++) {
            prefs.putBoolean("farmlock" + i, false);
            prefs.flush();
            farmLocks[i] = prefs.getBoolean("farmlock" + i);
        }
        MapScreen.setFarmLocksArray(farmLocks);

        //not tested
        for(int i=0; i<farmAmount; i++) {
            prefs.remove("lastTimeClicked" + i);
            prefs.flush();
        }
        //pitäskö tähän laittaa set???

        for(int i=0; i<farmAmount; i++) {
            prefs.putBoolean("coinAdded" + i, false);
            prefs.flush();
            coinAdded[i] = prefs.getBoolean("coinAdded" + i);
        }
        MapScreen.setCoinAdded(coinAdded);

        for(int i=0; i<farmAmount; i++) {
            prefs.putInteger("workerAmount" + i, 1);
            prefs.flush();
            workerAmount[i] = prefs.getInteger("workerAmount" + i);
        }
        FarmScreen.setWorkerAmountArray(workerAmount);
    }

    public static void saveStartingTime() {
        prefs.putInteger("startingTime", Main.getStartingTime());
        prefs.putBoolean("gameBegan", true);
        prefs.flush();
    }
}
