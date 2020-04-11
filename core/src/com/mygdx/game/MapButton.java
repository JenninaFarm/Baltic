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
    private Texture notBought;
    private InfoLabel infoLabel;
    private boolean bought = false;
    private float width;
    private float height;
    private int index;
    private int cost;

    public MapButton(Main m, MapScreen ms, int x, int y, int i) {

        index = i;
        main = m;
        mapScreen = ms;
        cost = (int)(1000* Math.pow(10, (i-1)));
        System.out.println(cost);
        button = new Texture(Gdx.files.internal("farm-icon.png"));
        notBought = new Texture(Gdx.files.internal("test_icon.jpg"));
        setX(x);
        setY(y);
        width = button.getWidth()/7f;
        height = button.getHeight()/7f;
        setWidth(width);
        setHeight(height);
        setBounds(getX(), getY(), getWidth(), getHeight());

        addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                if(bought) {
                    System.out.println("to farm");
                    main.switchScreen(3, index);
                } else {
                    int currentMoney = main.nonStaticGetMoney();
                    boolean [] researchBooleans = ResearchButton.getResearchBooleans();
                    if(index == 2) {
                        //requires livestock research
                        if(researchBooleans[6]) {
                            if(currentMoney >= cost) {
                                bought = true;
                                main.setMoney(currentMoney-cost);
                                mapScreen.addCoin(index);
                            }
                        }
                    } else if(index == 3) {
                        //requires organic farm research
                        if(researchBooleans[16]) {
                            if(currentMoney >= cost) {
                                bought = true;
                                main.setMoney(currentMoney-cost);
                                mapScreen.addCoin(index);
                            }
                        }
                    } else {
                        if (currentMoney >= cost) {
                            bought = true;
                            main.setMoney(currentMoney - cost);
                            mapScreen.addCoin(index);
                        }
                    }
                }
                return true;

            }

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                if(!bought) {
                    I18NBundle myBundle = main.getMyBundle();
                    infoLabel = new InfoLabel(main, myBundle.get("farmInfo" + index));
                    mapScreen.addInfoLabel(infoLabel);
                }
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                mapScreen.clearInfoLabel();

            }
        });
    }

    public void draw(Batch batch, float alpha) {
        if(!bought && index != 0) {
            batch.draw(notBought, this.getX(), this.getY(), width, height);
        } else {
            batch.draw(button, this.getX(), this.getY(), width, height);
            //sets the first farm bought
            bought = true;
        }
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }
}
