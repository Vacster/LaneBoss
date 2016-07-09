package com.mygdx.game;

import com.badlogic.gdx.scenes.scene2d.Stage;

public class OrientationAndroid implements OrientationHelper{

	@Override
	public void changeOrientation(boolean portrait, Stage stage) {
		Ori.change(portrait, stage);
	}

}
