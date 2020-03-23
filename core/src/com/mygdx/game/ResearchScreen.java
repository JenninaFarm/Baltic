package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;

import java.util.ArrayList;

public class ResearchScreen implements Screen {

    private Main main;
    private SpriteBatch batch;
    private Stage stage;
    private Stage stageUI;
    private ArrayList<ResearchButton> researchButtons = new ArrayList<>();
    private int researchAmount = 6;
    private Texture buttonRegionTexture;
    private Texture boughtRegionTexture;
    private ReturnButton returnButton;
    private Label moneyLabel;
    private Camera camera;
    private Vector2 dragNew, dragOld;


    public ResearchScreen(Main m) {
        main = m;
        batch = main.getBatch();

        stage = new Stage(new FitViewport(800, 450), batch);
        stageUI = new Stage(new FitViewport(800, 450), batch);

        buttonRegionTexture = new Texture(Gdx.files.internal("researchButtons.png"));
        boughtRegionTexture = new Texture(Gdx.files.internal("researchButtonsBought.png"));

        camera = stage.getCamera();

        createButtons();
        addActors();
        createMoneyLabel();
    }

    private  void createButtons() {
        TextureRegion [][] buttonRegion = Utils.createTextureRegion2DArray(buttonRegionTexture, 2, 3);
        TextureRegion [] buttonTextureArray = Utils.transformTo1D(buttonRegion, 2, 3);
        TextureRegion [][] buttonRegionBought = Utils.createTextureRegion2DArray(boughtRegionTexture, 2, 3);
        TextureRegion [] buttonTextureArrayBought = Utils.transformTo1D(buttonRegionBought, 2, 3);
        for(int i=0; i<researchAmount; i++){
            int costAmount = 2000 + 2000*(int)Math.pow(2, i);
            researchButtons.add(new ResearchButton(main, buttonTextureArray[i], buttonTextureArrayBought[i], i, costAmount));
        }
        returnButton = new ReturnButton(main, 2);
    }

    private void addActors() {
        stageUI.addActor(returnButton);
        for(int i=0; i<researchAmount; i++) {
            stage.addActor(researchButtons.get(i));
        }
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
        stageUI.addActor(moneyLabel);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        handleInput();
        camera.update();
        Gdx.gl.glClearColor(0, 0, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(Gdx.graphics.getDeltaTime());
        moneyLabel.setText(main.getMoney());
        stage.draw();

        stageUI.act(Gdx.graphics.getDeltaTime());
        stageUI.draw();
    }

    private void handleInput() {

        if (Gdx.input.justTouched()){
            dragNew = new Vector2(Gdx.input.getX(), Gdx.input.getY());
            dragOld = dragNew;
        }

        if (Gdx.input.isTouched()){
            dragNew = new Vector2(Gdx.input.getX(), Gdx.input.getY());
            if (!dragNew.equals(dragOld)){
                ((OrthographicCamera)camera).translate(dragOld.x - dragNew.x, dragNew.y - dragOld.y);
                dragOld = dragNew;
            }
        }

        camera.position.x = MathUtils.clamp(camera.position.x, 800 / 2f, 800 - 800 / 2f);
        camera.position.y = MathUtils.clamp(camera.position.y, 0, 450 - 450 / 2f);
    }

    public Stage getStage() {
        return stage;
    }
    public Stage getStageUI() { return stageUI; }

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
        stageUI.dispose();
    }
}
