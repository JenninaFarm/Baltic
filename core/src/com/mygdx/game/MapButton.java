package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class MapButton extends Actor {

    private Main main;
    private Texture button;
    private float width;
    private float height;

    public MapButton(Main m) {
        main = m;
        button = new Texture(Gdx.files.internal("farm-icon.png"));
        setX(0);
        setY(0);
        width = button.getWidth()/4f;
        height = button.getHeight()/4f;
        setWidth(width);
        setHeight(height);
        setBounds(0, 0, getWidth(), getHeight());

        addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("there");
                main.switchScreen(3, 1);
                return true;
            }
        });
    }

    public void draw(Batch batch, float alpha) {
        batch.draw(button, this.getX(), this.getY(), width, height);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }
}
