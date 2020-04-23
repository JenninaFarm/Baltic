package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.I18NBundle;
import com.badlogic.gdx.utils.viewport.FitViewport;

import java.util.ArrayList;

public class OptionsScreen implements Screen {

    private Main main;
    private SpriteBatch batch;
    private Stage stage;
    private Background background;

    private ArrayList<OptionsButton> optionsButtons = new ArrayList<>();
    private int buttonAmount = 5;
    private ReturnButton returnButton;


    public OptionsScreen(Main m) {
        main = m;
        batch = main.getBatch();
        stage = new Stage(new FitViewport(800, 450), batch);

        background = new Background(new Texture(Gdx.files.internal("startscreen.png")));
        background.setSize(800, 450);

        createButtons();
        addActors();

        Gdx.input.setInputProcessor(stage);
    }

    private void createButtons() {
        I18NBundle myBundle = main.getMyBundle();
        for(int i=0; i<buttonAmount; i++) {
            optionsButtons.add(new OptionsButton(main, myBundle.get("options" + i), i));
        }
        returnButton = new ReturnButton(main, 1);
    }

    private void addActors() {
        stage.addActor(background);
        for(int i=0; i<buttonAmount; i++) {
            Button button = optionsButtons.get(i).getButton();
            stage.addActor(button);
        }
        stage.addActor(returnButton);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.end();

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    public Stage getStage() {
        return stage;
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
}
