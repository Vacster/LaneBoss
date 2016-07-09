package com.mygdx.game;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;

public class ChipLongSword extends Image{

	private boolean playerOne, hit = false;
	private int posX, posY;
	private Player enemy;
	private ServerGame server;
	private int lanesX[] = {0, 226, 455, 680, 906, 1135}, lanesY[] = {50, 170, 285};
	private Timer timer;
	
	public ChipLongSword(final boolean playerOneShot, int posX, int posY, Player enemy, ServerGame server) {
		super(TextureLoader.longBlade);
		posX += (playerOneShot?1:-2);
		setBounds(lanesX[posX], lanesY[posY], 455.0f, 111.0f);
		this.playerOne = playerOneShot;
		this.posX = posX;
		this.posY = posY;
		this.enemy = enemy;
		this.server = server;
		timer = new Timer();
		timer.scheduleTask(new Task(){
			@Override
			public void run() {
				remove();
			}
		}, 0.5f);
	}
	
	@Override
	public void act(float delta) {
		if(!hit && enemy.getPosY() == posY && (enemy.getPosX() == posX || enemy.getPosX() == posX+(playerOne?1:-1)))
		{
			hit = true;
			server.getHit(playerOne, 35);
		}
		super.act(delta);
	}
}
