package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.utils.viewport.FitViewport;

import java.util.ArrayList;

public class MapScreen extends ApplicationAdapter implements Screen {

    private Main main;
    private SpriteBatch batch;
    private MapBackground map;
    private Stage stage;
    private Stage stageUI;
    private int actorAmount = 4;
    private ArrayList<MoneyButton> coins = new ArrayList<>();
    private ArrayList<MapButton> farms = new ArrayList<>();
    private MapResearchButton research;
    private ReturnButton returnButton;
    private Meter meter;
    private MoneyLabel moneyLabel;
    private IncomeLabel incomeLabel;
    private InfoLabel infoLabel;

    private Vector2 dragNew, dragOld;

    private Camera camera;

    private static boolean [] coinAdded = new boolean[4];

    private Tutorial [] tutorial_1_Actors = new Tutorial[6];
    private Tutorial [] tutorial_3_Actors = new Tutorial[6];

    private Boat boat1;
    private MoneyButton boatCoins1;
    private Boat boat2;
    private MoneyButton boatCoins2;

    private Cloud cloud;
    private Cloud cloud2;
    private Cloud cloud3;
    private Cloud cloud4;

    MapScreen(Main m) {

        main = m;
        batch = main.getBatch();

        stageUI = new Stage(new FitViewport(800, 450), batch);
        stage = new Stage(new FitViewport(800, 450), batch);
        camera = stage.getCamera();

        research = new MapResearchButton(main, 680, 270);
        returnButton = new ReturnButton(main, 1);
        moneyLabel = new MoneyLabel(main);
        incomeLabel = new IncomeLabel(main, 5);
        meter = new Meter(main);
        createFarms();
        createBoats();
        createCoins();

        if (Tutorial.tutorial) {
            Tutorial.tutorial_1_Stages[0] = true;
            for (int i = 0; i < 4; i++) {
                tutorial_1_Actors[i] = new Tutorial(1, i);
            }
            stageUI.addActor(tutorial_1_Actors[0]);
        }

        if (Tutorial.tutorial) {
            Tutorial.tutorial_3_Stages[0] = true;
            for (int i = 0; i < 4; i++) {
                tutorial_3_Actors[i] = new Tutorial(3, i);
            }
            stageUI.addActor(tutorial_3_Actors[0]);
            tutorial_3_Actors[0].setVisible(false);
        }

        map = new MapBackground();
        map.setSize(800, 450);
        map.addListener(new ActorGestureListener() {
            public void zoom (InputEvent event, float initialDistance, float distance) {
                ((OrthographicCamera)camera).zoom = ((initialDistance / distance) * ((OrthographicCamera)camera).zoom);
                camera.update();
            }
        });

        cloud = new Cloud(-540, 0, 900);
        cloud2 = new Cloud(-600, -150, 1100);
        cloud3 = new Cloud(-600, -500, 800);
        cloud4 = new Cloud(30, -40, 900);

        addActorsToStage();
        addCoinsToStage();
        addCloudsToStage();

        ((OrthographicCamera)camera).zoom += 27f;
    }

    private void addActorsToStage() {
        stageUI.addActor(moneyLabel);
        stageUI.addActor(incomeLabel);
        stageUI.addActor(returnButton);

        stage.addActor(map);
        stage.addActor(research);
        stage.addActor(meter);

        addBoatsToStage();

        for(int i=0; i<actorAmount; i++) {
            stage.addActor(farms.get(i));
        }
        stage.addActor(coins.get(0));
    }

    private void addCloudsToStage() {
        stage.addActor(cloud);
        stage.addActor(cloud2);
        stage.addActor(cloud3);
        stage.addActor(cloud4);
    }

    static boolean [] getCoinAdded() {
        return coinAdded;
    }

    static void setCoinAdded(boolean [] array) {
        coinAdded = array;
    }

    private void addBoatsToStage() {
        if (Main.getBalticSituation() >= 25) {
            stage.addActor(boat1);
            stage.addActor(boatCoins1);
        }
        if (Main.getBalticSituation() >= 50) {
            stage.addActor(boat2);
            stage.addActor(boatCoins2);
        }
    }

    private void addCoinsToStage() {
        coinAdded[0] = true;
        for(int i=0; i<actorAmount; i++) {
            if(coinAdded[i]) {
                stage.addActor(coins.get(i));
                if(MoneyButton.getLastTimeClicked()[i] == 0) {
                    coins.get(i).setClicked();
                }
            }
        }
    }

