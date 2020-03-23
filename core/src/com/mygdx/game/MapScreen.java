package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;

import java.util.ArrayList;

public class MapScreen extends ApplicationAdapter implements Screen, GestureListener {

    private Main main;
    private SpriteBatch batch;
    private Actor map;
    private Stage stage;
    private int actorAmount = 4;

    public static InputMultiplexer inputMultiplex;

    private ArrayList<MoneyButton> coins = new ArrayList<>();
    private ArrayList<MapButton> farms = new ArrayList<>();
    private MapResearchButton research;
    private ReturnButton returnButton;
    private Label moneyLabel;

    private Vector2 dragNew, dragOld;

    private Camera camera;

    public MapScreen(Main m) {
        main = m;
        batch = main.getBatch();

        stage = new Stage(new FitViewport(800, 450), batch);
        camera = stage.getCamera();

        research = new MapResearchButton(main, 670, 215);
        returnButton = new ReturnButton(main, 1);
        createFarms();
        createCoins();

        map = new MapBackground();
        map.setSize(800, 450);
        map.setPosition(0, 0);

        stage.addActor(map);
        createMoneyLabel();

        addCoinsAndFarmsToStage();
        stage.addActor(research);
        stage.addActor(returnButton);

        GestureDetector gd = new GestureDetector(this);
        inputMultiplex = new InputMultiplexer();
        inputMultiplex.addProcessor(stage);
        inputMultiplex.addProcessor(gd);
        Gdx.input.setInputProcessor(gd);

        ((OrthographicCamera)camera).zoom += 25.6f;
    }

    private void addCoinsAndFarmsToStage() {
        for(int i=0; i<actorAmount; i++) {
            stage.addActor(farms.get(i));
            stage.addActor(coins.get(i));
        }
    }

    private void createCoins() {
        coins.add(new MoneyButton(main, 245, 65));
        coins.add(new MoneyButton(main, 405, 195));
        coins.add(new MoneyButton(main, 645, 115));
        coins.add(new MoneyButton(main, 604, 315));
    }

    private void createFarms() {
        farms.add(new MapButton(main, 200, 20, 1));
        farms.add(new MapButton(main, 360, 150, 2));
        farms.add(new MapButton(main, 600, 70, 3));
        farms.add(new MapButton(main,560, 270, 4));
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
        stage.addActor(moneyLabel);
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

        moneyLabel.setText(main.getMoney());

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
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


        ((OrthographicCamera)camera).zoom = MathUtils.clamp(((OrthographicCamera)camera).zoom, 1, 5000/camera.viewportWidth);
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
    }

    public Stage getStage() {
        return stage;
    }

    @Override
    public boolean touchDown(float x, float y, int a, int b) {
        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        return false;
    }

    @Override
    public boolean longPress(float x, float y) {
        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        return false;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        Gdx.app.log("INFO", "In pan");

        ((OrthographicCamera)camera).translate(-deltaX, deltaY);
        camera.update();
        return false;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        Gdx.app.log("INFO", "panStop");
        ((OrthographicCamera)camera).zoom = ((OrthographicCamera)camera).zoom;
        return false;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {
        String message = "Zoom performed";
        Gdx.app.log("INFO", message);
        System.out.println("zoom");
        ((OrthographicCamera)camera).zoom = (initialDistance / distance) * ((OrthographicCamera)camera).zoom;
        camera.update();
        return true;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {

        return false;
    }

    @Override
    public void pinchStop() {

    }
}
