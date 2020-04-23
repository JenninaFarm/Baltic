package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;

public class MoneyButton extends Actor {

    private Main main;
    private static int [] maxAmount = {0, 0, 0, 0, 5000, 5000};

    private int timeWhenClickedInSec;
    private Texture coin;
    private int index;
    private int originalX;
    private int originalY;

    private Sound coinSound = Gdx.audio.newSound(Gdx.files.internal("sounds/coin.wav"));

    private static float [] multipliers = new float[6];
    private static int [] lastTimeClicked = new int[6];
    private int moneyCollected;

    public MoneyButton(Main m, final int x, final int y, int i) {
        main = m;
        originalX = x;
        originalY = y;
        index = i;
        setX(originalX);
        setY(originalY);
        coin = new Texture(Gdx.files.internal("coin-icon.png"));
        setWidth(coin.getWidth()/1.7f);
        setHeight(coin.getHeight()/1.7f);
        setBounds(getX(), getY(), getWidth(), getHeight());

        addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                timeWhenClickedInSec = Utils.getCurrentTimeInSeconds();
                moneyCollected = countMoney(timeWhenClickedInSec);
                coinSound.play(timeWhenClickedInSec);

                System.out.println("money collected:" + moneyCollected);
                main.setMoney(main.nonStaticGetMoney() + moneyCollected);
                lastTimeClicked[index] = timeWhenClickedInSec;
                Save.saveVariables();
                Save.loadVariables();

                MoveToAction moveAction = new MoveToAction();

                moveAction.setPosition(300, 410);
                moveAction.setDuration(0.5f);

                MoneyButton.this.addAction(moveAction);
                return true;
            }
        });
    }

    public static void addToMaxAmount(int amount, int index) {
        maxAmount[index] += amount;
        System.out.println("new max Amount: " + index + " farm: " + maxAmount[index]);
    }

    public static void setMultipliers(float [] array) {
        multipliers = array;
    }

    public static int[] getMaxAmount() {
        return maxAmount;
    }

    public static void addToMultiplier(float addedmp, int farmindex) {
        multipliers[farmindex] += addedmp;
        System.out.println("New multiplier for farm " + farmindex + ": " + multipliers[farmindex]);
    }

    public static void setMaxAmount(int [] array) {
        maxAmount = array;
    }

    public static int[] getLastTimeClicked() {
        return lastTimeClicked;
    }

    public static void setLastTimeClicked(int [] array) {
        lastTimeClicked = array;
    }


    public static float[] getMultipliers() {
        return multipliers;
    }

    public int countMoney(int timeNowInSec) {
        int countedMoney = 0;
        int timePassedInSec = timeNowInSec - lastTimeClicked[index];

        int possibleAmount = (int)(timePassedInSec*multipliers[index]);
        if(possibleAmount > maxAmount[index]) {
            countedMoney = maxAmount[index];
        } else {
            countedMoney = possibleAmount;
        }
        return countedMoney;
    }

    public void draw(Batch batch, float alpha) {
        int potentialMoney = countMoney(Utils.getCurrentTimeInSeconds());
        if(potentialMoney > 5 * multipliers[index]) {
            batch.draw(coin, getX(), getY(), getWidth(), getHeight());

            //when coin is att 300, 410 location, then it comes to back it's original location
            if(getX() == 300 && getY() == 410) {
                MoveToAction moveToAction = new MoveToAction();
                moveToAction.setPosition(originalX, originalY);
                moveToAction.setDuration(0f);
                MoneyButton.this.addAction(moveToAction);
            }
        }
    }

    //called when new coin is added to stage. It set's the lastTimeClicked to active mode;
    public void setClicked() {
        lastTimeClicked[index] = Utils.getCurrentTimeInSeconds();
    }
}
