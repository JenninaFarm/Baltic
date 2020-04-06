package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;

import java.util.ArrayList;

public class MapScreen extends ApplicationAdapter implements Screen {

    private Main main;
    private SpriteBatch batch;
    private Actor map;
    private Stage stage;
    private Stage stageUI;
    private int actorAmount = 4;
    private ArrayList<MoneyButton> coins = new ArrayList<>();
    private ArrayList<MapButton> farms = new ArrayList<>();
    public static float [] savedMultipliers;
    private MapResearchButton research;
    private ReturnButton returnButton;
    private Meter meter;
    private Label moneyLabel;

    private Vector2 dragNew, dragOld;

    private Camera camera;

    public MapScreen(Main m) {
        main = m;
        batch = main.getBatch();

        stageUI = new Stage(new FitViewport(800, 450), batch);
        stage = new Stage(new FitViewport(800, 450), batch);
        camera = stage.getCamera();

        research = new MapResearchButton(main, 680, 270);
        returnButton = new ReturnButton(main, 1);
        meter = new Meter(main);
        createFarms();
        createCoins();

        map = new MapBackground();
        map.setSize(800, 450);
        map.setPosition(0, 0);
        map.addListener(new ActorGestureListener() {
            public void zoom (InputEvent event, float initialDistance, float distance) {
                //System.out.println("zoom " + initialDistance + ", " + distance);
                ((OrthographicCamera)camera).zoom = ((initialDistance / distance) * ((OrthographicCamera)camera).zoom);
                camera.update();
            }
        });

        stageUI.addActor(returnButton);

        stage.addActor(map);
        stage.addActor(research);
        stage.addActor(meter);
        addFarmsToStage();
        addCoinsToStage();

        createMoneyLabel();

        ((OrthographicCamera)camera).zoom += 25.6f;
    }

    private void addFarmsToStage() {
        for(int i=0; i<actorAmount; i++) {
            stage.addActor(farms.get(i));
        }
    }

    public static void setSavedMultipliers(float [] array) {
        savedMultipliers = array;
    }

    private void createCoins() {
        coins.add(new MoneyButton(main, 198, 82, 0, savedMultipliers[0]));
        coins.add(new MoneyButton(main, 395, 225, 1, savedMultipliers[1]));
        coins.add(new MoneyButton(main, 665, 130, 2, savedMultipliers[2]));
        coins.add(new MoneyButton(main, 615, 340, 3, savedMultipliers[3]));
    }

    private void addCoinsToStage() {
        for(int i=0; i<actorAmount; i++) {
            stage.addActor(coins.get(i));
        }
    }

    private void createFarms() {
        farms.add(new MapButton(main, 150, 50, 0));
        farms.add(new MapButton(main, 350, 190, 1));
        farms.add(new MapButton(main, 620, 100, 2));
        farms.add(new MapButton(main,570, 310, 3));
    }

    private void createMoneyLabel() {
        Label.LabelStyle label1Style = new Label.LabelStyle();
        BitmapFont myFont = new BitmapFont(Gdx.files.internal("sansFont.fnt"));
        label1Style.font = myFont;
        label1Style.fontColor = Color.RED;

        moneyLabel = new Label(Integer.toString(main.getMoney()), label1Style);
        moneyLabel.setSize(800 ,30);
        moneyLabel.setPosition(50,400);
        moneyLabel.setAlignment(Align.center);
        stageUI.addActor(moneyLabel);
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

        stage.act(Gdx.graphics.getDeltaTime());
        moneyLabel.setText(main.getMoney());
        stage.draw();

        stageUI.act(Gdx.graphics.getDeltaTime());
        stageUI.draw();
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
    }

    public Stage getStage() {
        return stage;
    }
    public Stage getStageUI() {
        return stageUI;
    }
}
