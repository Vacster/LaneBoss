package com.mygdx.game;

import java.io.IOException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

public class ServerLobby extends Stage{
	
	private Skin skin;
	private Server server;
	private Label titleLabel, playersLabel;
	private TextField textField;
	private TextButton okTextButton, startTextButton;
	
	public ServerLobby(final MainMenu main, Skin skin, Viewport viewport) {
		this.skin = skin;
		setViewport(viewport);
		
		titleLabel = new Label("SELECT PORT", skin, "small");
		titleLabel.setPosition(410.0f, 510.0f);
		addActor(titleLabel);
		
		textField = new TextField("", skin);
		textField.setBounds(510.0f, 360.0f, 340.0f, 80.0f);
		addActor(textField);
		
		okTextButton = new TextButton("OK", skin, "small");
		okTextButton.setBounds(510.0f, 200.0f, 340.0f, 110.0f);
		okTextButton.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				if(!textField.getText().equals(""))
					try{
						selectPort(Integer.parseInt(textField.getText()));
					}catch(NumberFormatException e){
						Gdx.app.log("Error", "Invalid port.");
					}
				super.clicked(event, x, y);
			}
		});
		addActor(okTextButton);
		
	}
	
	private void selectPort(int port)
	{
		server = new Server();
		server.start();
		try {
			server.bind(port);
		} catch (IOException e) {
			Gdx.app.log("Error", "Port " + port + " is invalid.\n");
			e.printStackTrace();
		}
		Kryo kryo = server.getKryo();
		KryoHelper.registerClasses(kryo);
		
		okTextButton.remove();
		textField.remove();
		titleLabel.setText("ONLINE PLAYERS");
		titleLabel.setPosition(380.0f, 510.0f);
		playersLabel = new Label("0", skin, "small");
		playersLabel.setPosition(660.0f ,390.0f);
		addActor(playersLabel);

		server.addListener(new Listener() {
			@Override
			public void connected(Connection connection) {
				if(server.getConnections().length >= 1)
				{
					playersLabel.setText(Integer.toString(server.getConnections().length));
					startTextButton = new TextButton("START", skin, "small");
					startTextButton.setBounds(510.0f, 200.0f, 340.0f, 110.0f);
					startTextButton.addListener(new ClickListener(){
						@Override
						public void clicked(InputEvent event, float x, float y) {
							//TODO: start game
							
							Gdx.app.log("Server", "Game Start");
							super.clicked(event, x, y);
						}
					});
					addActor(startTextButton);
				}
				super.connected(connection);
			}
			@Override
			public void disconnected(Connection connection) {
				playersLabel.setText(Integer.toString(server.getConnections().length));
				if(server.getConnections().length <= 0)
					startTextButton.remove();
				super.disconnected(connection);
			}
		});
	}
	@Override
	public void addActor(Actor actor) {
		super.addActor(actor);
	}
}
