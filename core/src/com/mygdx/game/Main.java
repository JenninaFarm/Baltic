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
	private static boolean gameBegan = false;
	private static int startingTime;

	private SpriteBatch batch;
	private MainMenuScreen mainMenuScreen;
	private MapScreen mapScreen;
	private ArrayList<FarmScreen> farmScreens;

	private int farmAmount = 4;
	private ResearchScreen researchScreen;
	private OptionsScreen optionsScreen;

	private static int money;
	private static int balticSituation = 0;

	private Skin mySkin;
	private I18NBundle myBundle;
	private Locale locale = new Locale("en", "GB");

	public static void setGameBegan(boolean gb) {
		gameBegan = gb;
	}

	public static int getBalticSituation() { return balticSituation; }

	public void changeLocale(Locale l) {
		locale = l;
	}

	public void switchScreen(int x, int y) {
		if(x == 1) {
			setScreen(mainMenuScreen);
			Gdx.input.setInputProcessor(mainMenuScreen.getStage());
		} else if (x == 2) {
			setScreen(mapScreen);
			InputMultiplexer multiplexer = new InputMultiplexer(mapScreen.getStageUI(), mapScreen.getStage(), mapScreen.getStageInfo());
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

	public static int getStartingTime() {
		return startingTime;
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

	public static int getMoney() {
		return money;
	}

	public int nonStaticGetMoney() {
		return money;
	}

	@Override
	public void create () {
		batch = new SpriteBatch();

		myBundle = I18NBundle.createBundle(Gdx.files.internal("MyBundle"), locale);
		mySkin = new Skin(Gdx.files.internal("mySkinTest/mySkinTest.json"));

		Save.loadVariables();

		//Save starting time for the game only when you begin a new game
		if(!gameBegan) {
			startingTime = Utils.getCurrentTimeInSeconds();
			gameBegan = true;
			Save.saveStartingTime();
		}

		mainMenuScreen = new MainMenuScreen(this);
		mapScreen = new MapScreen(this);

		farmScreens = new ArrayList<>();
		for(int i=0; i<farmAmount; i++) {
			farmScreens.add(new FarmScreen(this, i));
		}
		researchScreen = new ResearchScreen(this);
		optionsScreen = new OptionsScreen(this);

		setScreen(mainMenuScreen);
		Gdx.input.setInputProcessor(mainMenuScreen.getStage());
	}

	public static void setBalticSituation(int bs) {
		balticSituation = bs;
	};

	public SpriteBatch getBatch() {
		return batch;
	}

	public I18NBundle getMyBundle() {
		return myBundle;
	}

	public Skin getMySkin() { return mySkin;}

	public static void callCreate(Main m) {
		m.create();
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
