package com.mygdx.game;

import java.io.IOException;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

public class MyGdxGame extends ApplicationAdapter {
	String ipAddress = "192.168.43.1";
	boolean isServer;
	Connection con;
	
	Client client;
	Server server;
	Stage stage;
	
	public MyGdxGame(boolean isServer) {
		this.isServer = isServer;
	}
	
	@Override
	public void create () {
		/*
		stage = new Stage(new FitViewport(1600,900));
		stage.addListener(new ClickListener(){
			@Override
			public boolean handle(Event e) {
				if(isPressed())
				{
					Coordinates cord = new Coordinates();
					cord.x = getTouchDownX() - 2.5f;
					cord.y = getTouchDownY() - 2.5f;
					point p = new point();
					p.setPosition(cord.x,cord.y);
					stage.addActor(p);
					if(isServer)
						server.sendToTCP(con.getID(), cord);
					else
						client.sendTCP(cord);
					e.handle();
				}
				return super.handle(e);
			}
		});
		Gdx.input.setInputProcessor(stage);
		if(isServer)
		{
			server = new Server();
			server.start();
			try {
				server.bind(5000);
			} catch (IOException e) {
				e.printStackTrace();
			}
			Kryo kryo = server.getKryo();
			kryo.register(SomeRequest.class);
			kryo.register(point.class);
			kryo.register(Coordinates.class);
			server.addListener(new Listener() {
				public void received (Connection connection, Object object) {
			    	   if(object instanceof Coordinates)
			    	   {
			    		   final Coordinates cord = (Coordinates)object;
			    		   createPoint(cord);
			    	   }
			       }
				@Override
				public void connected(Connection connection) {
					con = connection;
					super.connected(connection);
				}
			});
		}
		else
		{
			client = new Client();
			client.start();
			try {
				client.connect(50000, ipAddress, 5000);
			} catch (IOException e) {
				e.printStackTrace();
				Gdx.app.exit();
			}
			if(client.isConnected())
				System.out.println("Works");
			else
				System.out.println("Fuck");
			
			Kryo kryo = client.getKryo();
			kryo.register(SomeRequest.class);
			kryo.register(point.class);
			kryo.register(Coordinates.class);
			client.addListener(new Listener() {
				 public void received (Connection connection, Object object) {
			    	   if(object instanceof Coordinates)
			    	   {
			    		   final Coordinates cord = (Coordinates)object;
			    		   createPoint(cord);
			    	   }
			       }
			 });
			
		}*/
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.draw();
	}
	/*
	public void createPoint(final Coordinates cord)
	{
		Gdx.app.postRunnable(new Runnable() {
			public void run() {
				point p = new point();
		    	p.setPosition(cord.x, cord.y);
		    	stage.addActor(p);
			}
		});
	}
	*/
	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height, true);
		super.resize(width, height);
	}
}
