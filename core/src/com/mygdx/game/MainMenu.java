package com.mygdx.game;

import org.omg.CORBA.FREE_MEM;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.esotericsoftware.kryonet.Client;

public class MainMenu extends ApplicationAdapter{

	private Skin skin;
	private Stage currentStage;
	public TextureLoader tLoader;
	
	public MainMenu() {
		super();
	}
	
	@Override
	public void create() {
		skin = new Skin(Gdx.files.internal("ui-gray.json"));	
		tLoader = new TextureLoader();
		changeStage(new MainMenuStage(this, skin));
		super.create();
	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		if(currentStage != null){
			currentStage.draw();
			currentStage.act();
		}
		super.render();
	}
	
	@Override
	public void dispose() {
		super.dispose();
	}
	
	@Override
	public void resize(int width, int height) {
		if(currentStage != null)
			currentStage.getViewport().update(width, height, false);
	}
	
	public void changeStage(Stage stage)
	{
		if(currentStage != null)
			currentStage.clear();
		currentStage = stage;
		resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	}
}
