package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.esotericsoftware.kryonet.Client;
import com.mygdx.game.buffers.BufferFinishSelection;

public class ClientSelectMenu extends Stage{

	private TextButton okButton;
	private int chips[] = {0,0,0,0,0}, currentChip = 0;
	private boolean finished = false;
	
	public ClientSelectMenu(final MainMenu main, final Skin skin, Viewport viewport, final Client client, final int player) {
		super(viewport);
		for(int x = 0; x < 3; x++)
			for(int y = 0; y < 3; y++)
				addActor(new Chip(this, x, y, MathUtils.random(1,4)));//TODO: change whenever more chips are added
		
		okButton = new TextButton("OK", skin, "small");
		okButton.setBounds(890.0f, 70.0f, 400.0f, 193.0f);
		okButton.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				finished = true;
				BufferFinishSelection buffer = new BufferFinishSelection();
				buffer.chips = chips;
				buffer.playerOne = player == 1;
				client.sendTCP(buffer);
				okButton.remove();
				super.clicked(event, x, y);
			}
		});
		addActor(okButton);
		
		Gdx.input.setInputProcessor(this);
	}

	public void addChip(Chip chip) {
		if(!finished)
		{
			if(currentChip < 5)
			{
				chips[currentChip] = chip.getId();
				currentChip++;
				chip.remove();
			}
		}
	}
	
}
