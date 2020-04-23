package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class MainMenuButton extends Actor {

    private Main main;
    private TextureRegion button;
    private float width;
    private float height;
    private int index;

    private Button textButton;

    public MainMenuButton(Main m, TextureRegion buttonTexture, int i) {
        main = m;
        button = buttonTexture;
        index = i;
        width = button.getRegionWidth() / 2f;
        height = button.getRegionHeight() / 2f;
        setX(800 / 2f - width / 2f);
        setY(450 / 2f - height / 2f - index * height);
        setWidth(width);
        setHeight(height);
        setBounds(getX(), getY(), getWidth(), getHeight());

        addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (index == 0) {
                    System.out.println("to map");
                    main.switchScreen(2, 0);
                } else if (index == 1) {
                    System.out.println("to options");
                    main.switchScreen(5, 0);
                }

                return true;
            }
        });
    }

    public MainMenuButton(Main m, String label, int i) {
        main = m;
        index = i;

        width = 150;
        height = 50;

        textButton = new TextButton(label, main.getMySkin());
        textButton.setSize(width, height);
        textButton.setPosition(800 / 2f - width / 2f, 450 / 2f - height / 2f - index * height);
        textButton.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                if (index == 0) {
                    System.out.println("to map");
                    main.switchScreen(2, 0);
                } else if (index == 1) {
                    System.out.println("to options");
                    main.switchScreen(5, 0);
                }
                return true;
            }
        });
    }

    public Button getButton() {
        return textButton;
    }

    public void draw(Batch batch, float alpha) {
        batch.draw(button, this.getX(), this.getY(), width, height);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }
}
