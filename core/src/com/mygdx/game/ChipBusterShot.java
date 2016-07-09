package com.mygdx.game;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;

public class ChipBusterShot extends Image{

	private int lanesX[] = {0, 226, 495, 680, 906, 1135}, lanesY[] = {50, 170, 285}, posX, posY;
	private Player enemy;
	private boolean player;
	private ServerGame server;
	private Timer time1;

	public ChipBusterShot(final boolean playerOneShot, int posX, int posY, Player enemy, ServerGame server) {
		super(playerOneShot?TextureLoader.normalShotP1:TextureLoader.normalShotP2);
		this.posX = posX;
		this.posY = posY;
		this.server = server;
		this.player = playerOneShot;
		this.enemy = enemy;
		setBounds(lanesX[posX], lanesY[posY], 193, 193);//TODO: pretty
		time1 = new Timer();
		time1.scheduleTask(new Task(){
			@Override
			public void run() {
				if(playerOneShot)
					moveRight();
				else
					moveLeft();
			}
		}, 0, 0.22f);
	}
	
	private void moveRight()
	{
		if(posX < 5)
		{
			posX++;
			update();	
		}
		else
		{
			time1.clear();
			remove();
		}
	}
	
	private void moveLeft()
	{
		if(posX > 0)
		{
			posX--;
			update();
		}
		else
		{
			time1.clear();
			remove();
		}
	}
	
	private void update()
	{
		setPosition(lanesX[posX], lanesY[posY]);
		if(enemy.getPosX() == posX && enemy.getPosY() == posY)
		{
			server.getHit(player, 20);
			remove();
		}
	}
	
	@Override
	public boolean remove() {
		posX = -10;
		posY = -10;
		time1.clear();
		return super.remove();
	}
}
