package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class MapButton extends Actor {

    private Main main;
    Texture button;

    public MapButton(Main m) {
        main = m;
        button = new Texture(Gdx.files.internal("test_icon.jpg"));
        setX(0);
        setY(0);
        setWidth(button.getWidth());
        setHeight(button.getHeight());
        setBounds(0, 0, getWidth(), getHeight());

        addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("there");
                main.switchScreen(1, 0);
                return true;
            }
        });
    }

    public void draw(Batch batch, float alpha) {
        batch.draw(button, this.getX(), this.getY(), button.getWidth(), button.getHeight());
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }
}
