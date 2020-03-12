package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class MainMenuScreen implements Screen {

    private Main main;
    private SpriteBatch batch;

    //uutta
    private Stage stage;
    private ButtonActor buttonIcon;
    private boolean visible = true;

    public MainMenuScreen(Main m) {
        main = m;
        batch = main.getBatch();

        //uutta
        stage = new Stage(new FitViewport(1000, 500), batch);
        buttonIcon = new ButtonActor(main);
        stage.addActor(buttonIcon);

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.end();

       /* if(Gdx.input.justTouched()) {
            main.switchScreen(2,0);
        }*/

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
}
