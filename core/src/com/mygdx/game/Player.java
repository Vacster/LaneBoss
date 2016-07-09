package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Timer;
import com.sun.jmx.snmp.tasks.Task;

public class Player extends Image{

	private int posX = 1, posY = 1, player, lanesX[] = {0,0,0} , lanesY[] = {50, 170, 285};
	private Timer timer1;
	
	public Player(int player) {
		super(player==1?TextureLoader.playerP1:TextureLoader.playerP2);
		setZIndex(1000);//To the front
		this.player = player;
		if(this.player == 1){ lanesX[0] = 0; lanesX[1] = 226; lanesX[2] = 455; }
		else {lanesX[0] = 680; lanesX[1] = 906; lanesX[2] = 1135;}
		
		setBounds(lanesX[posX], lanesY[posY], 200, 200);
		timer1 = new Timer();
	}

	public void shootAnimation()
	{
		setDrawable(player==1?TextureLoader.shootingDrawableP1:TextureLoader.shootingDrawableP2);
		timer1.scheduleTask(new ChangeTexture(this, player==1?TextureLoader.playerDrawableP1:TextureLoader.playerDrawableP2), 0.4f);
	}
	
	public void moveLeft()
	{
		if(posX != 0)
			posX--;
		setX(lanesX[posX]);
	}
	
	public void moveRight()
	{
		if(posX != 2)
			posX++;
		setX(lanesX[posX]);
	}
	
	public void moveUp()
	{
		if(posY != 2)
			posY++;
		setY(lanesY[posY]);
	}
	
	public void moveDown()
	{
		if(posY != 0)
			posY--;
		setY(lanesY[posY]);
	}

	public int getPosY() {
		return posY;
	}

	public int getPosX() {
		if(player == 1)
			return posX;
		else 
			return posX + 3;
	}

	
}
