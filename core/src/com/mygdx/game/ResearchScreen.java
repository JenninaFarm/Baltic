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
    private ArrayList<ResearchButton> researchButtons = new ArrayList<ResearchButton>();
    private TextureRegion [] buttonTextureArray;
    private int researchAmount = 6;
    private Texture tutkimus1;

    public ResearchScreen(Main m) {
        main = m;
        batch = main.getBatch();

        stage = new Stage(new FitViewport(1000, 500), batch);
        tutkimus1 = new Texture(Gdx.files.internal("tutkimus1.jpg"));

        createButtonArray();
        createButtons();
        addActors();

        Gdx.input.setInputProcessor(stage);
    }

    private void createButtonArray() {
        TextureRegion [][] buttonRegion = TextureRegion.split(
                tutkimus1,
                tutkimus1.getWidth() / 3,
                tutkimus1.getHeight() / 2);
        buttonTextureArray = Utils.transformTo1D(buttonRegion, 3, 2);
    }

    private  void createButtons() {
        for(int i=0; i<researchAmount; i++){
            researchButtons.add(new ResearchButton(main, buttonTextureArray[i], i));
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
