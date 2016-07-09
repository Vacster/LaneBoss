package com.mygdx.game;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class Chip extends Image{

	private final Chip chip;
	private int id;
	public static final int BUSTER_SHOT = 1, WIDE_BLADE = 2, HEALING = 3, LONG_BLADE = 4;
	
	public Chip(final ClientSelectMenu client, int X, int Y, int randy) {
		super(TextureLoader.getIcon(randy));
		id = randy;
		setBounds(70.0f + (193*X), 70.0f + (193*Y), 193.0f, 193.0f);
		chip = this;
		
		addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				client.addChip(chip);
				super.clicked(event, x, y);
			}
		});
	}
	
	public int getId()
	{
		return id;
	}
	
}
