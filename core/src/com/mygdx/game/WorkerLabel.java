package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;

/**
 * WorkerLabel is a object base class to create a TextField with information of how many workers specific farm has.
 *
 * @author  Jennina Färm
 * @author  Tommi Häkkinen
 * @version 2020.2204
 * @since 1.8
 */

public class WorkerLabel extends Actor {

    /**
     * TextField that is created and drawn
     */
    private TextField workerLabel;
    /**
     * FarmScreen where the textField is added
     */
    private FarmScreen farmScreen;

    /**
     * Constructor. Sets FarmScreen, x- and y-coordinates, width and height for the text field.
     *
     * @param main Main where the skin is asked
     * @param fs FarmScreen where the WorkerLabel TextField is added.
     */
    WorkerLabel(Main main,FarmScreen fs) {
        farmScreen = fs;

        workerLabel = new TextField(Integer.toString(farmScreen.getWorkerAmount()), main.getMySkin());
        workerLabel.setX(700);
        workerLabel.setY(410);
        workerLabel.setWidth(50);
        workerLabel.setHeight(33);
    }

    /**
     * Calls the TextField draw(batch, alpha)-method.
     * Sets up the current number of workers in the farmScreen and adds one to it since every farm has one worker as default.
     *
     * @param batch batch that is used in draw method
     * @param alpha delta time?
     */

    public void draw(Batch batch, float alpha) {
        workerLabel.setText(Integer.toString( farmScreen.getWorkerAmount()+ 1));
        workerLabel.draw(batch, alpha);
    }
}
