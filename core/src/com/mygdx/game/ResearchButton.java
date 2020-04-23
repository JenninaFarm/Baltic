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


public class ResearchButton extends Actor {

    private Main main;
    private ResearchScreen researchScreen;
    private Button button1;
    private int index;
    private int cost;
    private boolean bought;
    private boolean available = false;

    private InfoLabel infoLabel;

    private static boolean [] researchBooleans = new boolean [19];

    public ResearchButton(Main m, ResearchScreen rs, int i, boolean b) {
        main = m;
        researchScreen = rs;
        index = i;
        bought = b;

        I18NBundle myBundle = main.getMyBundle();
        cost = Integer.parseInt(myBundle.get("researchCost" + index));


        float width = 200;
        float height = 60;

        button1 = new TextButton(myBundle.get("research" + i), main.getMySkin());
        button1.setSize(width, height);
        button1.setPosition(Integer.parseInt(myBundle.get("researchX" + i)), Integer.parseInt(myBundle.get("researchY" + i)));


        if(bought) {
            button1.getStyle().checked = button1.getStyle().over;
            button1.setChecked(true);
            button1.getStyle().checkedOver = button1.getStyle().up;
            button1.setDisabled(true);

        } else if(available){
            button1.setChecked(false);
        } else {
            button1.getStyle().checked = button1.getStyle().checkedOver;
            button1.setChecked(true);
            button1.setDisabled(true);
        }

        button1.addListener(new InputListener() {

            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                int currentMoney = main.getMoney();
                //researchScreen.setInfoVisible(false);

                if(currentMoney >= cost && !bought && available) {
                    System.out.println("bought");
                    main.setMoney(currentMoney - cost);
                    button1.getStyle().checked = button1.getStyle().over;
                    button1.setChecked(true);
                    button1.getStyle().checkedOver = button1.getStyle().up;
                    button1.setDisabled(true);
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
                researchScreen.addInfoLabel(infoLabel);
                researchScreen.setInfoVisible(true);
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                researchScreen.setInfoVisible(false);
            }
        });
    }

    public void setResearchAvailable() {
        available = true;
        if(!bought) {
            button1.setChecked(false);
            button1.setDisabled(false);
        }
    }

    public Button getButton() {
        return button1;
    }


    public static boolean [] getResearchBooleans() {
        return researchBooleans;
    }

    public void draw(Batch batch, float alpha) {
        //button1.draw(batch, alpha);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }
}
