package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;

/**
 * MoneyButton is an object base class to create a Texture extending Actor with inputListener.
 *
 * @author  Jennina Färm
 * @author  Tommi Häkkinen
 * @version 2020.2204
 * @since 1.8
 */
public class MoneyButton extends Actor {

    /**
     *
     */
    private static int [] maxAmount = {0, 0, 0, 0, 5000, 5000};
    /**
     * Main to handle meta data of the game
     */
    private Main main;
    /**
     * Texture that is created and drawn
     */
    private Texture coin;
    /**
     * Sound of the coin clicked
     */
    private Sound coinSound = Gdx.audio.newSound(Gdx.files.internal("sounds/coin.wav"));
    private int index;
    private int originalX;
    private int originalY;
    /**
     *
     */
    private int timeWhenClickedInSec;
    private static float [] multipliers = {4, 4, 4, 4, 50, 50};
    private static int [] lastTimeClicked = new int[6];
    private int moneyCollected;

    MoneyButton(Main m, final int x, final int y, int i) {
        main = m;
        originalX = x;
        originalY = y;
        index = i;
        setX(originalX);
        setY(originalY);
        coin = new Texture(Gdx.files.internal("coin-icon.png"));
        setWidth(coin.getWidth() / 1.7f);
        setHeight(coin.getHeight() / 1.7f);
        setBounds(getX(), getY(), getWidth(), getHeight());

        addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                timeWhenClickedInSec = Utils.getCurrentTimeInSeconds();
                moneyCollected = countMoney(timeWhenClickedInSec);
                if (main.soundeffects_ON) {
                    coinSound.play(1f);
                }
                System.out.println("money collected:" + moneyCollected);
                main.nonStaticSetMoney(main.nonStaticGetMoney() + moneyCollected);
                lastTimeClicked[index] = timeWhenClickedInSec;
                Save.saveVariables();
                Save.loadVariables();

                MoveToAction moveToAction = new MoveToAction();
                moveToAction.setPosition(300, 410);
                moveToAction.setDuration(0.5f);
                MoneyButton.this.addAction(moveToAction);

                return true;
            }
        });
    }

    private void move(int toX, int toY) throws InterruptedException {
        int amountX = toX - originalX;
        int amounty = toY - originalY;
        for(int i=0; i<Math.abs(amountX); i++){
            setX(getX() + i+1);
            for(int j=0; j<Math.abs(amounty); j++) {
                setY(getY() + j + 1);
            }
        }
    }

    static void addToMaxAmount(int amount, int index) {
        maxAmount[index] += amount;
        System.out.println("new max Amount: " + index + " farm: " + maxAmount[index]);
    }

    public void draw(Batch batch, float alpha) {
        int potentialMoney = countMoney(Utils.getCurrentTimeInSeconds());
        setTouchable(Touchable.disabled);
        if(potentialMoney > 10 * multipliers[index] || potentialMoney < 2 * multipliers[index]) {
            setTouchable(Touchable.enabled);
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

    static int[] getMaxAmount() {
        return maxAmount;
    }

    static void setMaxAmount(int [] array) {
        maxAmount = array;
    }

    static void addToMultiplier(float addedmp, int farmindex) {
        multipliers[farmindex] += addedmp;
        System.out.println("New multiplier for farm " + farmindex + ": " + multipliers[farmindex]);
    }

    private int countMoney(int timeNowInSec) {
        int countedMoney;
        int timePassedInSec = timeNowInSec - lastTimeClicked[index];

        int possibleAmount = (int)(timePassedInSec*multipliers[index]);
        if(possibleAmount > maxAmount[index]) {
            countedMoney = maxAmount[index];
        } else {
            countedMoney = possibleAmount;
        }
        return countedMoney;
    }

    //called when new coin is added to stage. It set's the lastTimeClicked to active mode;
    void setClicked() {
        lastTimeClicked[index] = Utils.getCurrentTimeInSeconds();
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

    public static void setMultipliers(float [] array) {
        multipliers = array;
    }

    public void disposeCoinSound() {
        coinSound.dispose();
    }
}
