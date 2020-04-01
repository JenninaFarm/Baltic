package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

import java.util.ArrayList;

import static com.mygdx.game.MapScreen.coins;

public class MoneyButton extends Actor {

    private Main main;
    private Texture button;
    private float width;
    private float height;

    private int timeLastClicked;
    private int timeWhenClickedInSec;
    private int money;
    private static int index;
    private static float multiplier;

    private static float [] multipliers = new float[5];

    public MoneyButton(Main m, int x, int y, int i, float mp) {

        main = m;
        button = new Texture(Gdx.files.internal("coin-icon.png"));
        index = i;
        multiplier = mp;
        setX(x);
        setY(y);
        width = button.getWidth()/2f;
        height = button.getHeight()/2f;
        setWidth(width);
        setHeight(height);
        setBounds(getX(), getY(), getWidth(), getHeight());
        timeLastClicked = Utils.getCurrentTimeInSeconds();

        addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                timeWhenClickedInSec = Utils.getCurrentTimeInSeconds();
                countMoney();

                System.out.println("money collected:" + money);
                main.setMoney(main.getMoney() + money);
                System.out.println("balance now:" + main.getMoney());
                return true;
            }
        });
    }

    private void countMoney() {
        int timePassedInSec = timeWhenClickedInSec - timeLastClicked;
        System.out.println("timePassed: " + timePassedInSec);

        money = (int)(timePassedInSec * multiplier);
        timeLastClicked = timeWhenClickedInSec;
    }

    public void draw(Batch batch, float alpha) {
        int currentTime = Utils.getCurrentTimeInSeconds();
        int timePassedInSec = currentTime - timeLastClicked;
        int potentialMoney = (int)(timePassedInSec * multiplier);
        if(potentialMoney > 5 * multiplier) {
            batch.draw(button, this.getX(), this.getY(), width, height);
        }
    }

    public static void setMultiplier(float mp, int i) {
        coins.get(i).addToMultiplier(mp);
    }

    public static void addToMultiplier(float x) {
        multiplier += x;
        System.out.println("new multiplier:" + multiplier);
        multipliers[index] = multiplier;
    }

    public static float[] getMultipliers() {
        return multipliers;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }
}
