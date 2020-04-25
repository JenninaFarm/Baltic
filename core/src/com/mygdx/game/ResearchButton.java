package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.I18NBundle;

/**
 * MapButton is an object base class to create create a buttons to ResearchScreen with inputListener.
 *
 * @author  Jennina Färm
 * @author  Tommi Häkkinen
 * @version 2020.2204
 * @since 1.8
 */
public class ResearchButton extends Actor {

    /**
     * Array that contains booleans if ResearchButtons are bought or not
     */
    private static boolean [] researchBought = new boolean [19];
    /**
     * Main for money handling and asking I18NBundle
     */
    private Main main;
    /**
     * ResearchScreen for InfoLabel
     */
    private ResearchScreen researchScreen;
    /**
     * Button that created and drawn
     */
    private Button button1;
    /**
     * Index to identify the Button
     */
    private int index;
    /**
     * Cost of the ResearchButton
     */
    private int cost;
    /**
     * Boolean to know if the ResearchButton is available to buy
     */
    private boolean available = false;
    /**
     * Actor for showing information about the research
     */
    private InfoLabel infoLabel;

    /**
     * Constructor. Creates all the private variables and sets x- and y-coordinates, width and height of the ResearchButton.
     * It contains anonymous InputListener to detect touchDown, enter and exit of the ResearchButton to buy the Button or set InfoLabel visible or not.
     *
     * @param m Main contains meta data of the game
     * @param rs ResearchScreen handling InfoLabel
     * @param i Index of the ResearchButton
     */
    ResearchButton(Main m, ResearchScreen rs, int i) {
        main = m;
        researchScreen = rs;
        index = i;

        I18NBundle myBundle = main.getMyBundle();
        cost = Integer.parseInt(myBundle.get("researchCost" + index));

        Skin mySkin = new Skin(Gdx.files.internal("mySkinTest/mySkinTest.json"));


        float width = 200;
        float height = 60;

        button1 = new TextButton(myBundle.get("research" + i), mySkin);
        button1.setSize(width, height);
        button1.setPosition(Integer.parseInt(myBundle.get("researchX" + i)), Integer.parseInt(myBundle.get("researchY" + i)));

        if(researchBought[index]) {
            button1.getStyle().checked = button1.getStyle().down;
            button1.setChecked(true);
            button1.setDisabled(true);

        } else if(available){
            button1.setChecked(false);
        } else {
            button1.setChecked(true);
            button1.setDisabled(true);
        }

        button1.addListener(new InputListener() {

            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                int currentMoney = main.nonStaticGetMoney();
                //uncomment this to set InfoLabel invisible in desktop
                //researchScreen.setInfoVisible(false);

                if(currentMoney >= cost && !researchBought[index] && available) {
                    System.out.println("bought");
                    main.nonStaticSetMoney(currentMoney - cost);
                    button1.getStyle().checked = button1.getStyle().down;
                    button1.setChecked(true);
                    button1.setDisabled(true);
                    researchBought[index] = true;
                }
                return false;
            }

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                I18NBundle myBundle = main.getMyBundle();
                infoLabel = new InfoLabel(main, myBundle.get("researchInfo" + index), 20, 50, 300, 310);
                researchScreen.addInfoLabel(infoLabel);
                researchScreen.setInfoVisible(true);
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                researchScreen.setInfoVisible(false);
            }
        });
    }

    /**
     * Set -method to receive updated researchBought array.
     *
     * @param research Array that is set to be researchBought
     */
    static void setBooleanArray(boolean[] research) {
        researchBought = research;
    }

    /**
     * Get -method to collect the researchBought array.
     *
     * @return Array that contains if the ResearchButton have been bought or not
     */
    static boolean [] getResearchBooleans() {
        return researchBought;
    }

    /**
     * Sets researchButton available if research is bought already
     */
    void setResearchAvailable() {
        available = true;
        if(!researchBought[index]) {
            button1.setChecked(false);
            button1.setDisabled(false);
        }
    }

    /**
     * Get -method to collect Button of the object.
     *
     * @return TextButton that is created
     */
    public Button getButton() {
        return button1;
    }
}
