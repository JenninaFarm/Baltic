package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.I18NBundle;

import static java.lang.Float.parseFloat;

public class FarmButton extends Actor {
    private Main main;
    private FarmScreen farmScreen;
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

    public FarmButton(Main m, FarmScreen fs, final int buttonI, int farmI) {
        main = m;
        farmScreen = fs;
        buttonIndex = buttonI;
        farmIndex = farmI;

        I18NBundle myBundle = main.getMyBundle();
        cost = Integer.parseInt(myBundle.get("researchCost" + buttonIndex)) / 2;
        multiplier = parseFloat(myBundle.get("upgradeMultiplier" + buttonIndex));
        balticSituation = Integer.parseInt(myBundle.get("upgradeBaltic" + buttonIndex));

        Skin mySkin = new Skin(Gdx.files.internal("mySkinTest/mySkinTest.json"));

        float width = 200;
        float height = 50;

        setX(550);
        setY(320 - height/2f - buttonIndex*height);

        button1 = new TextButton(myBundle.get("upgrade" + buttonIndex), mySkin);
        button1.setSize(width, height);
        button1.setPosition(getX(), getY());
        setBounds(getX(), getY(), getWidth(), getHeight());

        if(bought[farmIndex][buttonIndex]) {
            button1.setVisible(false);
        } else if(available){
            button1.setChecked(false);
        } else {
            button1.getStyle().checked = button1.getStyle().checkedOver;
            button1.setChecked(true);
            button1.setDisabled(true);
        }

        button1.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                int currentMoney = main.getMoney();
                if(currentMoney < cost || bought[farmIndex][buttonIndex] || !available) {
                    button1.setDisabled(true);
                } else {
                    button1.setDisabled(false);
                }
                if(available && currentMoney >= cost && !bought[farmIndex][buttonIndex]) {
                    System.out.println("bought");
                    bought[farmIndex][buttonIndex] = true;
                    farmScreen.setFarmButtonY(buttonI);
                    //set button style
                    button1.getStyle().checked = button1.getStyle().over;
                    button1.setChecked(true);
                    button1.getStyle().checkedOver = button1.getStyle().up;
                    button1.setDisabled(true);
                    button1.setVisible(false);
                    //set new amount of money and balticSituation
                    main.setMoney(currentMoney-cost);
                    main.addBalticSituation(balticSituation);
                    //set
                    MoneyButton.addToMultiplier(multiplier, farmIndex);
                    MoneyButton.addToMaxAmount(cost/2, farmIndex);
                    //save and load
                    Save.saveVariables();
                    Save.loadVariables();
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
        button1.setDisabled(true);
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
            button1.setDisabled(false);
            available = true;
        }
    }

    public void draw(Batch batch, float alpha) {
        if(bought[farmIndex][0]) {
            Texture wheat = new Texture(Gdx.files.internal("farmUpgrades/wheat.png"));
            batch.draw(wheat, 250, 200, wheat.getWidth()/2.5f, wheat.getHeight()/2.5f);
        }
        if(bought[farmIndex][3]) {
            Texture strawberry = new Texture(Gdx.files.internal("farmUpgrades/strawberry.png"));
            batch.draw(strawberry, -20, -20, strawberry.getWidth()/2.5f, strawberry.getHeight()/2.5f);
        }
        if(bought[farmIndex][11]) {
            Texture flowerSide = new Texture(Gdx.files.internal("farmUpgrades/flowerSide.png"));
            batch.draw(flowerSide, 220, 145, flowerSide.getWidth()/2.6f, flowerSide.getHeight()/2.6f);
        }
        if(bought[farmIndex][4]) {
            Texture tractor = new Texture(Gdx.files.internal("farmUpgrades/tractor.png"));
            batch.draw(tractor, -10, 10, tractor.getWidth()/2.5f, tractor.getHeight()/2.5f);
        }
        if(bought[farmIndex][6]) {
            Texture cow = new Texture(Gdx.files.internal("farmUpgrades/cow.png"));
            batch.draw(cow, 340, 0, cow.getWidth()/5f, cow.getHeight()/5f);
        }
        if(bought[farmIndex][12]) {
            Texture bees = new Texture(Gdx.files.internal("farmUpgrades/bees.png"));
            batch.draw(bees, 370, 300, bees.getWidth()/2.5f, bees.getHeight()/2.5f);
        }
        if(bought[farmIndex][16]) {
            Texture organicFIN = new Texture(Gdx.files.internal("farmUpgrades/organicFIN.png"));
            batch.draw(organicFIN, 35, 324, organicFIN.getWidth()/2.5f, organicFIN.getHeight()/2.5f);
        }
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }
}
