package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.I18NBundle;

public class FarmWorker extends Actor {

    private Main main;
    private Button button1;
    private int cost;

    public FarmWorker(Main m, final FarmScreen farmScreen) {
        main = m;

        Skin mySkin = new Skin(Gdx.files.internal("mySkinTest/mySkinTest.json"));
        I18NBundle myBundle = main.getMyBundle();

        cost = Integer.parseInt(myBundle.get("workerCost"));

        setWidth(200);
        setHeight(60);

        setX(550);
        setY(350);

        button1 = new TextButton(myBundle.get("worker"), mySkin);
        button1.setSize(200, 50);
        button1.setPosition(getX(), getY());
        button1.setDisabled(true);
        int workerAmount = farmScreen.getWorkerAmount();
        if (workerAmount >= 3) {
            button1.setChecked(true);
            button1.getStyle().down = button1.getStyle().checked;
        }

        button1.addListener(new InputListener() {
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                int currentMoney = main.getMoney();
                int workerAmount = farmScreen.getWorkerAmount();
                if (currentMoney >= cost && workerAmount < 3) {
                    System.out.println("bought");
                    main.setMoney(currentMoney - cost);
                    farmScreen.addWorker();
                    button1.setChecked(false);
                    Save.saveVariables();
                    Save.loadVariables();
                } else {
                    System.out.println("Not enough money!");
                    System.out.println("cost: " + cost);
                    System.out.println("current balance: " + main.getMoney());
                }

                workerAmount = farmScreen.getWorkerAmount();
                if (workerAmount >= 3) {
                    button1.setChecked(true);
                    button1.getStyle().down = button1.getStyle().checked;
                }
                return true;
            }
        });
    }

    public Button getButton() {
        return button1;
    }
}
