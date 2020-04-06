package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class MapResearchButton extends Actor {

    private Main main;
    private Texture button;
    private float width;
    private float height;

    public MapResearchButton(Main m, int x, int y) {
        main = m;
        button = new Texture(Gdx.files.internal("researchmapicon.png"));
        setX(x);
        setY(y);
        width = button.getWidth()/6f;
        height = button.getHeight()/6f;
        setWidth(width);
        setHeight(height);
        setBounds(getX(), getY(), getWidth(), getHeight());

        addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("to research");
                main.switchScreen(4, 0);
                return true;
            }
        });
    }

    public void draw(Batch batch, float alpha) {
        batch.draw(button, this.getX(), this.getY(), width, height);
    }
}
