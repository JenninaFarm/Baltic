package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
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

	private int money;
	private double multiplier = 0.5;
	private double balticSituation = 0;

	@Override
	public void create () {
		batch = new SpriteBatch();
		money = 6000;
		mainMenuScreen = new MainMenuScreen(this);
		mapScreen = new MapScreen(this);

		farmScreens = new ArrayList<FarmScreen>();
		for(int i=0; i<farmAmount; i++) {
			farmScreens.add(new FarmScreen(this));
		}

		researchScreen = new ResearchScreen(this);
		optionsScreen = new OptionsScreen(this);

		setScreen(mainMenuScreen);
		Gdx.input.setInputProcessor(mainMenuScreen.getStage());
	}

	public void switchScreen(int x, int y) {
		if(x == 1) {
			setScreen(mainMenuScreen);
			Gdx.input.setInputProcessor(mainMenuScreen.getStage());
		} else if (x == 2) {
			setScreen(mapScreen);
			InputMultiplexer multiplexer = new InputMultiplexer(mapScreen.getStage(), mapScreen.getStageUI());
			Gdx.input.setInputProcessor(multiplexer);
		} else if(x == 3) {
			if(y > 0) {
				setScreen(farmScreens.get(y - 1));
				Gdx.input.setInputProcessor(farmScreens.get(y - 1).getStage());
			}
		} else if (x == 4) {
			setScreen(researchScreen);
			InputMultiplexer multiplexer = new InputMultiplexer(researchScreen.getStage(), researchScreen.getStageUI());
			Gdx.input.setInputProcessor(multiplexer);
		} else if(x == 5) {
			setScreen(optionsScreen);
			Gdx.input.setInputProcessor(optionsScreen.getStage());
		}
	}

	public void setMoney(int amount) {
		if(amount < 0) {
			System.out.println("Program is failing. Not enough money to this action.");
		} else {
			money = amount;
		}
	}

	public int getMoney() {
		return money;
	}

	public void setMultiplier(double amount) {
		if(amount < 0) {
			System.out.println("Program is failing. Multiplier is below zero.");
		} else {
			multiplier = amount;
		}
	}

	public double getMultiplier() {
		return multiplier;
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
