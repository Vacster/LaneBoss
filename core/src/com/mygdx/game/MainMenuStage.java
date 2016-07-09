package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class MainMenuStage extends Stage{

	
	public MainMenuStage(final MainMenu main, final Skin skin) {
		super(new ExtendViewport(1360, 720));
		Gdx.input.setInputProcessor(this);

		Label label;
		TextButton server;
		TextButton client;
		label = new Label("PIXEL WAR", skin);
		server = new TextButton("SERVER", skin, "small");
		client = new TextButton("CLIENT", skin, "small");
		
		label.setPosition(96.0f, 440.0f);
		addActor(label);
		
		server.setBounds(510.0f, 270.0f, 340.0f, 90.0f);
		server.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				main.changeStage(new ServerLobby(main, skin, getViewport()));
				super.clicked(event, x, y);
			}
		});
		addActor(server);
		
		client.setBounds(510.0f, 90.0f, 340.0f, 90.0f);
		client.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				main.changeStage(new ClientLobby(main, skin, getViewport()));
				super.clicked(event, x, y);
			}
		});
		addActor(client);
	}
	
}
