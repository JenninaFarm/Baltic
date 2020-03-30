package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class Save {

    private static boolean [] research = ResearchButton.getResearchBooleans();

    public static void saveVariables() {

        Preferences prefs =  Gdx.app.getPreferences("savefile");
        prefs.putInteger("money", Main.getMoney());

        for(int i=0; i<6; i++) {
            prefs.putBoolean("research" + i, research[i]);
        }

        prefs.flush();
    }

    public static void loadVariables() {

        Preferences prefs =  Gdx.app.getPreferences("savefile");
        Main.setMoney(prefs.getInteger("money", 600000));

        for(int i=0; i<6; i++) {
            research[i] = prefs.getBoolean("research" + i);
        }
        ResearchScreen.setBooleanArray(research);
    }
}
