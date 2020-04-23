package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class FarmUpgrades extends Actor {

    private boolean [][] bought = new boolean[4][19];
    private int farmIndex;
    private Texture wheat;
    private Texture strawberry;
    private Texture flowerStrip;
    private Texture tractor;
    private Texture cow;
    private Texture bees;
    private Texture organicFIN;

    public FarmUpgrades(int index) {

        farmIndex = index;
        wheat = new Texture(Gdx.files.internal("farmUpgrades/wheat.png"));
        strawberry = new Texture(Gdx.files.internal("farmUpgrades/strawberry.png"));
        flowerStrip = new Texture(Gdx.files.internal("farmUpgrades/flowerSide.png"));
        tractor = new Texture(Gdx.files.internal("farmUpgrades/tractor.png"));
        cow = new Texture(Gdx.files.internal("farmUpgrades/cow.png"));
        bees = new Texture(Gdx.files.internal("farmUpgrades/bees.png"));
        organicFIN = new Texture(Gdx.files.internal("farmUpgrades/organicFIN.png"));
    }

    public void draw(Batch batch, float alpha) {

        bought = FarmButton.getBoughtArray();

       if(bought[farmIndex][0]) {
            batch.draw(wheat, 250, 200, wheat.getWidth()/2.5f, wheat.getHeight()/2.5f);
        }
        if(bought[farmIndex][3]) {
            batch.draw(strawberry, -20, -20, strawberry.getWidth()/2.5f, strawberry.getHeight()/2.5f);
        }
        if(bought[farmIndex][11]) {
            batch.draw(flowerStrip, 220, 145, flowerStrip.getWidth()/2.6f, flowerStrip.getHeight()/2.6f);
        }
        if(bought[farmIndex][4]) {
            batch.draw(tractor, -10, 10, tractor.getWidth()/2.5f, tractor.getHeight()/2.5f);
        }
        if(bought[farmIndex][6]) {
            batch.draw(cow, 340, 0, cow.getWidth()/5f, cow.getHeight()/5f);
        }
        if(bought[farmIndex][12]) {
            batch.draw(bees, 370, 300, bees.getWidth()/2.5f, bees.getHeight()/2.5f);
        }
        if(bought[farmIndex][16]) {
            batch.draw(organicFIN, 35, 324, organicFIN.getWidth()/2.5f, organicFIN.getHeight()/2.5f);
        }
    }
}
