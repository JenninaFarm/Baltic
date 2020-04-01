package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.FitViewport;

import java.util.ArrayList;

public class OptionsScreen implements Screen {

    private Main main;
    private SpriteBatch batch;
    private Stage stage;
    private Table table;
    private Skin mySkin;


    private ArrayList<OptionsButton> optionsButtons = new ArrayList<OptionsButton>();
    private TextureRegion[] buttonTextureArray;
    private int buttonAmount = 3;
    private Texture buttonRegionTexture;
    private ReturnButton returnButton;


    public OptionsScreen(Main m) {
        main = m;
        batch = main.getBatch();
        stage = new Stage(new FitViewport(800, 450), batch);
        table = new Table();
        mySkin = new Skin(Gdx.files.internal("testUiSkin.json"));

        buttonRegionTexture = new Texture(Gdx.files.internal("tutkimus1.jpg"));

        //createButtons();
        //addActors();

        int width = 100;
        int height = 20;
        int index = 0;

        Button textButton = new TextButton("asetus1", mySkin);
        textButton.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("to map");
                main.switchScreen(2, 0);
                return true;
            }
        });
        Button textButton2 = new TextButton("asetus2", mySkin);
        textButton2.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("to map");
                main.switchScreen(2, 0);
                return true;
            }
        });


        returnButton = new ReturnButton(main, 1);
        table.add(returnButton).expand().top().left();
        table.row();
        table.add(textButton).expand().bottom();
        table.row();
        table.add(textButton2).expand().top();
        table.setFillParent(true);
        stage.addActor(table);
        Gdx.input.setInputProcessor(stage);
    }

    private void createButtons() {
        TextureRegion [][] buttonRegion = Utils.createTextureRegion2DArray(buttonRegionTexture, 2, 2);
        buttonTextureArray = Utils.transformTo1D(buttonRegion, 2, 2);
        for(int i=0; i<buttonAmount; i++){
            optionsButtons.add(new OptionsButton(main, buttonTextureArray[i], i));
        }
        returnButton = new ReturnButton(main, 1);
    }

    private void addActors() {
        for(int i=0; i<buttonAmount; i++) {
            stage.addActor(optionsButtons.get(i));
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

    }
}
