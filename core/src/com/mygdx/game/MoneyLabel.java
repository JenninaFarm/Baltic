package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;

public class MoneyLabel extends Actor {

    private Main main;
    private TextArea moneyLabel;
    private Skin mySkin;
    private int money;
    private Texture coin;


    public MoneyLabel(Main m) {
        main = m;
        mySkin = new Skin(Gdx.files.internal("mySkinTest/mySkinTest.json"));
        money = main.nonStaticGetMoney();

        moneyLabel = new TextArea(Integer.toString(money), mySkin);
        moneyLabel.setX(500);
        moneyLabel.setY(412);
        moneyLabel.setWidth(150);
        moneyLabel.setHeight(33);
        coin = new Texture(Gdx.files.internal("coin-icon.png"));
    }


    public void draw(Batch batch, float alpha) {
        money = main.nonStaticGetMoney();
        moneyLabel.setText("    " + money);
        moneyLabel.draw(batch, alpha);
        batch.draw(coin, moneyLabel.getX(), moneyLabel.getY() + 2, 30, 30);
    }
}
