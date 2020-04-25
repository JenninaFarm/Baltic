package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;


public class Cloud extends Actor {

    private int originalX;
    private int originalY;
    private Texture cloudTexture;
    private float cloudSpeedX;
    private float cloudSpeedY;
    private float x;
    private float y;
    private float maxDistance;

    public Cloud(int a, int b, float max) {

        originalX = a;
        originalY = b;
        int random = MathUtils.random(3, 6);
        cloudSpeedX = 0.4f;
        cloudSpeedY = 0.2f;
        maxDistance = max;
        x = a;
        y = b;
        cloudTexture = new Texture(Gdx.files.internal("clouds/cloud" + MathUtils.random(1, 5) + ".png"));
        setX(a);
        setY(b);
        setWidth(cloudTexture.getWidth() / random);
        setHeight(cloudTexture.getHeight() / random);
    }

    public void cloudMove() {

        this.x += cloudSpeedX;
        this.y += cloudSpeedY;
        if (this.x >= maxDistance) {
            this.x = originalX;
            this.y = originalY;
        }
    }

    public void draw(Batch batch, float alpha) {

        batch.draw(this.cloudTexture, this.x, this.y, this.getWidth(), this.getHeight());
    }
}
