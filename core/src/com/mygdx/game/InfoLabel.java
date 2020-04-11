package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;

public class InfoLabel extends Actor {

    private Main main;
    private TextArea textArea;

    public InfoLabel(Main m, String info) {
        main = m;

        textArea = new TextArea(info, main.getMySkin());
        textArea.setX(500);
        textArea.setY(50);
        textArea.setWidth(300);
        textArea.setHeight(300);
    }

    public TextArea getInfoLabel() {
        return textArea;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        textArea.draw(batch, parentAlpha);
    }

}
