package com.mygdx.game.desktop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.OrientationHelper;

public class OrientationDesktop implements OrientationHelper{

	@Override
	public void changeOrientation(boolean portrait, Stage stage) {
		Gdx.app.log("Ayy", "Lmao");
	}
	
}
