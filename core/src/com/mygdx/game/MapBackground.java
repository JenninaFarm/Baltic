package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;

public class MapBackground extends Actor {

    private Texture map;

    public MapBackground() {

        map = new Texture(Gdx.files.internal("map.jpg"));
    }

    public void draw(Batch batch, float alpha) {
        batch.draw(map, this.getX(), this.getY(), 800, 450);
    }
}
