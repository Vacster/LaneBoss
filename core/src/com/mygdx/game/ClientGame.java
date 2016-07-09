package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.esotericsoftware.kryonet.Client;
import com.mygdx.game.buffers.BufferBusterShot;
import com.mygdx.game.buffers.BufferPlayerMove;
import com.mygdx.game.buffers.BufferUseChip;
import com.mygdx.game.listeners.ClientGameGestureListener;
import com.mygdx.game.listeners.ClientGameListener;

public class ClientGame extends Stage{
	
	private MainMenu main;
	private Skin skin;
	private Client client;
	private TextButton shoot, chip;
	private int player;
	private InputMultiplexer im;
	
	public ClientGame(final MainMenu main, final Skin skin, Viewport viewport, final Client client, final int player) {
		super(viewport);
		this.main = main;
		this.client = client;
		this.player = player;
		this.skin = skin;
		
		shoot = new TextButton("SHOOT", skin, "small");
		shoot.setBounds(0, 0, 360.0f, 360.0f);
		shoot.addListener(new ClickListener(){
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				BufferBusterShot buffer = new BufferBusterShot();
				buffer.player1 = player == 1;
				client.sendTCP(buffer);
				event.handle();
				super.touchUp(event, x, y, pointer, button);
			}
		});
		addActor(shoot);
		
		chip = new TextButton("CHIP", skin, "small");
		chip.setBounds(0, 360.0f, 360.0f, 360.0f);
		chip.addListener(new ClickListener(){
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				BufferUseChip buffer = new BufferUseChip();
				buffer.playerOne = player == 1;
				client.sendTCP(buffer);
				event.handle();
				super.touchUp(event, x, y, pointer, button);
			}
		});
		addActor(chip);
		
		this.client.addListener(new ClientGameListener(this));
		
		im = new InputMultiplexer();
		im.addProcessor(this);
		im.addProcessor(new GestureDetector(new ClientGameGestureListener(this)));
		Gdx.input.setInputProcessor(im);
	}

	public void disconnect() {
		Gdx.app.postRunnable(new Runnable() {
			public void run() {
				main.changeStage(new MainMenuStage(main, skin));
			}
		});
	}
	
	public void move(int direction)
	{
		BufferPlayerMove buffer = new BufferPlayerMove();
		buffer.msg = direction;
		buffer.player = player;
		client.sendTCP(buffer);
	}

	public void selectStage() {
		Gdx.app.postRunnable(new Runnable() {
			public void run() {
				main.changeStage(new ClientSelectMenu(main, skin, getViewport(), client, player));
			}
		});
	}

	public void finishSelection() {
		Gdx.app.postRunnable(new Runnable() {
			public void run() {
				main.changeStage(new ClientGame(main, skin, getViewport(), client, player));
			}
		});	
	}
	
}
