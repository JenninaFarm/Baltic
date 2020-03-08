package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.Main;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.pauseWhenBackground = true;
		config.pauseWhenMinimized = true;
		config.title = "Project Baltic";
		config.height = 500;
		config.width = 1000;
		config.x = 0;
		config.y = 0;
		new LwjglApplication(new Main(), config);
	}
}
