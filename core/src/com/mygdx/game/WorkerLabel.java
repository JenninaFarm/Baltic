package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;

public class WorkerLabel extends Actor {

    private TextField workerLabel;
    private FarmScreen farmScreen;

    public WorkerLabel(Main main, FarmScreen fs) {
        farmScreen = fs;
        Skin mySkin = new Skin(Gdx.files.internal("mySkinTest/mySkinTest.json"));

        workerLabel = new TextField(Integer.toString(farmScreen.getWorkerAmount()), mySkin);
        workerLabel.setX(700);
        workerLabel.setY(410);
        workerLabel.setWidth(50);
        workerLabel.setHeight(33);
    }

    public void draw(Batch batch, float alpha) {
        workerLabel.setText(Integer.toString( farmScreen.getWorkerAmount()+ 1));
        workerLabel.draw(batch, alpha);
    }
}
