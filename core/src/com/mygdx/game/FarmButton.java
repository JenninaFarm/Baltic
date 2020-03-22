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
    private boolean available = false;

    public FarmButton(Main m, TextureRegion buttonTexture, int buttonI, final int farmI, final double multiplier) {
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

        addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                int currentMoney = main.getMoney();
                if(available && currentMoney >= cost) {
                    System.out.println("bought");
                    main.setMoney(currentMoney-cost);
                    main.setMultiplier(multiplier, farmI);

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
    }

    public void draw(Batch batch, float alpha) {
        if(available) {
            batch.draw(button, this.getX(), this.getY(), width, height);
        }
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }
}
