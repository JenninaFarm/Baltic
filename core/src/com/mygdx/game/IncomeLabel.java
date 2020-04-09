package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;

public class IncomeLabel extends Actor {

    private Main main;
    private TextArea label;
    private int money;
    private Texture coin;
    private String per;


    public IncomeLabel(Main m, String perWhat) {
        main = m;
        per = perWhat;
        Skin mySkin = new Skin(Gdx.files.internal("mySkinTest/mySkinTest.json"));
        money = main.nonStaticGetMoney();


        label = new TextArea(Integer.toString(money), mySkin);
        label.setX(470);
        label.setY(412);
        label.setWidth(200);
        label.setHeight(33);
        coin = new Texture(Gdx.files.internal("coin-icon.png"));
    }


    public void draw(Batch batch, float alpha) {
        money = main.nonStaticGetMoney();
        label.setText("   /h " + per + " " + money);
        label.draw(batch, alpha);
        batch.draw(coin, label.getX(), label.getY() + 2, 30, 30);
    }
}

