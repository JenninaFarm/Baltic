package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Boat extends Actor {

    private Texture texture;
    private float width;
    private float height;

    public Boat(int x, int y) {

        texture = new Texture(Gdx.files.internal("boat-icon.png"));
        setX(x);
        setY(y);
        width = texture.getWidth() / 8f;
        height = texture.getHeight() / 8f;
        setWidth(width);
        setHeight(height);
    }

    public void draw(Batch batch, float alpha) {
        batch.draw(texture, this.getX(), this.getY(), width, height);
    }
}
