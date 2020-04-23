package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;

public class IncomeLabel extends Actor {

    private TextField label;
    private int index;
    private Texture coin;


    IncomeLabel(int i) {
        index = i;
        Skin mySkin = new Skin(Gdx.files.internal("mySkinTest/mySkinTest.json"));


        label = new TextField(Integer.toString(0), mySkin);
        label.setX(470);
        label.setY(410);
        label.setWidth(220);
        label.setHeight(33);
        coin = new Texture(Gdx.files.internal("coin-icon.png"));
    }


    public void draw(Batch batch, float alpha) {
        float [] multipliers = MoneyButton.getMultipliers();
        boolean [] farmsBought = MapButton.getFarmLocks();
        int boughtAmount = 0;
        for(int i=0; i<farmsBought.length; i++) {
            if(farmsBought[i]) {
                boughtAmount++;
            }
        }
        int incomePerMin = 0;
        if(index < 5) {
            incomePerMin = (int)(multipliers[index] * 60);
        } else {
            for(int i=0; i<boughtAmount; i++) {
                incomePerMin += (int)(multipliers[i] * 60);
            }
        }
        label.setText("       /min " + incomePerMin);
        label.draw(batch, alpha);
        batch.draw(coin, label.getX(), label.getY() + 2, 30, 30);
    }
}

