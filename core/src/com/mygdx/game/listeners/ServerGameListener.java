package com.mygdx.game.listeners;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.mygdx.game.ServerGame;
import com.mygdx.game.buffers.BufferBusterShot;
import com.mygdx.game.buffers.BufferFinishSelection;
import com.mygdx.game.buffers.BufferPlayerMove;
import com.mygdx.game.buffers.BufferUseChip;

public class ServerGameListener extends Listener{
	
	private ServerGame server;
	
	public ServerGameListener(ServerGame server) {	
		this.server = server;
	}
	
	@Override
	public void received(Connection connection, Object object) {
		if(object instanceof BufferPlayerMove)
		{
			BufferPlayerMove buffer = (BufferPlayerMove) object;
			server.move(buffer.msg, buffer.player);
		}else if(object instanceof BufferFinishSelection)
		{
			server.finishSelection((BufferFinishSelection)object);
		}
		else if( object instanceof BufferBusterShot)
		{
			BufferBusterShot buffer = (BufferBusterShot) object;
			server.shoot(buffer.player1);
		}else if(object instanceof BufferUseChip)
		{
			BufferUseChip buffer = (BufferUseChip) object;
			server.useChip(buffer.playerOne);
		}
		super.received(connection, object);
	}
	
	@Override
	public void disconnected(Connection connection) {
		server.disconnect();
		super.disconnected(connection);
	}
	
}
