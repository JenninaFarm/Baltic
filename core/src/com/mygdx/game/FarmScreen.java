package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.utils.Array;
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
    private static float [][] actorY = new float[4][19];

    private ArrayList<FarmButton> farmButtons = new ArrayList<>();
    private FarmWorker workerButton;
    private WorkerLabel workerLabel;
    private int upgradeAmount = 19;
    private MoneyLabel moneyLabel;
    private IncomeLabel incomeLabel;
    private Vector2 dragNew, dragOld;
    private Camera camera;

    private static int [] workerAmount = new int[4];
    private static Array<Actor> actors;
    private Background farmBackground;

    private Tutorial [] tutorial_4_Actors = new Tutorial[6];

    public FarmScreen(Main m, int i) {
        main = m;
        farmIndex = i;
        batch = main.getBatch();
        farmBackground = new Background(new Texture(Gdx.files.internal("farm-background.png")));

        stage = new Stage(new FitViewport(800, 450), batch);
        stageUI = new Stage(new FitViewport(800, 450), batch);
        stageInfo = new Stage(new FitViewport(800, 450), batch);

        camera = stage.getCamera();
        returnButton = new ReturnButton(main, 2);
        moneyLabel = new MoneyLabel(main);
        workerLabel = new WorkerLabel(main, this);
        setIncomeLabel();

        if(Tutorial.tutorial_4 && Tutorial.tutorial) {
            Tutorial.tutorial_4_Stages[0] = true;
            for(int j=0;j<5;j++) {
                tutorial_4_Actors[j] = new Tutorial(4, j);
            }
            stageUI.addActor(tutorial_4_Actors[0]);
        }

        createButtons();
        addActors();
        actors = stage.getActors();
        for(int j=0; j<upgradeAmount; j++) {
            actors.get(j).setY(actorY[farmIndex][j]);
        }
    }

    public static float[][] getFarmActorYArray() {
        return actorY;
    }

    private void setIncomeLabel() {
        incomeLabel = new IncomeLabel(main, "farm", farmIndex);
    }

    public static void setFarmActorYArray(float [][] array) {
        actorY = array;
    }

    public void addToStage(InfoLabel infoLabel) {
        TextArea textArea = infoLabel.getInfoLabel();
        stageInfo.addActor(textArea);
    }

    public void clearStageInfo() {
        stageInfo.clear();
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

    public static int[] getWorkerAmountArray() {
        return workerAmount;
    }


    public static void setWorkerAmountArray(int [] array) { workerAmount = array;}

    public void addWorker() {
        if(workerAmount[farmIndex] < 4) {
            workerAmount[farmIndex]++;
            Save.saveVariables();
            Save.loadVariables();
        }
    }

    public int getWorkerAmount() {
        return workerAmount[farmIndex];
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

        manageTutorial_4();

        setUpgradesAvailable();

        stage.act(Gdx.graphics.getDeltaTime());
        stageInfo.draw();
        stageUI.act(Gdx.graphics.getDeltaTime());
        stageUI.draw();
        stage.draw();
    }

    private void manageTutorial_4() {

        for(int i=0; i<5; i++) {
            if(Tutorial.tutorial_4_Stages[i] && Tutorial.tutorial_4) {
                stageUI.addActor(tutorial_4_Actors[i]);
            }
        }
        if(!Tutorial.tutorial_4) {
            for(int j=0; j<5; j++) {
                tutorial_4_Actors[j].setVisible(false);
            }
        }
    }

    public void setUpgradesAvailable() {
        boolean [][] boughtArray = FarmButton.getBoughtArray();

        farmButtons.get(0).setAvailable();
        farmButtons.get(4).setAvailable();

        for(int i=0; i<3; i++) {
            if(boughtArray[farmIndex][i] && workerAmount[farmIndex] >= i) {
                farmButtons.get(i+1).setAvailable();
            }
        }

        if(workerAmount[farmIndex] >= 1) {
            farmButtons.get(6).setAvailable();
            if(boughtArray[farmIndex][1]) {
                farmButtons.get(9).setAvailable();
            }
            for(int i=0; i<2; i++) {
                if(boughtArray[farmIndex][i+6]) {
                    farmButtons.get(i+7).setAvailable();
                }
            }
            if(boughtArray[farmIndex][9]) {
                farmButtons.get(10).setAvailable();
            }
            if(boughtArray[farmIndex][10]) {
                farmButtons.get(11).setAvailable();
                farmButtons.get(13).setAvailable();
            }
            if(boughtArray[farmIndex][13]) {
                farmButtons.get(14).setAvailable();
                farmButtons.get(15).setAvailable();
            }
        }

        if(workerAmount[farmIndex] >= 2) {
            if(boughtArray[farmIndex][4] && boughtArray[farmIndex][8]){
                farmButtons.get(5).setAvailable();
            }
            if(boughtArray[farmIndex][10]) {
                farmButtons.get(12).setAvailable();
            }
        }

        if(workerAmount[farmIndex] >= 3) {
            if(boughtArray[farmIndex][14]) {
                farmButtons.get(16).setAvailable();
            }
            if(boughtArray[farmIndex][15]) {
                farmButtons.get(17).setAvailable();
                farmButtons.get(18).setAvailable();
                if(boughtArray[farmIndex][17]) {
                    farmButtons.get(18).setUnavailable();
                } else if(boughtArray[farmIndex][18]) {
                    farmButtons.get(17).setUnavailable();
                }
            }
        }
    }

    private  void createButtons() {
        for(int i=0; i<upgradeAmount; i++) {
            farmButtons.add(new FarmButton(main, this, i, farmIndex));
        }
        workerButton = new FarmWorker(main, this);
    }

    private void addActors() {
        for(int i=0; i<upgradeAmount; i++) {
            stage.addActor(farmButtons.get(i).getButton());
        }
        stage.addActor(workerButton.getButton());
        stageUI.addActor(farmBackground);
        stageUI.addActor(incomeLabel);
        stageUI.addActor(moneyLabel);
        stageUI.addActor(workerLabel);
        stageUI.addActor(farmButtons.get(0));
        stageUI.addActor(returnButton);
    }

    public void setFarmButtonY(int buttonIndex) {
        actors = stage.getActors();
        for(int i=0; i<upgradeAmount; i++) {
            if(buttonIndex == i) {
                for(int j=i; j<upgradeAmount; j++) {
                    moveY(actors.get(j), actors.get(j).getY(), j);
                }
            }
        }
    }

    private void moveY(Actor actor, float y, int index) {
        actor.setY(y+50);
        actorY[farmIndex][index] = actor.getY();
        Save.saveVariables();
        Save.loadVariables();
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
