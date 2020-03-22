package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class ReturnButton extends Actor {

    private Main main;
    private Texture button;
    private float width;
    private float height;
    private int index;

    public ReturnButton(Main m, int i) {

        index = i;
        main = m;
        button = new Texture(Gdx.files.internal("test_icon.jpg"));
        setX(0);
        setY(434);
        width = button.getWidth()/4f;
        height = button.getHeight()/4f;
        setWidth(width);
        setHeight(height);
        setBounds(getX(), getY(), getWidth(), getHeight());

        addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("return");
                main.switchScreen(index, 0);
                return true;
            }
        });
    }

    public void draw(Batch batch, float alpha) {
        batch.draw(button, this.getX(), this.getY(), width, height);
    }
}
