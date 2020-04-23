package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;

/**
 * InfoLabel is a object base class to create a TextArea with information of given info.
 *
 * @author  Jennina Färm
 * @author  Tommi Häkkinen
 * @version 2020.2204
 * @since 1.8
 */

public class InfoLabel extends Actor {

    /**
     * TextArea that is created and drawn
     */
    private TextArea textArea;

    /**
     * Constructor. Sets
     *
     * @param main
     * @param info
     */

    InfoLabel(Main main, String info, int x, int y, int width, int height) {

        textArea = new TextArea(info, main.getMySkin());
        textArea.setX(x);
        textArea.setY(y);
        textArea.setWidth(width);
        textArea.setHeight(height);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        textArea.draw(batch, parentAlpha);
    }

}
