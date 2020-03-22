package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;

import java.util.ArrayList;

public class FarmScreen implements Screen {

    private Main main;
    private int farmIndex;
    private SpriteBatch batch;
    private Stage stage;
    private ReturnButton returnButton;

    private ArrayList<FarmButton> farmButtons = new ArrayList<FarmButton>();
    private TextureRegion[] buttonTextureArray;
    private int upgradeAmount = 5;
    private Texture buttonRegionTexture;
    private Label moneyLabel;

    public FarmScreen(Main m, int i) {
        main = m;
        farmIndex = i;
        batch = main.getBatch();

        stage = new Stage(new FitViewport(800, 450), batch);
        buttonRegionTexture = new Texture(Gdx.files.internal("farmButtons.png"));

        createButtons();
        addActors();
        createMoneyLabel();

        Gdx.input.setInputProcessor(stage);
    }

    private  void createButtons() {
        TextureRegion [][] buttonRegion = Utils.createTextureRegion2DArray(buttonRegionTexture, 2, 3);
        buttonTextureArray = Utils.transformTo1D(buttonRegion, 2, 3);

        farmButtons.add(new FarmButton(main, buttonTextureArray[0], 0, farmIndex, 2.5));
        farmButtons.add(new FarmButton(main, buttonTextureArray[1], 1, farmIndex, 3.5));
        farmButtons.add(new FarmButton(main, buttonTextureArray[2], 2, farmIndex, 2.5));
        farmButtons.add(new FarmButton(main, buttonTextureArray[3], 3, farmIndex, 4));
        farmButtons.add(new FarmButton(main, buttonTextureArray[4], 4, farmIndex, 2.5));
        farmButtons.add(new FarmButton(main, buttonTextureArray[5], 5, farmIndex, 3));

        returnButton = new ReturnButton(main, 2);
    }

    private void addActors() {
        for(int i=0; i<upgradeAmount; i++) {
            stage.addActor(farmButtons.get(i));
        }
        stage.addActor(returnButton);
    }

    public void setAvailable(int index) {
        farmButtons.get(index).setAvailable();
    }

    private void createMoneyLabel() {
        Label.LabelStyle label1Style = new Label.LabelStyle();
        BitmapFont myFont = new BitmapFont(Gdx.files.internal("sansFont.fnt"));
        label1Style.font = myFont;
        label1Style.fontColor = Color.RED;

        moneyLabel = new Label(Integer.toString(main.getMoney()), label1Style);
        moneyLabel.setSize(800 ,30);
        moneyLabel.setPosition(200,400);
        moneyLabel.setAlignment(Align.center);
        stage.addActor(moneyLabel);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 1, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.end();

        stage.act(Gdx.graphics.getDeltaTime());
        moneyLabel.setText(main.getMoney());
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
