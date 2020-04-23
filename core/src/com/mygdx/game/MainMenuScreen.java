package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.I18NBundle;
import com.badlogic.gdx.utils.viewport.FitViewport;
import java.util.ArrayList;

/**
 * MainMenuScreen is a object base class to create a Screen.
 *
 * @author  Jennina Färm
 * @author  Tommi Häkkinen
 * @version 2020.2204
 * @since 1.8
 */

public class MainMenuScreen implements Screen {

    /**
     * Main to ask SpriteBatch and I18NBundle and pass over to other objects
     */
    private Main main;

    /**
     * Stage to place Actors
     */
    private Stage stage;

    /**
     * Background to create background for the screen
     */
    private Background background;

    /**
     * ArrayList to contain MainMenuButtons
     */
    private ArrayList<MainMenuButton> mainMenuButtons = new ArrayList<>();

    /**
     * Constructor. Sets stage, background, buttons and inputProcessor of the MainMenuScreen.
     *
     * @param m Main that contains metadata
     */
    MainMenuScreen(Main m) {
        main = m;
        SpriteBatch batch = main.getBatch();

        stage = new Stage(new FitViewport(800, 450), batch);

        background = new Background(new Texture(Gdx.files.internal("startscreen.png")));
        background.setSize(800, 450);

        createButtons();
        addActors();

        Gdx.input.setInputProcessor(stage);
    }

    /**
     * Created buttons of the screen.
     */
    private void createButtons() {
        I18NBundle myBundle = main.getMyBundle();
        mainMenuButtons.add(new MainMenuButton(main, myBundle.get("main1"), 0));
        mainMenuButtons.add(new MainMenuButton(main, myBundle.get("main2"), 1));
    }

    /**
     * Sets Actors to the Stage
     */
    private void addActors() {
        stage.addActor(background);
        stage.addActor(mainMenuButtons.get(0).getButton());
        stage.addActor(mainMenuButtons.get(1).getButton());
    }

    /**
     * Method to ask Stage of the screen.
     *
     * @return stage of the screen
     */
    Stage getStage() {
        return stage;
    }

    @Override
    public void show() {

    }

    /**
     * Calls for the draw -method of the Stage
     *
     * @param delta delta time of the device
     */
    @Override
    public void render(float delta) {
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

    /**
     * Calls the dispose -method of the Stage
     */
    @Override
    public void dispose() {
        stage.dispose();
    }


}
