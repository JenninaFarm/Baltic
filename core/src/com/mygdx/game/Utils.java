package com.mygdx.game;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Utils {
    public static TextureRegion[] transformTo1D(TextureRegion [][] tmp, int frameCols, int frameRows) {
        TextureRegion [] frames = new TextureRegion[frameCols * frameRows];
        int index = 0;

        for(int i=0; i < frameRows; i++) {
            for(int j=0; j < frameCols; j++) {
                frames[index] = tmp[i][j];
                index++;
            }
        }
        return frames;
    }

    public static TextureRegion [] deleteTheLastFrame(TextureRegion [] tmp) {
        TextureRegion [] temp = new TextureRegion[tmp.length - 1];
        for(int i=0; i<temp.length; i++) {
            temp[i] = tmp[i];
        }
        return temp;
    }

    public static TextureRegion[][] createTextureRegion2DArray(Texture t) {
        TextureRegion [][] regionArray = TextureRegion.split(
                t,
                t.getWidth() / 3,
                t.getHeight() / 2);
        return regionArray;
    }
}