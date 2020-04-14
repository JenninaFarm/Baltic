package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;

public class MoneyButton extends Actor {

    private Main main;
    private Texture button;
    private float width;
    private float height;

    private int timeWhenClickedInSec;
    private int money;
    private int index;
    private int originalX;
    private int originalY;

    private static float [] multipliers = new float[4];
    private static int [] lastTimeClicked = new int[4];

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

        addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                timeWhenClickedInSec = Utils.getCurrentTimeInSeconds();
                if((timeWhenClickedInSec - lastTimeClicked[index]) >= 5) {
                    countMoney();

                    System.out.println("money collected:" + money);
                    main.setMoney(main.nonStaticGetMoney() + money);
                    System.out.println("balance now:" + main.nonStaticGetMoney());
                    lastTimeClicked[index] = timeWhenClickedInSec;
                    Save.saveVariables();
                    Save.loadVariables();

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
        int timePassedInSec = timeWhenClickedInSec - lastTimeClicked[index];
        System.out.println("timePassed: " + timePassedInSec);
        System.out.println("lastTimeClicked: " + index + lastTimeClicked[index]);

        money = (int)(timePassedInSec * multipliers[index]);
    }

    public void draw(Batch batch, float alpha) {

        int currentTime = Utils.getCurrentTimeInSeconds();
        int timePassedInSec = currentTime - lastTimeClicked[index];
        int potentialMoney = (int)(timePassedInSec * multipliers[index]);
        if(potentialMoney > 5 * multipliers[index] || getX() != 300) {
            batch.draw(button, this.getX(), this.getY(), width, height);

            //when coin is att 300, 410 location, then it comes to back it's original location
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

    public static int[] getLastTimeClicked() {
        return lastTimeClicked;
    }

    public static void setLastTimeClicked(int [] array) {
        lastTimeClicked = array;
    }

    //called when new coin is added to stage. It set's the lastTimeClicked to active mode;
    public void setClicked() {
        lastTimeClicked[index] = Utils.getCurrentTimeInSeconds();
    }

    public static float[] getMultipliers() {
        return multipliers;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }
}
