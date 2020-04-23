package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;

public class InfoLabel extends Actor {

    private TextArea textArea;

    InfoLabel(Main main, String info) {

        textArea = new TextArea(info, main.getMySkin());
        textArea.setX(20);
        textArea.setY(50);
        textArea.setWidth(300);
        textArea.setHeight(310);
    }

    TextArea getInfoLabel() {
        return textArea;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        textArea.draw(batch, parentAlpha);
    }

}
