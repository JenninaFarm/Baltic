package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class Save {

    private static boolean [] research = ResearchButton.getResearchBooleans();
    private static boolean [][] farmAvailable = FarmButton.getAvailableBooleans();
    private static boolean [][] farmBought = FarmButton.getBoughtBooleans();
    private static float [] multipliers = MoneyButton.getMultipliers();

    private static Preferences prefs = Gdx.app.getPreferences("baltic_savefile");

    private static int farmAmount = 5;
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

        prefs.flush();
    }

    public static void loadVariables() {

        Main.setMoney(prefs.getInteger("money", 600000));

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
    }

    public static void newGame() {

        prefs.putInteger("money", 600000);
        Main.setMoney(prefs.getInteger("money"));

        for(int i=0; i<farmAmount; i++) {
            prefs.putFloat("multiplier" + i, 4);
            multipliers[i] = prefs.getFloat("multiplier" + i);
        }
        MapScreen.setSavedMultipliers(multipliers);

        for(int i=0; i<researchAmount; i++) {
            prefs.putBoolean("research" + i, false);
            research[i] = prefs.getBoolean("research" + i);
        }
        ResearchScreen.setBooleanArray(research);

        for(int i=0; i<farmAmount; i++) {
            for(int j=0; j<researchAmount; j++) {
                prefs.putBoolean("farmavailable" + i + j, false);
                farmAvailable[i][j] = prefs.getBoolean("farmavailable" + i + j);
            }
        }
        FarmScreen.setAvailableArray(farmAvailable);

        for(int i=0; i<farmAmount; i++) {
            for(int j=0; j<researchAmount; j++) {
                prefs.putBoolean("farmbought" + i + j, false);
                farmBought[i][j] = prefs.getBoolean("farmbought" + i + j);
            }
        }
        FarmScreen.setBoughtArray(farmBought);
    }
}
