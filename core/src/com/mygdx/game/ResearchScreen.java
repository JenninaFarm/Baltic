package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;

import java.util.ArrayList;

public class ResearchScreen implements Screen {

    private Main main;
    private SpriteBatch batch;
    private Stage stage;
    private ResearchButton researchButton;
    private ArrayList<ResearchButton> researchButtons = new ArrayList<ResearchButton>();
    private ArrayList<Texture> buttonTextures = new ArrayList<Texture>();
    private int researchAmount = 2;
    private Texture tutkimus1;

    public ResearchScreen(Main m) {
        main = m;
        batch = main.getBatch();

        stage = new Stage(new FitViewport(1000, 500), batch);
        tutkimus1 = new Texture(Gdx.files.internal("tutkimus1.jpg"));
        Texture tutkimus2 = new Texture(Gdx.files.internal("tutkimus2.jpg"));
        buttonTextures.add(tutkimus1);
        buttonTextures.add(tutkimus2);
        for(int i=0; i<researchAmount; i++){
            researchButtons.add(new ResearchButton(main, buttonTextures.get(i), i));
        }
        for(int i=0; i<researchAmount; i++) {
            stage.addActor(researchButtons.get(i));
        }
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 1, 1);
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

    }
}
