package com.mygdx.game;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class MoneyLabel extends Actor {

    private Main main;
    private Label moneyLabel;
    private Skin mySkin;
    private int money;


    public MoneyLabel(Main m) {
        main = m;
        mySkin = main.getMySkin();
        money = main.getMoney();

        moneyLabel = new Label(Integer.toString(money), mySkin);
    }
}
