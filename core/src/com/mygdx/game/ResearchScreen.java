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
    private ArrayList<ResearchButton> researchButtons = new ArrayList<>();
    private TextureRegion [] buttonTextureArray;
    private int researchAmount = 6;
    private Texture buttonRegionTexture;

    public ResearchScreen(Main m) {
        main = m;
        batch = main.getBatch();

        stage = new Stage(new FitViewport(1000, 500), batch);
        buttonRegionTexture = new Texture(Gdx.files.internal("tutkimus1.jpg"));

        createButtons();
        addActors();

        Gdx.input.setInputProcessor(stage);
    }

    private  void createButtons() {
        TextureRegion [][] buttonRegion = Utils.createTextureRegion2DArray(buttonRegionTexture);
        buttonTextureArray = Utils.transformTo1D(buttonRegion, 3, 2);
        for(int i=0; i<researchAmount; i++){
            int costAmount = 2000 + 2000*(int)Math.pow(2, i);
            researchButtons.add(new ResearchButton(main, buttonTextureArray[i], i, costAmount));
        }
    }

    private void addActors() {
        for(int i=0; i<researchAmount; i++) {
            stage.addActor(researchButtons.get(i));
        }
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
