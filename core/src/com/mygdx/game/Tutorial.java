package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;

public class Tutorial extends Actor {

    private int index;
    private Texture tutorialTexture;
    public static boolean tutorial = true;
    public static boolean [] tutorialStages = new boolean [5];

    public Tutorial(int i) {

        tutorialTexture = new Texture(Gdx.files.internal("tutorialexample.png"));
        index = i;

        addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("Next tutorial stage");
                Tutorial.tutorialStages[index] = false;
                Tutorial. tutorialStages[index++] = true;
                return true;
            }
        });
    }

    public void draw(Batch batch, float alpha) {
        batch.draw(tutorialTexture, 0, 0, 300, 300);
    }
}
