package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

class ResearchBackground extends Actor {

    private Texture texture;

    public ResearchBackground () {

        texture = new Texture(Gdx.files.internal("researchBackground.png"));
    }

    public void draw(Batch batch, float alpha) {
        batch.draw(texture, this.getX(), -570, 2118, 1200);
    }
}
