package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.ParallelAction;

public class MoneyButton extends Actor {

    private Main main;
    private Texture button;
    private float width;
    private float height;

    private int timeLastClicked;
    private int timeWhenClickedInSec;
    private int money;
    private int index;
    private int originalX;
    private int originalY;

    private static float [] multipliers = new float[5];

    public MoneyButton(Main m, final int x, final int y, int i, float mp) {

        main = m;
        button = new Texture(Gdx.files.internal("coin-icon.png"));
        index = i;
        multipliers[i] = mp;
        originalX = x;
        originalY = y;
        setX(originalX);
        setY(originalY);
        width = button.getWidth()/1.7f;
        height = button.getHeight()/1.7f;
        setWidth(width);
        setHeight(height);
        setBounds(getX(), getY(), getWidth(), getHeight());
        timeLastClicked = Utils.getCurrentTimeInSeconds();

        addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                timeWhenClickedInSec = Utils.getCurrentTimeInSeconds();
                if((timeWhenClickedInSec - timeLastClicked) >= 5) {
                    countMoney();

                    System.out.println("money collected:" + money);
                    main.setMoney(main.getMoney() + money);
                    System.out.println("balance now:" + main.getMoney());
                    timeLastClicked = timeWhenClickedInSec;

                    MoveToAction moveAction = new MoveToAction();

                    moveAction.setPosition(300, 410);
                    moveAction.setDuration(0.5f);

                    MoneyButton.this.addAction(moveAction);
                }
                return true;
            }
        });
    }

    private void countMoney() {
        int timePassedInSec = timeWhenClickedInSec - timeLastClicked;
        System.out.println("timePassed: " + timePassedInSec);

        money = (int)(timePassedInSec * multipliers[index]);
    }

    public void draw(Batch batch, float alpha) {

        int currentTime = Utils.getCurrentTimeInSeconds();
        int timePassedInSec = currentTime - timeLastClicked;
        int potentialMoney = (int)(timePassedInSec * multipliers[index]);
        if(potentialMoney > 5 * multipliers[index] || getX() != 300) {
            batch.draw(button, this.getX(), this.getY(), width, height);

            if(getX() == 300 && getY() == 410) {
                MoveToAction moveToAction = new MoveToAction();
                moveToAction.setPosition(originalX, originalY);
                moveToAction.setDuration(0f);
                MoneyButton.this.addAction(moveToAction);
            }
        }
    }

    public static void addToMultiplier(float addedmp, int farmindex) {
        multipliers[farmindex] += addedmp;
        System.out.println("New multiplier for farm " + farmindex + ": " + multipliers[farmindex]);
    }

    public static float[] getMultipliers() {
        return multipliers;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }
}
