package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Meter extends Actor {

    private Main main;
    private Texture meterTexture;
    private float width;
    private float height;

    public Meter(Main m) {
        main = m;
        meterTexture = new Texture(Gdx.files.internal("meter.png"));
        setX(730);
        setY(30);
        width = meterTexture.getWidth()/4f;
        height = meterTexture.getHeight()/4f;
        setWidth(width);
        setHeight(height);
        setBounds(getX(), getY(), getWidth(), getHeight());
    }

    public void draw(Batch batch, float alpha) {
        batch.draw(meterTexture, this.getX(), this.getY(), width, height);
    }
}