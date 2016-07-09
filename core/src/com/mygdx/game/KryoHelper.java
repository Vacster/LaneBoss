package com.mygdx.game;

import com.esotericsoftware.kryo.Kryo;
import com.mygdx.game.buffers.BufferBusterShot;
import com.mygdx.game.buffers.BufferEndGame;
import com.mygdx.game.buffers.BufferFinishSelection;
import com.mygdx.game.buffers.BufferPlayerConnect;
import com.mygdx.game.buffers.BufferPlayerMove;
import com.mygdx.game.buffers.BufferSelectMenu;
import com.mygdx.game.buffers.BufferUseChip;

public class KryoHelper {
	
	public static void registerClasses(Kryo kryo)
	{
		kryo.register(BufferEndGame.class);
		kryo.register(BufferPlayerConnect.class);
		kryo.register(BufferPlayerMove.class);
		kryo.register(BufferSelectMenu.class);
		kryo.register(BufferFinishSelection.class);
		kryo.register(BufferBusterShot.class);
		kryo.register(BufferUseChip.class);
		kryo.register(int[].class);
	}
}
