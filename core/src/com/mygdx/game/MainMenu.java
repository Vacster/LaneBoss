package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class MainMenu extends ApplicationAdapter{

	Stage stage;
	Label label;
	TextButton server, client;
	Skin skin;
	
	@Override
	public void create() {
		skin = new Skin(Gdx.files.internal("ui-gray.json"));
		stage = new Stage(new FitViewport(1360, 720));
		label = new Label("LANE BOSS", skin);
		server = new TextButton("SERVER", skin, "small");
		client = new TextButton("CLIENT", skin, "small");
		label.setPosition(96.0f, 440.0f);
		stage.addActor(label);
		server.setBounds(510.0f, 270.0f, 340.0f, 90.0f);
		server.addListener(new ClickListener(){
			
		});
		stage.addActor(server);
		client.setBounds(510.0f, 90.0f, 340.0f, 90.0f);
		stage.addActor(client);
		Gdx.input.setInputProcessor(stage);
		super.create();
	}
	
	@Override
	public void render() {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.draw();
		super.render();
	}
	
	@Override
	public void dispose() {
		
		super.dispose();
	}
	
	@Override
	public void resize(int width, int height) {
		stage.getViewport().setScreenSize(width, height);
	}
}
