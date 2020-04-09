package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.I18NBundle;

import java.util.ArrayList;
import java.util.Locale;

public class Main extends Game {
	private SpriteBatch batch;
	private MainMenuScreen mainMenuScreen;
	private MapScreen mapScreen;
	private ArrayList<FarmScreen> farmScreens;

	private int farmAmount = 4;
	private ResearchScreen researchScreen;
	private OptionsScreen optionsScreen;

	private static int money;
	private int balticSituation = 0;

	private Skin mySkin;
	private I18NBundle myBundle;

	@Override
	public void create () {
		batch = new SpriteBatch();
		Locale locale = new Locale("en", "UK");
		myBundle = I18NBundle.createBundle(Gdx.files.internal("MyBundle"), locale);
		Save.loadVariables();
		mainMenuScreen = new MainMenuScreen(this);
		mapScreen = new MapScreen(this);

		farmScreens = new ArrayList<>();
		for(int i=0; i<farmAmount; i++) {
			farmScreens.add(new FarmScreen(this, i));
		}

		mySkin = new Skin(Gdx.files.internal("mySkinTest/mySkinTest.json"));

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
			InputMultiplexer multiplexer = new InputMultiplexer(mapScreen.getStageUI(), mapScreen.getStage());
			Gdx.input.setInputProcessor(multiplexer);
		} else if(x == 3) {
			if(y >= 0) {
				setScreen(farmScreens.get(y));
				InputMultiplexer multiplexer = new InputMultiplexer(farmScreens.get(y).getStage(), farmScreens.get(y).getStageUI(), farmScreens.get(y).getStageInfo());
				Gdx.input.setInputProcessor(multiplexer);
			}
		} else if (x == 4) {
			setScreen(researchScreen);
			InputMultiplexer multiplexer = new InputMultiplexer(researchScreen.getStage(), researchScreen.getStageUI(), researchScreen.getStageInfo());
			Gdx.input.setInputProcessor(multiplexer);
		} else if(x == 5) {
			setScreen(optionsScreen);
			Gdx.input.setInputProcessor(optionsScreen.getStage());
		}
	}

	public static void setMoney(int amount) {
		if(amount < 0) {
			System.out.println("Program is failing. Not enough money to this action.");
		} else {
			money = amount;
		}
	}

	public void addBalticSituation(int amount) {
		if(amount < 0 || amount > 4) {
			System.out.println("Program is failing. Amount not possible");
		} else {
			balticSituation += amount;
			System.out.println("baltic Situation = " + balticSituation);
		}
	}

	public void addResearchScreenStage(InfoLabel infoLabel) {
		researchScreen.addToStage(infoLabel);
	}

	public void addFarmScreenStage(InfoLabel infoLabel, int index) {
		farmScreens.get(index).addToStage(infoLabel);
	}

	public void clearInfoLabel(int index) {
		researchScreen.clearStageInfo();
		farmScreens.get(index).clearStageInfo();
	}

	public void setAvailable(int index) {
		for(int i=0; i<farmAmount; i++) {
			farmScreens.get(i).setAvailable(index);
		}
	}

	public static int getMoney() {
		return money;
	}

	public int nonStaticGetMoney() {
		return money;
	}

	public int getBalticSituation() { return balticSituation; };

	public SpriteBatch getBatch() {
		return batch;
	}

	public I18NBundle getMyBundle() {
		return myBundle;
	}

	public Skin getMySkin() { return mySkin;}

	@Override
	public void render () {
		super.render();
	}

	@Override
	public void dispose () {
		batch.dispose();
	}

}
