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
                tutorialTexture = new Texture(Gdx.files.internal("tutorial/tutorial-1-" + index + ".png"));
                setX(30);
                setY(0);
                setWidth(width);
                setHeight(height);
                setBounds(getX(), getY(), getWidth(), getHeight());

                addListener(new InputListener() {
                    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                        if (index < 4) {
                            System.out.println("Next tutorial stage");
                            tutorial_1_Stages[index++] = true;
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
                setX(30);
                setY(0);
                setWidth(width);
                setHeight(height);
                setBounds(getX(), getY(), getWidth(), getHeight());

                addListener(new InputListener() {
                    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                        if (index < 3) {
                            System.out.println("Next tutorial stage");
                            tutorial_2_Stages[index++] = true;
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
                setX(350);
                setY(0);
                setWidth(width);
                setHeight(height);
                setBounds(getX(), getY(), getWidth(), getHeight());

                addListener(new InputListener() {
                    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                        if (index < 4) {
                            System.out.println("Next tutorial stage");
                            tutorial_3_Stages[index++] = true;
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
                setX(30);
                setY(0);
                setWidth(width);
                setHeight(height);
                setBounds(getX(), getY(), getWidth(), getHeight());

                addListener(new InputListener() {
                    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                        if (index < 4) {
                            System.out.println("Next tutorial stage");
                            tutorial_4_Stages[index++] = true;
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
        batch.draw(tutorialTexture, getX(), getY(), width, height);
    }
}
