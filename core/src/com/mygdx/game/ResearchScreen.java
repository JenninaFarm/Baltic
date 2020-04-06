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
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.I18NBundle;
import com.badlogic.gdx.utils.viewport.FitViewport;

import java.util.ArrayList;

public class ResearchScreen implements Screen {

    private Main main;
    private SpriteBatch batch;
    private Stage stage;
    private Stage stageUI;
    private Stage stageInfo;
    private ArrayList<ResearchButton> researchButtons = new ArrayList<>();
    private static boolean [] booleans;
    private int researchAmount = 6;
    private ReturnButton returnButton;
    private Label moneyLabel;
    private Camera camera;
    private Vector2 dragNew, dragOld;


    public ResearchScreen(Main m) {
        main = m;
        batch = main.getBatch();

        stage = new Stage(new FitViewport(800, 450), batch);
        stageUI = new Stage(new FitViewport(800, 450), batch);
        stageInfo = new Stage(new FitViewport(800, 450), batch);

        camera = stage.getCamera();

        createButtons();
        addActors();
        createMoneyLabel();
    }

    public static void setBooleanArray(boolean [] array) {

        booleans = array;
    }
    private void createButtons() {
        for(int i=0; i<researchAmount; i++){
            researchButtons.add(new ResearchButton(main, i, booleans[i]));
        }
        returnButton = new ReturnButton(main, 2);
    }

    private void addActors() {
        stageUI.addActor(returnButton);
        for(int i=0; i<researchAmount; i++) {
            Button button = researchButtons.get(i).getButton();
            stage.addActor(button);
        }
    }

    public void addToStage(InfoLabel infoLabel) {
        TextArea textArea = infoLabel.getInfoLabel();
        stageInfo.addActor(textArea);
    }

    public void clearStageInfo() {
        stageInfo.clear();
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

        setResearchesAvailable();

        stage.act(Gdx.graphics.getDeltaTime());
        moneyLabel.setText(main.getMoney());
        stage.draw();

        stageUI.act(Gdx.graphics.getDeltaTime());
        stageUI.draw();

        stageInfo.draw();
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

        camera.position.x = MathUtils.clamp(camera.position.x, 0, 800);
        camera.position.y = MathUtils.clamp(camera.position.y, 0, 450);
    }

    private void setResearchesAvailable() {
        researchButtons.get(0).setAvailable();
        researchButtons.get(4).setAvailable();
        //researchButtons.get(6).setAvailable();
        //researchButtons.get(9).setAvailable();

        for(int i=0; i<3; i++) {
            if(booleans[i]) {
                researchButtons.get(i+1).setAvailable();
            }
        }
        if(booleans[4] /*&& booleans[8]*/) {
            researchButtons.get(5).setAvailable();
        }
        /*
        for(int i=0; i<2; i++) {
            if(booleans[i+6]) {
                researchButtons.get(i+7).setAvailable();
            }
        }
        if(booleans[9]) {
            researchButtons.get(10).setAvailable();
        }
        if(booleans[10]) {
            researchButtons.get(11).setAvailable();
            researchButtons.get(12).setAvailable();
            researchButtons.get(13).setAvailable();
        }
        if(booleans[13]) {
            researchButtons.get(14).setAvailable();
            researchButtons.get(15).setAvailable();
        }
        if(booleans[14]) {
            researchButtons.get(16).setAvailable();
        }
        if(booleans[15]) {
            researchButtons.get(17).setAvailable();
            researchButtons.get(18).setAvailable();
           TÄHÄN JOTAIN MITEN SAADAAN JOKO 17 TAI 18. EI VOI OSTAA MOLENPIA
        }*/
    }

    public Stage getStage() {
        return stage;
    }
    public Stage getStageUI() {
        return stageUI;
    }

    public Stage getStageInfo() {
        return stageInfo;
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
        Save.saveVariables();
    }

    @Override
    public void dispose() {
        stage.dispose();
        stageUI.dispose();
        stageInfo.dispose();
    }
}
