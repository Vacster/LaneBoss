package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Timer.Task;

public class ChangeTexture extends Task{

	private Image im;
	private Drawable dr;
	
	public ChangeTexture(Image im, Drawable drawable) {
		this.im = im;
		this.dr = drawable;
	}
	
	@Override
	public void run() {
		im.setDrawable(dr);
	}

}
