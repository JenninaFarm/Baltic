package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.utils.viewport.FitViewport;

import java.util.ArrayList;

public class FarmScreen implements Screen {

    private Main main;
    private int farmIndex;
    private SpriteBatch batch;
    private Stage stage;
    private Stage stageUI;
    private Stage stageInfo;
    private ReturnButton returnButton;

    private ArrayList<FarmButton> farmButtons = new ArrayList<FarmButton>();
    private int upgradeAmount = 19;
    private MoneyLabel moneyLabel;
    private Vector2 dragNew, dragOld;
    private Camera camera;

    private static boolean [][] availableArray;
    private static boolean [][] boughtArray;

    public FarmScreen(Main m, int i) {
        main = m;
        farmIndex = i;
        batch = main.getBatch();

        stageUI = new Stage(new FitViewport(800, 450), batch);
        stage = new Stage(new FitViewport(800, 450), batch);
        stageInfo = new Stage(new FitViewport(800, 450), batch);

        camera = stage.getCamera();
        returnButton = new ReturnButton(main, 2);
        moneyLabel = new MoneyLabel(main);

        createButtons();
        addActors();
    }

    private  void createButtons() {
        for(int i=0; i<upgradeAmount; i++) {
            farmButtons.add(new FarmButton(main, i, farmIndex, availableArray[farmIndex][i], boughtArray[farmIndex][i]));
        }
    }

    private void addActors() {
        for(int i=0; i<upgradeAmount; i++) {
            stage.addActor(farmButtons.get(i).getButton());
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


    public void setAvailable(int index) {
        farmButtons.get(index).setAvailable();
    }


    private void handleInput() {

        if (Gdx.input.justTouched()){
            dragNew = new Vector2(Gdx.input.getX(), Gdx.input.getY());
            dragOld = dragNew;
        }

        if (Gdx.input.isTouched()){
            dragNew = new Vector2(Gdx.input.getX(), Gdx.input.getY());
            if (!dragNew.equals(dragOld)){
                ((OrthographicCamera)camera).translate(0, dragNew.y - dragOld.y);
                dragOld = dragNew;
            }
        }

        camera.position.x = MathUtils.clamp(camera.position.x, 300, 500);
        camera.position.y = MathUtils.clamp(camera.position.y, -400, 250);
    }

    public static void setAvailableArray(boolean [][] array) {
        availableArray = array;
    }

    public static void setBoughtArray(boolean [][] arrayb) {
        boughtArray = arrayb;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        handleInput();
        camera.update();
        Gdx.gl.glClearColor(0, 1, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.end();

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
        stageInfo.draw();
        stageUI.act(Gdx.graphics.getDeltaTime());
        stageUI.draw();
    }

    public Stage getStage() {
        return stage;
    }
    public Stage getStageInfo() {
        return stageInfo;
    }
    public Stage getStageUI() {
        return stageUI;
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

    }

    @Override
    public void dispose() {
        stage.dispose();
        stageInfo.dispose();
    }
}
