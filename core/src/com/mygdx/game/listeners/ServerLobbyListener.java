package com.mygdx.game.listeners;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.mygdx.game.ServerLobby;

public class ServerLobbyListener extends Listener{

	private ServerLobby server;
	
	public ServerLobbyListener(ServerLobby server) {
		this.server = server;
	}
	
	@Override
	public void connected(Connection connection) {
		server.connect();
		super.connected(connection);
	}
	@Override
	public void disconnected(Connection connection) {
		server.disconnect();
		super.disconnected(connection);
	}
	
}
