package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.I18NBundle;
import com.badlogic.gdx.utils.viewport.FitViewport;

import java.util.ArrayList;

public class MainMenuScreen implements Screen {

    private Main main;
    private SpriteBatch batch;
    private Stage stage;
    private Actor background;

    private ArrayList<MainMenuButton> mainMenuButtons = new ArrayList<MainMenuButton>();
    private int buttonAmount = 2;

    public MainMenuScreen(Main m) {
        main = m;
        batch = main.getBatch();

        stage = new Stage(new FitViewport(800, 450), batch);

        background = new StartmenuBackground();
        background.setSize(800, 450);
        background.setPosition(0, 0);

        createButtons();
        addActors();

        Gdx.input.setInputProcessor(stage);
    }

    private void createButtons() {
        I18NBundle myBundle = main.getMyBundle();
        mainMenuButtons.add(new MainMenuButton(main, myBundle.get("main1"), 0));
        mainMenuButtons.add(new MainMenuButton(main, myBundle.get("main2"), 1));
    }

    private void addActors() {
        stage.addActor(background);
        for(int i=0; i<buttonAmount; i++) {
            Button button = mainMenuButtons.get(i).getButton();
            stage.addActor(button);
        }
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
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
        stage.dispose();
    }

    public Stage getStage() {
        return stage;
    }
}
