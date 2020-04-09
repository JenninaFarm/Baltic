package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Meter extends Actor {

    private Main main;
    private Texture [] meterTexture;
    private float width;
    private float height;

    public Meter(Main m) {
        main = m;
        meterTexture = new Texture[4];
        meterTexture[0] = new Texture(Gdx.files.internal("meters/meter-red.png"));
        meterTexture[1] = new Texture(Gdx.files.internal("meters/meter-orange.png"));
        meterTexture[2] = new Texture(Gdx.files.internal("meters/meter-yellow.png"));
        meterTexture[3] = new Texture(Gdx.files.internal("meters/meter-green.png"));

        setX(730);
        setY(30);
        width = meterTexture[0].getWidth()/4f;
        height = meterTexture[0].getHeight()/4f;
        setWidth(width);
        setHeight(height);
        setBounds(getX(), getY(), getWidth(), getHeight());
    }

    public void draw(Batch batch, float alpha) {
        int balticSituation = main.getBalticSituation();
        if(balticSituation < 30) {
            batch.draw(meterTexture[0], this.getX(), this.getY(), width, height);
        } else if(balticSituation < 60) {
            batch.draw(meterTexture[1], this.getX(), this.getY(), width, height);
        } else if (balticSituation < 90) {
            batch.draw(meterTexture[2], this.getX(), this.getY(), width, height);
        } else {
            batch.draw(meterTexture[3], this.getX(), this.getY(), width, height);
        }
    }
}