package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class MapBackground extends Actor {

    private Texture map1;
    private Texture map2;
    private Texture map3;
    private Texture map4;

    public MapBackground() {
        map1 = new Texture(Gdx.files.internal("mapBackGrounds/map.png"));
        map2 = new Texture(Gdx.files.internal("mapBackGrounds/map2.png"));
        map3 = new Texture(Gdx.files.internal("mapBackGrounds/map3.png"));
        map4 = new Texture(Gdx.files.internal("mapBackGrounds/map4.png"));
    }

    public void draw(Batch batch, float alpha) {
        if (Main.getBalticSituation() < 25) {
            batch.draw(map4, getX(), getY(), getWidth(), getHeight());
        } else if (Main.getBalticSituation() >= 25 && Main.getBalticSituation() < 50) {
            batch.draw(map3, getX(), getY(), getWidth(), getHeight());
        } else if (Main.getBalticSituation() >= 50 && Main.getBalticSituation() < 75) {
            batch.draw(map2, getX(), getY(), getWidth(), getHeight());
        } else {
            batch.draw(map1, getX(), getY(), getWidth(), getHeight());
        }
    }

}
