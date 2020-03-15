package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;

import java.util.ArrayList;

public class MainMenuScreen implements Screen {

    private Main main;
    private SpriteBatch batch;
    private Stage stage;

    private ArrayList<MainMenuButton> mainMenuButtons = new ArrayList<MainMenuButton>();
    private TextureRegion[] buttonTextureArray;
    private int buttonAmount = 2;
    private Texture buttonRegionTexture;

    public MainMenuScreen(Main m) {
        main = m;
        batch = main.getBatch();

        stage = new Stage(new FitViewport(1000, 500), batch);
        buttonRegionTexture = new Texture(Gdx.files.internal("tutkimus1.jpg"));

        createButtons();
        addActors();

        Gdx.input.setInputProcessor(stage);
    }

    private void createButtons() {
        TextureRegion [][] buttonRegion = Utils.createTextureRegion2DArray(buttonRegionTexture);
        buttonTextureArray = Utils.transformTo1D(buttonRegion, 3, 2);
        for(int i=0; i<buttonAmount; i++){
            mainMenuButtons.add(new MainMenuButton(main, buttonTextureArray[i], i));
        }
    }

    private void addActors() {
        for(int i=0; i<buttonAmount; i++) {
            stage.addActor(mainMenuButtons.get(i));
        }
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
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

    }

    public Stage getStage() {
        return stage;
    }
}
