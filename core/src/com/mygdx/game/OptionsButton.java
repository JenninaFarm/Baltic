package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class OptionsButton extends Actor {
    private Main main;
    private TextureRegion button;
    private float width;
    private float height;
    private int index;

    public OptionsButton(Main m, TextureRegion buttonTexture, int i) {
        main = m;
        button = buttonTexture;
        index = i;
        width = button.getRegionWidth() / 2f;
        height = button.getRegionHeight() / 2f;
        setX(1000 / 2f - width / 2f);
        setY(500 / 2f - height / 2f - index * height);
        setWidth(width);
        setHeight(height);
        setBounds(getX(), getY(), getWidth(), getHeight());

        addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (index == 0) {
                    System.out.println("Options 1 i.e. music off");
                    main.switchScreen(1, 0);
                } else if (index == 1) {
                    System.out.println("Options 2 i.e. soundeffect of");
                    main.switchScreen(1, 0);
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
