package com.mygdx.game;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;

public class ChipWideSword extends Image{

	private int lanesX[] = {0, 226, 455, 680, 906, 1135}, lanesY[] = {50, 170, 285}, posX, posY;
	private Player enemy;
	private boolean player, hit = false;
	private ServerGame server;
	private Timer time1;
	
	public ChipWideSword(final boolean playerOneShot, int posX, int posY, Player enemy, ServerGame server) {
		super(TextureLoader.getWideBladeArea(posY));
		this.player = playerOneShot;
		this.server = server;
		this.enemy = enemy;
		this.posX = posX;
		this.posY = posY;
		setBounds(lanesX[posX+(player?1:-1)], lanesY[posY] - 115.0f, 225.0f, 350.0f);
		time1 = new Timer();
		time1.scheduleTask(new ChangeTexture(this, playerOneShot?TextureLoader.wideBladeP11:TextureLoader.wideBladeP21), 0.06f);
		time1.scheduleTask(new ChangeTexture(this, playerOneShot?TextureLoader.wideBladeP12:TextureLoader.wideBladeP22), 0.27f);
		time1.scheduleTask(new ChangeTexture(this, playerOneShot?TextureLoader.wideBladeP13:TextureLoader.wideBladeP23), 0.35f);
		time1.scheduleTask(new ChangeTexture(this, playerOneShot?TextureLoader.wideBladeP14:TextureLoader.wideBladeP24), 0.42f);
		time1.scheduleTask(new Task(){
			@Override
			public void run() {
				remove();
			}
		}, 0.45f);
	}
	
	@Override
	public void act(float delta) {
		if(!hit && enemy.getPosX() == posX+(player?1:-1) && (enemy.getPosY() == posY || enemy.getPosY() == posY-1 || enemy.getPosY() == posY+1))
		{
			hit = true;
			server.getHit(player, 35);
		}
		super.act(delta);
	}
	
}
