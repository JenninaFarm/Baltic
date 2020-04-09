package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class OptionsButton extends Actor {
    private Main main;
    private Button button;
    private float width;
    private float height;
    private int index;

    public OptionsButton(Main m, String label, int i) {
        main = m;
        Skin mySkin = new Skin(Gdx.files.internal("testUiSkin.json"));
        index = i;

        width = 200;
        height = 50;

        button = new TextButton(label, mySkin);
        button.setSize(width, height);
        button.setPosition(800 / 2f - width / 2f, 450 / 2f - height / 2f - index * height);
        button.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                if (index == 0) {
                    System.out.println("to main");
                    main.switchScreen(1, 0);
                } else if (index == 1) {
                    System.out.println("to map");
                    main.switchScreen(2, 0);
                } else if (index == 2) {
                    Save.newGame();
                }
                return true;
            }
        });
    }

    public Button getButton() {
        return button;
    }

    public void draw(Batch batch, float alpha) {
        //batch.draw(button, this.getX(), this.getY(), width, height);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }
}
