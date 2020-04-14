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
    private boolean researched = false;
    private boolean readyToUpgrade = false;
    private boolean available;
    private boolean bought;
    private int balticSituation;

    private InfoLabel infoLabel;


    private static boolean [][] availableBooleans = new boolean[4][19];
    private static boolean [][] boughtBooleans = new boolean[4][19];

    public FarmButton(Main m, final int buttonI, int farmI, boolean a, boolean b) {
        main = m;
        buttonIndex = buttonI;
        farmIndex = farmI;
        available = a;
        bought = b;

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

        if(bought) {
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
                if(available && currentMoney >= cost && !bought) {
                    System.out.println("bought");
                    bought = true;
                    button1.getStyle().checked = button1.getStyle().over;
                    button1.setChecked(true);
                    button1.setDisabled(true);
                    main.setMoney(currentMoney-cost);
                    main.addBalticSituation(balticSituation);
                    MoneyButton.addToMultiplier(multiplier, farmIndex);
                    MoneyButton.addToMaxAmount(cost/2, farmIndex);
                    boughtBooleans[farmIndex][buttonIndex] =  true;
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

    public void setResearched() {
        researched = true;
        if(!bought && readyToUpgrade) {
            button1.setChecked(false);
            button1.setTouchable(Touchable.enabled);
            available = true;
            availableBooleans[farmIndex][buttonIndex] = true;
        }
    }

    public void setReadyToUpgrade() {
        readyToUpgrade = true;
        if(researched && !bought) {
            button1.setChecked(false);
            button1.setTouchable(Touchable.enabled);
            available = true;
            availableBooleans[farmIndex][buttonIndex] = true;
        }
    }

    public void setUnavailable() {
        available = false;
        button1.getStyle().checked = button1.getStyle().checkedOver;
        button1.setChecked(true);
        button1.setTouchable(Touchable.disabled);
        availableBooleans[farmIndex][buttonIndex] = false;
    }

    public Button getButton() {
        return button1;
    }

    public static boolean [][] getAvailableBooleans() {
        return availableBooleans;
    }

    public static boolean [][] getBoughtBooleans() {
        return boughtBooleans;
    }

    public void draw(Batch batch, float alpha) {
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }
}
