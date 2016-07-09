package com.mygdx.game;

import java.io.IOException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import com.mygdx.game.listeners.ClientLobbyListener;

public class ClientLobby extends Stage{
	
	private TextField portTextField, ipTextField;
	private Label titleLabel, ipLabel;
	private TextButton textButton;
	private Client client;
	private ClientLobbyListener clientLobbyListener;
	private final MainMenu main;
	private final Skin skin;
	FileHandle h;
	
	public ClientLobby(final MainMenu main, Skin skin, Viewport viewport) {
		super(viewport);
		this.main = main;
		this.skin = skin;
		
		Gdx.input.setInputProcessor(this);
		
		titleLabel = new Label("SELECT PORT", skin, "small");
		titleLabel.setPosition(440.0f, 590.0f);
		addActor(titleLabel);
		
		portTextField = new TextField("", skin);
		portTextField.setBounds(510.0f, 470.0f, 340.0f, 80.0f);
		addActor(portTextField);
		
		ipLabel = new Label("SELECT IP ADDRESS", skin, "small");
		ipLabel.setPosition(320.0f, 370.0f);
		addActor(ipLabel);
		
		h = Gdx.files.local("misc.ini");
		if(!h.exists())
		{
			try {
				h.file().createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		ipTextField = new TextField(h.readString(), skin);
		ipTextField.setBounds(510.0f, 225.0f, 340.0f, 80.0f);
		addActor(ipTextField);
		
		textButton = new TextButton("OK", skin, "small");
		textButton.setBounds(510.0f, 90.0f, 340.0f, 80.0f);
		textButton.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				if(!portTextField.getText().equals(""))
					try{
						h.writeString(ipTextField.getText(), false);
						connect(ipTextField.getText(), Integer.parseInt(portTextField.getText()));
					}catch(NumberFormatException e){
						Gdx.app.log("Error", "Invalid port.");
					}catch(Exception e)
					{
						Gdx.app.log("Error", "Invalid Port.");
					}
				super.clicked(event, x, y);
			}
		});
		addActor(textButton);
	}

	private void connect(String ipAddress, int port) {
		client = new Client();

		Kryo kryo = client.getKryo();
		KryoHelper.registerClasses(kryo);
		
		client.start();
		try {
			client.connect(30000, ipAddress, port);
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(!client.isConnected())
			main.changeStage(new MainMenuStage(main, skin));
		
		clientLobbyListener = new ClientLobbyListener(this);
		client.addListener(clientLobbyListener);
		ipLabel.setText("WAITING TO START");
		textButton.remove();
		ipTextField.remove();
		titleLabel.remove();
		portTextField.remove();
		
	}
	
	public void Disconnect()
	{
		Gdx.app.postRunnable(new Runnable() {
			public void run() {
				main.changeStage(new MainMenuStage(main, skin));
			}
		});
	}
	
	public void StartGame(final int x)
	{
		ipLabel.remove();
		Gdx.app.log("Client", "Start Game");
		Gdx.app.postRunnable(new Runnable() {
			public void run() {
				main.changeStage(new ClientGame(main, skin, getViewport(), client, x));
			}
		});
		client.removeListener(clientLobbyListener);
	}
}
