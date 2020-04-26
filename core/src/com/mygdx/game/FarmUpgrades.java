package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * FarmUpgrades is an object base class to create Textures which extends an Actor.
 *
 * @author  Jennina Färm
 * @author  Tommi Häkkinen
 * @version 2020.2204
 * @since 1.8
 */
public class FarmUpgrades extends Actor {

    /**
     * Index to identify the farmScreen.
     */
    private int farmIndex;

    /**
     * Texture that is created and drawn.
     */
    private Texture wheat;

    /**
     * Texture that is created and drawn.
     */
    private Texture strawberry;

    /**
     * Texture that is created and drawn.
     */
    private Texture flowerStrip;

    /**
     * Texture that is created and drawn.
     */
    private Texture tractor;

    /**
     * Texture that is created and drawn.
     */
    private Texture cow;

    /**
     * Texture that is created and drawn.
     */
    private Texture bees;

    /**
     * Texture that is created and drawn.
     */
    private Texture organicFIN;

    /**
     * Texture that is created and drawn.
     */
    private Texture organicEN;

    /**
     * Constructor. Creates all the Textures.
     *
     * @param index Index of the farmScreen
     */
    FarmUpgrades(int index) {
        farmIndex = index;
        wheat = new Texture(Gdx.files.internal("farmUpgrades/wheat.png"));
        strawberry = new Texture(Gdx.files.internal("farmUpgrades/strawberry.png"));
        flowerStrip = new Texture(Gdx.files.internal("farmUpgrades/flowerSide.png"));
        tractor = new Texture(Gdx.files.internal("farmUpgrades/tractor.png"));
        cow = new Texture(Gdx.files.internal("farmUpgrades/cow.png"));
        bees = new Texture(Gdx.files.internal("farmUpgrades/bees.png"));
        organicFIN = new Texture(Gdx.files.internal("farmUpgrades/organicFIN.png"));
        organicEN = new Texture(Gdx.files.internal("farmUpgrades/organicEN.png"));
    }

    /**
     * Draws textures if specific farmButtons are bought.
     *
     * @param batch handles drawing
     * @param alpha used to handle transparency
     */
    public void draw(Batch batch, float alpha) {

        boolean [][] bought = FarmButton.getBoughtArray();

       if(bought[farmIndex][0]) {
            batch.draw(wheat, 250, 200, wheat.getWidth()/2.5f, wheat.getHeight()/2.5f);
            batch.draw(wheat, 500, 200, wheat.getWidth()/2.5f, wheat.getHeight()/2.5f);
       }
        if(bought[farmIndex][3]) {
            batch.draw(strawberry, -20, -20, strawberry.getWidth()/2.5f, strawberry.getHeight()/2.5f);
            batch.draw(strawberry, 150, -20, strawberry.getWidth()/2.5f, strawberry.getHeight()/2.5f);
            batch.draw(strawberry, 300, -20, strawberry.getWidth()/2.5f, strawberry.getHeight()/2.5f);
        }
        if(bought[farmIndex][11]) {
            batch.draw(flowerStrip, 220, 145, flowerStrip.getWidth()/2.6f, flowerStrip.getHeight()/2.6f);
        }
        if(bought[farmIndex][4]) {
            batch.draw(tractor, -10, 10, tractor.getWidth()/2.5f, tractor.getHeight()/2.5f);
        }
        if(bought[farmIndex][6]) {
            batch.draw(cow, 320, 0, cow.getWidth()/5f, cow.getHeight()/5f);
        }
        if(bought[farmIndex][12]) {
            batch.draw(bees, 370, 300, bees.getWidth()/2.5f, bees.getHeight()/2.5f);
        }
        if(bought[farmIndex][16]) {
            if(Main.finnish) {
                batch.draw(organicFIN, 35, 324, organicFIN.getWidth() / 2.5f, organicFIN.getHeight() / 2.5f);
            } else {
                batch.draw(organicEN, 35, 314, organicFIN.getWidth() / 2.5f, organicFIN.getHeight() / 2.5f);
            }
        }
    }
}
