package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.utils.I18NBundle;

import java.awt.TextField;
import java.time.ZonedDateTime;

public class MapButton extends Actor {

    private Main main;
    private MapScreen mapScreen;
    private Texture button;
    private Texture lock;
    private InfoLabel infoLabel;
    private float width;
    private float height;
    private int index;
    private int cost;

    private static boolean [] bought = new boolean[4];

    public MapButton(Main m, MapScreen ms, int x, int y, int i, boolean b) {

        index = i;
        main = m;
        mapScreen = ms;
        cost = (int)(1000* Math.pow(10, (i-1)));
        button = new Texture(Gdx.files.internal("farm-icon.png"));
        lock = new Texture(Gdx.files.internal("lock-icon.png"));
        setX(x);
        setY(y);
        width = button.getWidth()/7f;
        height = button.getHeight()/7f;
        setWidth(width);
        setHeight(height);
        setBounds(getX(), getY(), getWidth(), getHeight());

        bought[index] = b;
        //set first farm bought
        if(index == 0) {
            bought[index] = true;
        }

        addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                if(bought[index]) {
                    System.out.println("to farm");
                    main.switchScreen(3, index);
                } else {
                    //mapScreen.setInfoVisible(false);
                    int currentMoney = main.nonStaticGetMoney();
                    boolean [] researchBooleans = ResearchButton.getResearchBooleans();
                    if(index == 2) {
                        //requires livestock research
                        if(researchBooleans[6]) {
                            if(currentMoney >= cost) {
                                bought[index] = true;
                                main.setMoney(currentMoney-cost);
                                mapScreen.addCoin(index);
                                Save.saveVariables();
                                Save.loadVariables();
                            }
                        }
                    } else if(index == 3) {
                        //requires organic farm research
                        if(researchBooleans[16]) {
                            if(currentMoney >= cost) {
                                bought[index] = true;
                                main.setMoney(currentMoney-cost);
                                mapScreen.addCoin(index);
                                Save.saveVariables();
                                Save.loadVariables();
                            }
                        }
                    } else {
                        if (currentMoney >= cost) {
                            bought[index] = true;
                            main.setMoney(currentMoney - cost);
                            mapScreen.addCoin(index);
                            Save.saveVariables();
                            Save.loadVariables();
                        }
                    }
                }
                return true;

            }

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                if(!bought[index]) {
                    I18NBundle myBundle = main.getMyBundle();
                    infoLabel = new InfoLabel(main, myBundle.get("farmInfo" + index), 20, 150, 270, 76);
                    mapScreen.addInfoLabel(infoLabel);
                    mapScreen.setInfoVisible(true);
                }
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                if(!bought[index]) {
                    mapScreen.setInfoVisible(false);
                }
            }
        });
    }

    public static boolean[] getFarmLocks() {
        return bought;
    }

    public void draw(Batch batch, float alpha) {
        batch.draw(button, this.getX(), this.getY(), width, height);
        if(!bought[index] && index != 0) {
            batch.draw(lock, this.getX()+8, this.getY()+15, width/1.5f, height/1.5f);
        }
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }
}
