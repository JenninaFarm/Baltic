package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class StartmenuBackground extends Actor {

    private Texture startmenubackground;

    public StartmenuBackground() {

        startmenubackground = new Texture(Gdx.files.internal("startscreen.png"));
    }

    public void draw(Batch batch, float alpha) {
        batch.draw(startmenubackground, this.getX(), this.getY(), 800, 450);
    }
}