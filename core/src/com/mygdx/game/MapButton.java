package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

import java.time.ZonedDateTime;

public class MapButton extends Actor {

    private Main main;
    private Texture button;
    private float width;
    private float height;
    private int index;

    public MapButton(Main m, int x, int y, int i) {

        index = i;
        main = m;
        button = new Texture(Gdx.files.internal("farm-icon.png"));
        setX(x);
        setY(y);
        width = button.getWidth()/7f;
        height = button.getHeight()/7f;
        setWidth(width);
        setHeight(height);
        setBounds(getX(), getY(), getWidth(), getHeight());

        addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("to farm");
                main.switchScreen(3, index);
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
