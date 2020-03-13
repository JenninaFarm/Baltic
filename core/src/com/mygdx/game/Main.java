package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

public class Main extends Game {
	private SpriteBatch batch;
	private MainMenuScreen mainMenuScreen;
	private MapScreen mapScreen;
	private ArrayList<FarmScreen> farmScreens;
	private int farmAmount = 4;
	private ResearchScreen researchScreen;
	private OptionsScreen optionsScreen;

	@Override
	public void create () {
		batch = new SpriteBatch();
		mainMenuScreen = new MainMenuScreen(this);
		mapScreen = new MapScreen(this);

		farmScreens = new ArrayList<FarmScreen>();
		for(int i=0; i<farmAmount; i++) {
			farmScreens.add(new FarmScreen(this));
		}

		researchScreen = new ResearchScreen(this);
		optionsScreen = new OptionsScreen(this);

		setScreen(mainMenuScreen);
	}

	public void switchScreen(int x, int y) {
		if(x == 1) {
			setScreen(mainMenuScreen);
			Gdx.input.setInputProcessor(mainMenuScreen.getStage());

		} else if (x == 2) {
			setScreen(mapScreen);
			Gdx.input.setInputProcessor(mapScreen.getStage());
		} else if(x == 3) {
			if(y > 0) {
				setScreen(farmScreens.get(y - 1));
				Gdx.input.setInputProcessor(farmScreens.get(y - 1).getStage());
			}
		} else if (x == 4) {
			setScreen(researchScreen);
			Gdx.input.setInputProcessor(researchScreen.getStage());
		} else if(x == 5) {
			setScreen(optionsScreen);
			Gdx.input.setInputProcessor(optionsScreen.getStage());
		}
	}

	public SpriteBatch getBatch() {
		return batch;
	}

	@Override
	public void render () {
		super.render();
	}

	@Override
	public void dispose () {
		batch.dispose();
	}

}
