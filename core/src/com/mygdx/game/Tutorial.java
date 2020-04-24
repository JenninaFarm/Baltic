package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class Tutorial extends Actor {

    private int index;
    private Texture tutorialTexture;
    private Texture tutorialTexture_EN;

    public static boolean tutorial = Save.getTutorial();
    public static boolean tutorial_1 = true;
    public static boolean tutorial_2 = true;
    public static boolean tutorial_3 = true;
    public static boolean tutorial_4 = true;
    public static boolean [] tutorial_1_Stages = new boolean [4];
    public static boolean [] tutorial_2_Stages = new boolean [5];
    public static boolean [] tutorial_3_Stages = new boolean [5];
    public static boolean [] tutorial_4_Stages = new boolean [5];

    private float width = 730f;
    private float height = 400f;

    public Tutorial(int tutorialIndex, int stageIndex) {

        switch (tutorialIndex) {

            case 1:
                index = stageIndex;
                tutorialTexture = new Texture(Gdx.files.internal("tutorial1/tutorial-1-" + index + ".png"));
                tutorialTexture_EN = new Texture(Gdx.files.internal("tutorial1-EN/tutorial-1-" + index + "-en.png"));
                setX(30);
                setY(0);
                setWidth(width);
                setHeight(height);
                setBounds(getX(), getY(), getWidth(), getHeight());

                addListener(new InputListener() {
                    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                        if (index < 3) {
                            System.out.println("Next tutorial1 stage");
                            tutorial_1_Stages[++index] = true;
                        } else {
                            tutorial_1 = false;
                        }
                        return true;
                    }
                });
                break;

            case 2:
                index = stageIndex;
                tutorialTexture = new Texture(Gdx.files.internal("tutorial2/tutorial-2-" + index + ".png"));
                tutorialTexture_EN = new Texture(Gdx.files.internal("tutorial2-EN/tutorial-2-" + index + "-en.png"));
                setX(30);
                setY(0);
                setWidth(width);
                setHeight(height);
                setBounds(getX(), getY(), getWidth(), getHeight());

                addListener(new InputListener() {
                    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                        if (index < 2) {
                            System.out.println("Next tutorial1 stage");
                            tutorial_2_Stages[++index] = true;
                        } else {
                            tutorial_2 = false;
                        }
                        return true;
                    }
                });
                break;

            case 3:
                index = stageIndex;
                tutorialTexture = new Texture(Gdx.files.internal("tutorial3/tutorial-3-" + index + ".png"));
                tutorialTexture_EN = new Texture(Gdx.files.internal("tutorial3-EN/tutorial-3-" + index + "-en.png"));
                setX(350);
                setY(0);
                setWidth(width);
                setHeight(height);
                setBounds(getX(), getY(), getWidth(), getHeight());

                addListener(new InputListener() {
                    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                        if (index < 3) {
                            System.out.println("Next tutorial1 stage");
                            tutorial_3_Stages[++index] = true;
                        } else {
                            tutorial_3 = false;
                        }
                        return true;
                    }
                });
                break;

            case 4:
                index = stageIndex;
                tutorialTexture = new Texture(Gdx.files.internal("tutorial4/tutorial-4-" + index + ".png"));
                tutorialTexture_EN = new Texture(Gdx.files.internal("tutorial4-EN/tutorial-4-" + index + "-en.png"));
                setX(30);
                setY(0);
                setWidth(width);
                setHeight(height);
                setBounds(getX(), getY(), getWidth(), getHeight());

                addListener(new InputListener() {
                    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                        if (index < 3) {
                            System.out.println("Next tutorial1 stage");
                            tutorial_4_Stages[++index] = true;
                        } else {
                            tutorial_4 = false;
                        }
                        return true;
                    }
                });
                break;
        }
    }

    public void draw(Batch batch, float alpha) {
        if(Main.finnish){
            batch.draw(tutorialTexture, getX(), getY(), width, height);
        } else {
            batch.draw(tutorialTexture_EN, getX(), getY(), width, height);
        }
    }
}
