package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Main extends Game {
	//commit
	SpriteBatch batch;
	MainMenuScreen mainMenuScreen;
	MapScreen mapScreen;

	@Override
	public void create () {
		batch = new SpriteBatch();
		mainMenuScreen = new MainMenuScreen(this);
		mapScreen = new MapScreen(this);
		setScreen(mainMenuScreen);
	}

	public void switchScreen(int x, int y) {
		if(x == 1) {
			setScreen(mainMenuScreen);
		} else if (x == 2) {
			setScreen(mapScreen);
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
