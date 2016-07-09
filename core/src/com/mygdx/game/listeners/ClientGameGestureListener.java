package com.mygdx.game.listeners;

import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.ClientGame;
import com.mygdx.game.buffers.BufferPlayerMove;

public class ClientGameGestureListener implements GestureListener{

	ClientGame clientGame;
	
	public ClientGameGestureListener(ClientGame cl) {
		clientGame = cl;
	}
	
	@Override
	public boolean touchDown(float x, float y, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean tap(float x, float y, int count, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean longPress(float x, float y) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean fling(float velocityX, float velocityY, int button) {
		if(Math.abs(velocityX) > Math.abs(velocityY))
		{
			if(velocityX > 0)
				clientGame.move(BufferPlayerMove.RIGHT);
			else
				clientGame.move(BufferPlayerMove.LEFT);
		}else
		{
			if(velocityY > 0)
				clientGame.move(BufferPlayerMove.DOWN);
			else
				clientGame.move(BufferPlayerMove.UP);
		}
		return true;
	}

	@Override
	public boolean pan(float x, float y, float deltaX, float deltaY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean panStop(float x, float y, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean zoom(float initialDistance, float distance) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
		// TODO Auto-generated method stub
		return false;
	}

}
