package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class FarmButton extends Actor {
    private Main main;
    private TextureRegion button;
    private float width;
    private float height;
    private int buttonIndex;
    private int farmIndex;

    private int cost;
    private boolean available;
    private boolean bought;

    private static boolean [][] availableBooleans = new boolean[5][6];
    private static boolean [][] boughtBooleans = new boolean[5][6];

    public FarmButton(Main m, TextureRegion buttonTexture, int buttonI, final int farmI, final float multiplier, boolean a, boolean b) {
        buttonIndex = buttonI;
        farmIndex = farmI;
        main = m;
        cost = 1000 + 1500*(int)Math.pow(2, buttonI);
        button = buttonTexture;
        width = button.getRegionWidth()/2f;
        height = button.getRegionHeight()/2f;
        setX(800/2f - width/2f);
        setY(400 - height/2f - buttonIndex*height);
        setWidth(width);
        setHeight(height);
        setBounds(getX(), getY(), getWidth(), getHeight());
        available = a;
        bought = b;

        addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                int currentMoney = main.getMoney();
                if(available && currentMoney >= cost) {
                    System.out.println("bought");
                    bought = true;
                    main.setMoney(currentMoney-cost);
                    MoneyButton.setMultiplier(multiplier, farmIndex);
                    boughtBooleans[farmIndex][buttonIndex] =  true;
                } else {
                    System.out.println("Not enough money or upgrade not available!");
                    System.out.println(cost);
                    System.out.println(main.getMoney());
                }

                return true;
            }
        });
    }

    public void setAvailable() {
        available = true;
        availableBooleans[farmIndex][buttonIndex] = true;
    }

    public static boolean [][] getAvailableBooleans() {
        return availableBooleans;
    }

    public static boolean [][] getBoughtBooleans() {
        return boughtBooleans;
    }

    public void draw(Batch batch, float alpha) {
        if(available && !bought) {
            batch.draw(button, this.getX(), this.getY(), width, height);
        }
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }
}
