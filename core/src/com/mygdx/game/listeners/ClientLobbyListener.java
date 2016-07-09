package com.mygdx.game.listeners;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.mygdx.game.ClientLobby;
import com.mygdx.game.buffers.BufferPlayerConnect;

public class ClientLobbyListener extends Listener{

	private ClientLobby clientLobby;
	
	public ClientLobbyListener(ClientLobby cl) {
		this.clientLobby = cl;
	}
	
	@Override
	public void received(Connection connection, Object object) {
		if(object instanceof BufferPlayerConnect)
		{
			BufferPlayerConnect bpc = (BufferPlayerConnect) object;
			int x = bpc.player;
			clientLobby.StartGame(x);
		}
		super.received(connection, object);
	}
	
	@Override
	public void connected(Connection connection) {
		super.connected(connection);
	}
	
	@Override
	public void disconnected(Connection connection) {
		clientLobby.Disconnect();
		super.disconnected(connection);
	}
	
}
