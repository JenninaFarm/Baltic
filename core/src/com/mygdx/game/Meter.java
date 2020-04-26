package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.ScaleByAction;
import com.badlogic.gdx.scenes.scene2d.actions.ScaleToAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.actions.SizeToAction;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class Meter extends Actor {

    private Main main;
    private TextureRegion [] meterTexture;
    private Label balticMeter;
    private float width;
    private float height;
    private int balticSituation;

    public Meter(Main m) {
        main = m;
        meterTexture = new TextureRegion[9];
        for(int i=0; i<meterTexture.length; i++) {
            meterTexture[i] = new TextureRegion(new Texture (Gdx.files.internal("meter/meter" + i + ".png")));
        }


        balticSituation = main.getBalticSituation();

        balticMeter = new Label(Integer.toString(balticSituation), main.getMySkin());
        balticMeter.setX(753);
        balticMeter.setY(39);


        setX(730);
        setY(30);
        width = meterTexture[0].getRegionWidth()/4f;
        height = meterTexture[0].getRegionHeight()/4f;
        setWidth(width);
        setHeight(height);
        setBounds(getX(), getY(), getWidth(), getHeight());
    }

    public void draw(Batch batch, float alpha) {
        balticSituation = main.getBalticSituation();
        int [] balticState = {0, 5, 10, 18, 25, 35, 50, 67, 90, 120};
        for(int i=0; i<meterTexture.length; i++) {
            if(balticSituation >= balticState[i] && balticSituation < balticState[i+1]) {
                batch.draw(meterTexture[i], getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
            }
        }

        //move label location if the number is 10 or over
        if(balticSituation >= 10 && balticSituation < 20 || balticSituation >= 60 && balticSituation < 100) {
            balticMeter.setX(747);
        } else if(balticSituation >= 30 && balticSituation < 40 || balticSituation >= 50 && balticSituation < 60) {
            balticMeter.setX(746);
        } else if (balticSituation >= 40 && balticSituation < 49) {
            balticMeter.setX(745);
        } else if(balticSituation >= 100) {
            balticMeter.setX(741);
        }
        balticMeter.setText(balticSituation);
        balticMeter.draw(batch, alpha);
    }
}