package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class MapScreen extends ApplicationAdapter implements Screen {

    private Main main;
    private SpriteBatch batch;
    private Actor map;

    private Stage stage;
    private MapButton mapButton;
    private boolean visible = false;

    public MapScreen(Main m) {
        main = m;
        batch = main.getBatch();

        stage = new Stage(new FitViewport(1000, 500), batch);
        mapButton = new MapButton(main);
        map = new MapBackground();
        map.setPosition(0, 0);

        stage.addActor(map);
        stage.addActor(mapButton);

        Gdx.input.setInputProcessor(stage);

        ((OrthographicCamera)stage.getCamera()).zoom += 1000;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        handleInput();
        stage.getCamera().update();

        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    private void handleInput() {

        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            ((OrthographicCamera)stage.getCamera()).zoom += 0.3f;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.Q)) {
            ((OrthographicCamera)stage.getCamera()).zoom -= 0.3f;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            stage.getCamera().translate(-7, 0, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            stage.getCamera().translate(7, 0, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            stage.getCamera().translate(0, -7, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            stage.getCamera().translate(0, 7, 0);
        }

        ((OrthographicCamera)stage.getCamera()).zoom = MathUtils.clamp(((OrthographicCamera)stage.getCamera()).zoom, 10f, 1000/stage.getCamera().viewportWidth);
        float effectiveViewportWidth = stage.getCamera().viewportWidth * ((OrthographicCamera)stage.getCamera()).zoom;
        float effectiveViewportHeight = stage.getCamera().viewportHeight * ((OrthographicCamera)stage.getCamera()).zoom;

        stage.getCamera().position.x = MathUtils.clamp(stage.getCamera().position.x, effectiveViewportWidth / 2f, 1000 - effectiveViewportWidth / 2f);
        stage.getCamera().position.y = MathUtils.clamp(stage.getCamera().position.y, effectiveViewportHeight / 2f, 500 - effectiveViewportHeight / 2f);
    }

    @Override
    public void resize(int width, int height) {
        stage.getCamera().viewportWidth = 30f;
        stage.getCamera().viewportHeight = 30f * height / width;
        stage.getCamera().update();
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
}
