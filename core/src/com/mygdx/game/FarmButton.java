package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.I18NBundle;

import static java.lang.Float.parseFloat;

public class FarmButton extends Actor {
    private Main main;
    private Button button1;

    private int buttonIndex;
    private int farmIndex;

    private int cost;
    private float multiplier;
    private boolean available;
    private int balticSituation;

    private InfoLabel infoLabel;


    private static boolean [][] bought = new boolean[4][19];
    private static boolean [] researched = new boolean[19];

    public FarmButton(Main m, final int buttonI, int farmI) {
        main = m;
        buttonIndex = buttonI;
        farmIndex = farmI;

        I18NBundle myBundle = main.getMyBundle();
        cost = Integer.parseInt(myBundle.get("researchCost" + buttonIndex)) / 2;
        multiplier = parseFloat(myBundle.get("upgradeMultiplier" + buttonIndex));
        balticSituation = Integer.parseInt(myBundle.get("upgradeBaltic" + buttonIndex));

        Skin mySkin = new Skin(Gdx.files.internal("mySkinTest/mySkinTest.json"));

        float width = 200;
        float height = 50;

        setX(800/2f - width/2f);
        setY(380 - height/2f - buttonIndex*height);

        button1 = new TextButton(myBundle.get("research" + buttonIndex), mySkin);
        button1.setSize(width, height);
        button1.setPosition(getX(), getY());

        if(bought[farmIndex][buttonIndex]) {
            button1.getStyle().checked = button1.getStyle().over;
            button1.setChecked(true);
            button1.setDisabled(true);

        } else if(available){
            button1.setChecked(false);
        } else {
            button1.getStyle().checked = button1.getStyle().checkedOver;
            button1.setChecked(true);
            button1.setTouchable(Touchable.disabled);
        }

        button1.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                int currentMoney = main.getMoney();
                if(currentMoney < cost) {
                    button1.setDisabled(true);
                } else {
                    button1.setDisabled(false);
                }
                if(available && currentMoney >= cost && !bought[farmIndex][buttonIndex]) {
                    System.out.println("bought");
                    bought[farmIndex][buttonIndex] = true;
                    //set button style
                    button1.getStyle().checked = button1.getStyle().over;
                    button1.setChecked(true);
                    button1.setDisabled(true);
                    //set new amount of money and balticSituation
                    main.setMoney(currentMoney-cost);
                    main.addBalticSituation(balticSituation);
                    //set
                    MoneyButton.addToMultiplier(multiplier, farmIndex);
                    MoneyButton.addToMaxAmount(cost/2, farmIndex);
                    //save and load
                    Save.saveVariables();
                    Save.loadVariables();
                } else {
                    System.out.println("Not enough money!");
                    System.out.println("cost: " + cost);
                    System.out.println("current balance: " + main.getMoney());
                }

                return true;
            }

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                I18NBundle myBundle = main.getMyBundle();
                infoLabel = new InfoLabel(main, myBundle.get("researchInfo" + buttonIndex));
                main.addFarmScreenStage(infoLabel, farmIndex);
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                main.clearInfoLabel(farmIndex);
            }
        });
    }

    public static void setResearched(boolean [] array) {
        researched = array;
    }

    public void setUnavailable() {
        available = false;
        button1.getStyle().checked = button1.getStyle().checkedOver;
        button1.setChecked(true);
        button1.setTouchable(Touchable.disabled);
    }

    public Button getButton() {
        return button1;
    }

    public static boolean [][] getBoughtArray() {
        return bought;
    }

    public static void setBoughtArray(boolean [][] array) { bought = array; }

    public void setAvailable() {
        if(researched[buttonIndex] && !bought[farmIndex][buttonIndex]) {
            button1.setChecked(false);
            button1.setTouchable(Touchable.enabled);
            available = true;
        }
    }

    public void draw(Batch batch, float alpha) {
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }
}
