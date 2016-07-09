package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class Projectile extends Image{

	private int lanes[] = {0, 240, 480};
	
	public Projectile(Texture text, int lane) {
		super(text);
		Gdx.app.log("Projectile", "Projectile Fired");
		setBounds(lanes[lane], 1360.0f, 240.0f, 300.0f);
	}
	
	@Override
	public void act(float delta) {
		moveBy(0,-640*delta);
		super.act(delta);
	}
	
}
