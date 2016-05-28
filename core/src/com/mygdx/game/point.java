package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class point extends Image{
	
	public point() {
		super(new Texture(Gdx.files.internal("point.png")));
	}
	
}
