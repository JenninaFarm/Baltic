package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class ButtonActor extends Actor {

    private Texture button;

    public ButtonActor() {
        button = new Texture(Gdx.files.internal("test_icon.jpg"));
        setX(0);
        setY(0);
        setWidth(button.getWidth()*20f);
        setHeight(button.getHeight()*20f);
        setBounds(0, 0, getWidth(), getHeight());


    }

    public void draw(SpriteBatch batch) {
        batch.draw(button, getX(), getY(), getWidth(), getHeight());
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }
}
