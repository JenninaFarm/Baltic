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
    private int workerAmount = 1;
    private SpriteBatch batch;
    private Stage stage;
    private Stage stageUI;
    private Stage stageInfo;
    private ReturnButton returnButton;

    private ArrayList<FarmButton> farmButtons = new ArrayList<>();
    private FarmWorker workerButton;
    private WorkerLabel workerLabel;
    private int upgradeAmount = 19;
    private MoneyLabel moneyLabel;
    private IncomeLabel incomeLabel;
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
        workerLabel = new WorkerLabel(main, this);
        setIncomeLabel();

        createButtons();
        addActors();
    }

    private  void createButtons() {
        for(int i=0; i<upgradeAmount; i++) {
            farmButtons.add(new FarmButton(main, i, farmIndex, availableArray[farmIndex][i], boughtArray[farmIndex][i]));
        }
        workerButton = new FarmWorker(main, this);
    }

    private void setIncomeLabel() {
        incomeLabel = new IncomeLabel(main, "farm", farmIndex);
    }

    private void addActors() {
        for(int i=0; i<upgradeAmount; i++) {
            stage.addActor(farmButtons.get(i).getButton());
        }
        stage.addActor(workerButton.getButton());
        stageUI.addActor(incomeLabel);
        stageUI.addActor(moneyLabel);
        stageUI.addActor(workerLabel);
        stageUI.addActor(returnButton);
    }

    public void addToStage(InfoLabel infoLabel) {
        TextArea textArea = infoLabel.getInfoLabel();
        stageInfo.addActor(textArea);
    }

    public void clearStageInfo() {
        stageInfo.clear();
    }


    public void setResearched(int index) {
        farmButtons.get(index).setResearched();
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

    public void addWorker() {
        if(workerAmount < 4) {
            workerAmount++;
            workerLabel.setWorkerLabel(workerAmount);
        }
    }

    public int getWorkerAmount() {
        return workerAmount;
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

        setUpgradesAvailable();

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
        stageInfo.draw();
        stageUI.act(Gdx.graphics.getDeltaTime());
        stageUI.draw();
    }

    public void setUpgradesAvailable() {
        farmButtons.get(0).setReadyToUpgrade();
        farmButtons.get(4).setReadyToUpgrade();

        for(int i=0; i<3; i++) {
            if(boughtArray[farmIndex][i] && workerAmount == i+1) {
                farmButtons.get(i+1).setReadyToUpgrade();
            }
        }

        if(workerAmount == 2) {
            farmButtons.get(6).setReadyToUpgrade();
            if(boughtArray[farmIndex][1]) {
                farmButtons.get(9).setReadyToUpgrade();
            }
            for(int i=0; i<2; i++) {
                if(boughtArray[farmIndex][i+6]) {
                    farmButtons.get(i+7).setReadyToUpgrade();
                }
            }
            if(boughtArray[farmIndex][9]) {
                farmButtons.get(10).setReadyToUpgrade();
            }
            if(boughtArray[farmIndex][10]) {
                farmButtons.get(11).setReadyToUpgrade();
                farmButtons.get(13).setReadyToUpgrade();
            }
            if(boughtArray[farmIndex][13]) {
                farmButtons.get(14).setReadyToUpgrade();
                farmButtons.get(15).setReadyToUpgrade();
            }
        }

        if(workerAmount == 3) {
            if(boughtArray[farmIndex][4] && boughtArray[farmIndex][8]){
                farmButtons.get(5).setReadyToUpgrade();
            }
            if(boughtArray[farmIndex][10]) {
                farmButtons.get(12).setReadyToUpgrade();
            }
        }

        if(workerAmount == 4) {
            if(boughtArray[farmIndex][15]) {
                farmButtons.get(17).setReadyToUpgrade();
                farmButtons.get(18).setReadyToUpgrade();
                if(boughtArray[farmIndex][17]) {
                    farmButtons.get(18).setUnavailable();
                } else if(boughtArray[farmIndex][18]) {
                    farmButtons.get(17).setUnavailable();
                }
            }
        }
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
        stageUI.dispose();
        stageInfo.dispose();
    }
}
