package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import java.util.Locale;

public class OptionsButton extends Actor {
    private Main main;
    private Button button1;
    private float width;
    private float height;
    private int index;

    public OptionsButton(Main m, String label, int i) {
        main = m;
        index = i;

        width = 200;
        height = 50;

        button1 = new TextButton(label, main.getMySkin());
        button1.setSize(width, height);
        button1.setPosition(800 / 2f - width / 2f, 500 / 2f - height / 2f - index * height);
        button1.setDisabled(true);
        button1.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                if (index == 0) {
                    if (Main.music_ON) {
                        Main.setMusic(false);
                        Main.mapMusic.stop();
                    } else {
                        Main.setMusic(true);
                        Main.mapMusic.play();
                    }
                } else if (index == 1) {
                    if (Main.soundeffects_ON) {
                        Main.soundeffects_ON = false;
                    } else {
                        Main.soundeffects_ON = true;
                    }
                } else if (index == 2) {
                    main.changeLocale(new Locale("fi", "FI"));
                    Main.setLanguage(true);
                    Main.callCreate(main);
                } else if(index == 3) {
                    main.changeLocale(new Locale("en", "GB"));
                    Main.setLanguage(false);
                    Main.callCreate(main);
                } else if (index == 4) {
                    Save.newGame();
                    //Save.saveVariables();
                    //Save.loadVariables();
                    Main.callCreate(main);
                }
                return true;
            }
        });
    }

    public Button getButton() {
        return button1;
    }

    public void draw(Batch batch, float alpha) {
        //batch.draw(button, this.getX(), this.getY(), width, height);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }
}
