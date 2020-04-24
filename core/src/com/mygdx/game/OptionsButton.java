package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import java.util.Locale;

public class OptionsButton extends Actor {
    private Main main;
    private Button button1;
    private int index;

    public OptionsButton(Main m, String label, int i) {
        main = m;
        index = i;

        setWidth(200);
        setHeight(50);

        button1 = new TextButton(label, main.getMySkin());
        button1.setSize(getWidth(), getHeight());
        button1.setPosition(800 / 2f - getWidth() / 2f, 500 / 2f - getHeight() / 2f - index * getHeight());
        button1.setDisabled(true);
        button1.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                if (index == 0) {
                    if (main.music_ON) {
                        main.music_ON = false;
                        main.mapMusic.stop();
                        System.out.println("Music off");
                    } else {
                        main.music_ON = true;
                        main.mapMusic.play();
                        System.out.println("Music on");
                    }
                } else if (index == 1) {
                    if (main.soundeffects_ON) {
                        main.soundeffects_ON = false;
                        System.out.println("Sound off");
                    } else {
                        main.soundeffects_ON = true;
                        System.out.println("Sound on");
                    }
                } else if (index == 2) {
                    main.changeLocale(new Locale("fi", "FI"));
                    main.setLanguage(true);
                    main.callCreate(main);
                } else if(index == 3) {
                    main.changeLocale(new Locale("en", "GB"));
                    main.setLanguage(false);
                    main.callCreate(main);
                } else if (index == 4) {
                    Save.newGame();
                    main.callCreate(main);
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
