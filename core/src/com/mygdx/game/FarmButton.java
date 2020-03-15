package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class FarmButton extends Actor {
    private Main main;
    private TextureRegion button;
    private float width;
    private float height;
    private int index;

    public FarmButton(Main m, TextureRegion buttonTexture, int i) {
        index = i;
        main = m;
        button = buttonTexture;
        width = button.getRegionWidth()/2f;
        height = button.getRegionHeight()/2f;
        setX(1000/2f - width/2f);
        setY(500/2f - height/2f - index*height);
        setWidth(width);
        setHeight(height);
        setBounds(getX(), getY(), getWidth(), getHeight());

        addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                if(index == 0) {
                    System.out.println("to main");
                    main.switchScreen(1, 0);
                } else if(index == 1) {
                    System.out.println("to map");
                    main.switchScreen(2, 0);
                }

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
