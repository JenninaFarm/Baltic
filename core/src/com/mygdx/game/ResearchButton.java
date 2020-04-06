package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.I18NBundle;


public class ResearchButton extends Actor {

    private Main main;
    private Button button1;
    private float width;
    private float height;
    private int index;
    private int cost;
    private boolean bought;

    private InfoLabel infoLabel;

    private static boolean [] researchBooleans = new boolean [6];

    public ResearchButton(Main m, int i, boolean b) {
        main = m;
        index = i;
        bought = b;

        I18NBundle myBundle = main.getMyBundle();
        cost = Integer.parseInt(myBundle.get("researchCost" + index));
        Skin mySkin = new Skin(Gdx.files.internal("testUiSkin.json"));

        width = 200;
        height = 50;

        button1 = new TextButton(myBundle.get("research" + i), mySkin);
        button1.setSize(width, height);
        button1.setPosition(Integer.parseInt(myBundle.get("researchX" + i)), Integer.parseInt(myBundle.get("researchY" + i)));
        button1.getStyle().checked = button1.getStyle().down;

        if(bought) {
            button1.setChecked(true);
            button1.setTouchable(Touchable.disabled);

        } else {
            button1.setChecked(false);
        }

        button1.addListener(new InputListener() {

            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                int currentMoney = main.getMoney();

                if(currentMoney >= cost && !bought) {
                    System.out.println("bought");
                    main.setMoney(currentMoney - cost);
                    main.setAvailable(index);
                    button1.setChecked(false);
                    bought = true;
                    researchBooleans[index] = true;
                } else if(bought) {
                    System.out.println("Already researched");
                } else {
                    System.out.println("Not enough money!");
                    System.out.println("cost: " + cost);
                    System.out.println("current balance: " + main.getMoney());
                }
                return false;
            }

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                I18NBundle myBundle = main.getMyBundle();
                infoLabel = new InfoLabel(main, myBundle.get("researchInfo" + index));
                main.addResearchScreenStage(infoLabel);
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                main.clearInfoLabel();
            }
        });
    }

    public Button getButton() {
        return button1;
    }


    public static boolean [] getResearchBooleans() {
        return researchBooleans;
    }

    public void draw(Batch batch, float alpha) {
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }
}
