package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

import java.awt.TextField;
import java.time.ZonedDateTime;

public class MapButton extends Actor {

    private Main main;
    private Texture button;
    private Texture notBought;
    private boolean bought = false;
    private float width;
    private float height;
    private int index;
    private int cost;

    public MapButton(Main m, int x, int y, int i) {

        index = i;
        main = m;
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
                    if(currentMoney >= cost) {
                        bought = true;
                        main.setMoney(currentMoney-cost);
                    }
                }
                return true;
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
