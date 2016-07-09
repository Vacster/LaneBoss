package com.mygdx.game.listeners;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.mygdx.game.ClientGame;
import com.mygdx.game.buffers.BufferEndGame;
import com.mygdx.game.buffers.BufferFinishSelection;
import com.mygdx.game.buffers.BufferSelectMenu;

public class ClientGameListener extends Listener{

	private ClientGame client;
	
	public ClientGameListener(ClientGame client) {
		this.client = client;
	}
	
	@Override
	public void received(Connection connection, Object object) {
		if(object instanceof BufferEndGame)
			client.disconnect();
		else if(object instanceof BufferSelectMenu)
			client.selectStage();
		else if(object instanceof BufferFinishSelection)
			client.finishSelection();
		super.received(connection, object);
	}
	
	@Override
	public void disconnected(Connection connection) {
		client.disconnect();
		super.disconnected(connection);
	}
	
}
