package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.I18NBundle;
import com.badlogic.gdx.utils.viewport.FitViewport;

import java.util.ArrayList;

public class ResearchScreen implements Screen {

    private Main main;
    private SpriteBatch batch;
    private Stage stage;
    private Stage stageUI;
    private Stage stageInfo;
    private static ResearchButton [] researchButtons = new ResearchButton [19];
    private static boolean [] booleans;
    private static int researchAmount = 19;
    private ReturnButton returnButton;
    private MoneyLabel moneyLabel;
    private Camera camera;
    private Vector2 dragNew, dragOld;

    private Tutorial [] tutorial_2_Actors = new Tutorial[6];


    public ResearchScreen(Main m) {
        main = m;
        batch = main.getBatch();

        stage = new Stage(new FitViewport(800, 450), batch);
        stageUI = new Stage(new FitViewport(800, 450), batch);
        stageInfo = new Stage(new FitViewport(800, 450), batch);

        camera = stage.getCamera();
        returnButton = new ReturnButton(main, 2);
        moneyLabel = new MoneyLabel(main);

        createButtons();

        if(Tutorial.tutorial_2 && Tutorial.tutorial) {
            Tutorial.tutorial_2_Stages[0] = true;
            for(int i=0;i<5;i++) {
                tutorial_2_Actors[i] = new Tutorial(2, i);
            }
            stageUI.addActor(tutorial_2_Actors[0]);
        }

        addActors();
    }

    public static void setBooleanArray(boolean [] array) {
        booleans = array;
    }
    private void createButtons() {
        for(int i=0; i<researchAmount; i++){
            researchButtons[i] = (new ResearchButton(main, i, booleans[i]));
        }
    }

    private void addActors() {
        for(int i=0; i<researchAmount; i++) {
            Button button = researchButtons[i].getButton();
            stage.addActor(button);
        }
        stageUI.addActor(moneyLabel);
        stageUI.addActor(returnButton);
    }

    public void addToStage(InfoLabel infoLabel) {
        TextArea textArea = infoLabel.getInfoLabel();
        stageInfo.addActor(textArea);
    }

    public void clearStageInfo() {
        stageInfo.clear();
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        handleInput();
        camera.update();
        Gdx.gl.glClearColor(0, 0, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if(!Tutorial.tutorial) {
            setResearchesAvailable();
        }

        if(Tutorial.tutorial_2 && Tutorial.tutorial) {
            researchButtons[0].setResearchAvailable();
            returnButton.setVisible(false);
        }

        manageTutorial_2();

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();

        stageUI.act(Gdx.graphics.getDeltaTime());
        stageUI.draw();

        stageInfo.draw();
    }

    private void manageTutorial_2() {

        for(int i=0; i<5; i++) {
            if(Tutorial.tutorial_2_Stages[i] && Tutorial.tutorial_2) {
                stageUI.addActor(tutorial_2_Actors[i]);
            }
        }
        if(!Tutorial.tutorial_2) {
            returnButton.setVisible(true);
            for(int j=0; j<5; j++) {
                tutorial_2_Actors[j].setVisible(false);
            }
        }
    }

    private void handleInput() {

        if (Gdx.input.justTouched()){
            dragNew = new Vector2(Gdx.input.getX(), Gdx.input.getY());
            dragOld = dragNew;
        }

        if (Gdx.input.isTouched()){
            dragNew = new Vector2(Gdx.input.getX(), Gdx.input.getY());
            if (!dragNew.equals(dragOld)){
                ((OrthographicCamera)camera).translate(dragOld.x - dragNew.x, dragNew.y - dragOld.y);
                dragOld = dragNew;
            }
        }

        camera.position.x = MathUtils.clamp(camera.position.x, 400, 800);
        camera.position.y = MathUtils.clamp(camera.position.y, 50, 225);
    }

    public static void setResearchesAvailable() {
        researchButtons[0].setResearchAvailable();
        researchButtons[4].setResearchAvailable();
        researchButtons[6].setResearchAvailable();
        researchButtons[9].setResearchAvailable();

        for(int i=0; i<3; i++) {
            if(booleans[i]) {
                researchButtons[i+1].setResearchAvailable();
            }
        }
        if(booleans[4] && booleans[8]) {
            researchButtons[5].setResearchAvailable();
        }

        for(int i=0; i<2; i++) {
            if(booleans[i+6]) {
                researchButtons[i+7].setResearchAvailable();
            }
        }
        if(booleans[9]) {
            researchButtons[10].setResearchAvailable();
        }
        if(booleans[10]) {
            researchButtons[11].setResearchAvailable();
            researchButtons[12].setResearchAvailable();
            researchButtons[13].setResearchAvailable();
        }
        if(booleans[13]) {
            researchButtons[14].setResearchAvailable();
            researchButtons[15].setResearchAvailable();
        }
        if(booleans[14]) {
            researchButtons[16].setResearchAvailable();
        }
        if(booleans[15]) {
            researchButtons[17].setResearchAvailable();
            researchButtons[18].setResearchAvailable();
        }
    }

    public Stage getStage() {
        return stage;
    }
    public Stage getStageUI() {
        return stageUI;
    }
    public Stage getStageInfo() {
        return stageInfo;
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        Save.saveVariables();
    }

    public static void newGameReset(Main main) {
        for (int i=0; i<researchAmount; i++) {
            researchButtons[i] = new ResearchButton(main, i, false);
        }
        setResearchesAvailable();
    }

    @Override
    public void dispose() {
        stage.dispose();
        stageUI.dispose();
        stageInfo.dispose();
    }
}
