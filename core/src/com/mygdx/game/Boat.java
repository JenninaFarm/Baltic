package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Boat is an object base class to create a Texture which extends an Actor.
 *
 * @author  Jennina Färm
 * @author  Tommi Häkkinen
 * @version 2020.2204
 * @since 1.8
 */

public class Boat extends Actor {

    /**
     * Texture which is created and drawn
     */
    private Texture texture;

    /**
     * Constructor. Sets x- and y-coordinates, width and height of the Boat.
     *
     * @param x x-coordinate of the Boat
     * @param y y-coordinate of the Boat
     */

    Boat(int x, int y) {

        texture = new Texture(Gdx.files.internal("boat-icon.png"));
        setX(x);
        setY(y);
        setWidth(texture.getWidth() / 8f);
        setHeight(texture.getHeight() / 8f);
    }

    /**
     * Draws the Texture with specific location and size.
     *
     * @param batch batch that is used in draw method
     * @param alpha delta time?
     */

    public void draw(Batch batch, float alpha) {
        batch.draw(texture, getX(), getY(), getWidth(), getHeight());
    }
}
