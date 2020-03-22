package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class ResearchButton extends Actor {

    private Main main;
    private TextureRegion button;
    private float width;
    private float height;
    private int index;
    private int cost;

    public ResearchButton(Main m, TextureRegion buttonTexture, int i, int costAmount) {
        index = i;
        cost = costAmount;
        main = m;
        button = buttonTexture;
        width = button.getRegionWidth()/2f;
        height = button.getRegionHeight()/2f;
        setX(800/2f - width/2f);
        setY(400 - height/2f - index*height);
        setWidth(width);
        setHeight(height);
        setBounds(getX(), getY(), getWidth(), getHeight());

        addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                int currentMoney = main.getMoney();
                if(currentMoney >= cost) {
                    System.out.println("bought");
                    main.setMoney(currentMoney - cost);
                    main.setAvailable(index);
                } else {
                    System.out.println("Not enough money!");
                    System.out.println(cost);
                    System.out.println(main.getMoney());
                }

                return true;
            }
        });
    }

    public void draw(Batch batch, float alpha) {
        batch.draw(button, this.getX(), this.getY(), width, height);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }
}