    void addCoin(int index) {
        stage.addActor(coins.get(index));
        coinAdded[index] = true;
        coins.get(index).setClicked();

        Save.saveVariables();
        Save.loadVariables();
    }

    void addInfoLabel(InfoLabel il) {
        infoLabel = il;
        stageUI.addActor(infoLabel);
    }

    void setInfoVisible(boolean visible) {
        infoLabel.setVisible(visible);
    }

    private void createCoins() {
        coins.add(new MoneyButton(main,197, 82, 0));
        coins.add(new MoneyButton(main,357, 242, 1));
        coins.add(new MoneyButton(main,667, 133, 2));
        coins.add(new MoneyButton(main,617, 342, 3));
    }

    private void createFarms() {
        farms.add(new MapButton(main, this, 150, 50, 0));
        farms.add(new MapButton(main, this, 310, 210, 1));
        farms.add(new MapButton(main, this, 620, 100, 2));
        farms.add(new MapButton(main, this, 570, 310, 3));
    }

    private void createBoats() {
        boat1 = new Boat(420, 100);
        boatCoins1 = new MoneyButton(main, 460, 130, 4);
        boatCoins1.setClicked();
        boat2 = new Boat(520, 200);
        boatCoins2 = new MoneyButton(main, 560, 230, 5);
        boatCoins2.setClicked();
    }


    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {

        handleInput();
        camera.update();
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        cloud.cloudMove();
        cloud2.cloudMove();
        cloud3.cloudMove();
        cloud4.cloudMove();

        if(Tutorial.tutorial_1 && Tutorial.tutorial) {
            hideIconsTutorial_1();
            manageTutorial_1();
        }
        if(!Tutorial.tutorial_1 && Tutorial.tutorial) {
            for(int j=0; j<4; j++) {
                tutorial_1_Actors[j].setVisible(false);
            }
            research.setVisible(true);
        }

        if(Tutorial.tutorial_3 && !Tutorial.tutorial_2) {
            for (int i = 0; i < 4; i++) {
                farms.get(i).setVisible(true);
            }
            coins.get(0).setVisible(true);
            tutorial_3_Actors[0].setVisible(true);
            manageTutorial_3();
        }

        if(!Tutorial.tutorial_3 && Tutorial.tutorial) {
            for (int j = 0; j < 4; j++) {
                tutorial_3_Actors[j].setVisible(false);
            }
        }

        stage.draw();
        stage.act(Gdx.graphics.getDeltaTime());

        stageUI.draw();
    }

    private void hideIconsTutorial_1() {

        research.setVisible(false);
        for (int i = 0; i < actorAmount; i++) {
            farms.get(i).setVisible(false);
        }
        coins.get(0).setVisible(false);
    }

    private void manageTutorial_1() {

        for(int i=0; i<4; i++) {
            if(Tutorial.tutorial_1_Stages[i]) {
                stageUI.addActor(tutorial_1_Actors[i]);
            }
        }
    }

    private void manageTutorial_3() {

        for(int i=0; i<4; i++) {
            if(Tutorial.tutorial_3_Stages[i] && Tutorial.tutorial_3) {
                stageUI.addActor(tutorial_3_Actors[i]);
            }
        }
    }

    private void handleInput() {

        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            ((OrthographicCamera)camera).zoom += 0.3f;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.Q)) {
            ((OrthographicCamera)camera).zoom -= 0.3f;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            camera.translate(-7, 0, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            camera.translate(7, 0, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            camera.translate(0, -7, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            camera.translate(0, 7, 0);
        }

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

        ((OrthographicCamera)camera).zoom = MathUtils.clamp(((OrthographicCamera)camera).zoom, 10, 800/camera.viewportWidth);
        float effectiveViewportWidth = camera.viewportWidth * ((OrthographicCamera)camera).zoom;
        float effectiveViewportHeight = camera.viewportHeight * ((OrthographicCamera)camera).zoom;

        camera.position.x = MathUtils.clamp(camera.position.x, effectiveViewportWidth / 2f, 800 - effectiveViewportWidth / 2f);
        camera.position.y = MathUtils.clamp(camera.position.y, effectiveViewportHeight / 2f, 450 - effectiveViewportHeight / 2f);
    }

    @Override
    public void resize(int width, int height) {
        camera.viewportWidth = 30f;
        camera.viewportHeight = 30f * height / width;
        camera.update();
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
        for(int i=0; i<actorAmount; i++) {
            coins.get(i).disposeCoinSound();
        }
    }

    Stage getStage() {
        return stage;
    }
    Stage getStageUI() {
        return stageUI;
    }
}
