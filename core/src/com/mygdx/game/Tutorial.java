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

    public static boolean tutorial = Save.getTutorial();
    public static boolean tutorial_1 = true;
    public static boolean tutorial_2 = true;
    public static boolean tutorial_3 = true;
    public static boolean tutorial_4 = true;
    public static boolean [] tutorial_1_Stages = new boolean [5];
    public static boolean [] tutorial_2_Stages = new boolean [5];
    public static boolean [] tutorial_3_Stages = new boolean [5];
    public static boolean [] tutorial_4_Stages = new boolean [5];

    private float width = 300f;
    private float height = 300f;

    public Tutorial(int tutorialIndex, int stageIndex) {

        switch (tutorialIndex) {

            case 1:
                index = stageIndex;
                tutorialTexture = new Texture(Gdx.files.internal("tutorial/tutorialexample" + index + ".png"));
                setX(0);
                setY(0);
                setWidth(width);
                setHeight(height);
                setBounds(getX(), getY(), getWidth(), getHeight());

                addListener(new InputListener() {
                    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                        if (index < 5) {
                            System.out.println("Next tutorial stage");
                            tutorial_1_Stages[index] = false;
                            tutorial_1_Stages[index++] = true;
                        } else {
                            tutorial_1 = false;
                            for (int i = 0; i < tutorial_1_Stages.length; i++) {
                                tutorial_1_Stages[i] = false;
                            }
                        }
                        return true;
                    }
                });
                break;

            case 2:
                index = stageIndex;
                tutorialTexture = new Texture(Gdx.files.internal("tutorial2/tutorialexample" + index + ".png"));
                setX(500);
                setY(0);
                setWidth(width);
                setHeight(height);
                setBounds(getX(), getY(), getWidth(), getHeight());

                addListener(new InputListener() {
                    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                        if (index < 5) {
                            System.out.println("Next tutorial stage");
                            tutorial_2_Stages[index] = false;
                            tutorial_2_Stages[index++] = true;
                        } else {
                            tutorial_2 = false;
                            for (int i = 0; i < tutorial_2_Stages.length; i++) {
                                tutorial_2_Stages[i] = false;
                            }
                        }
                        return true;
                    }
                });
                break;

            case 3:
                index = stageIndex;
                tutorialTexture = new Texture(Gdx.files.internal("tutorial3/tutorialexample" + index + ".png"));
                setX(350);
                setY(0);
                setWidth(width);
                setHeight(height);
                setBounds(getX(), getY(), getWidth(), getHeight());

                addListener(new InputListener() {
                    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                        if (index < 5) {
                            System.out.println("Next tutorial stage");
                            tutorial_3_Stages[index] = false;
                            tutorial_3_Stages[index++] = true;
                        } else {
                            tutorial_3 = false;
                            for (int i = 0; i < tutorial_3_Stages.length; i++) {
                                tutorial_3_Stages[i] = false;
                            }
                        }
                        return true;
                    }
                });
                break;

            case 4:
                index = stageIndex;
                tutorialTexture = new Texture(Gdx.files.internal("tutorial4/tutorialexample" + index + ".png"));
                setX(0);
                setY(0);
                setWidth(width);
                setHeight(height);
                setBounds(getX(), getY(), getWidth(), getHeight());

                addListener(new InputListener() {
                    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                        if (index < 5) {
                            System.out.println("Next tutorial stage");
                            tutorial_4_Stages[index] = false;
                            tutorial_4_Stages[index++] = true;
                        } else {
                            tutorial_4 = false;
                            tutorial = false;
                            Save.saveVariables();
                            for (int i = 0; i < tutorial_4_Stages.length; i++) {
                                tutorial_4_Stages[i] = false;
                            }
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
