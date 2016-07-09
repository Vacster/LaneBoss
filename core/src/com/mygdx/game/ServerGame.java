package com.mygdx.game;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Queue;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Server;
import com.mygdx.game.buffers.BufferEndGame;
import com.mygdx.game.buffers.BufferFinishSelection;
import com.mygdx.game.buffers.BufferPlayerMove;
import com.mygdx.game.buffers.BufferSelectMenu;
import com.mygdx.game.listeners.ServerGameListener;

public class ServerGame extends Stage {

	private Image chargeBar, background, chargeCover;
	private Label hp1, hp2, waiting;
	private Server server;
	private MainMenu main;
	private Skin skin;
	private Player player1, player2;
	private Connection p1, p2;
	private ArrayList<Image> attacks;
	private Queue<Integer> chipQueueP1, chipQueueP2;
	private float charge = 0;
	private int player1Hp = 100, player2Hp = 100, selections = 0;
	private boolean selecting = false, p1cooldown = false, p2cooldown = false;
	private Timer timer;
	
	public ServerGame(final MainMenu main, Skin skin, Viewport viewport, Server server) {
		super(viewport);
		this.main = main;
		this.skin = skin;
		this.server = server;
		
		background = new Image(TextureLoader.gameBackground);
		background.setBounds(0, 0, 1360.0f, 720.0f);
		addActor(background);
		
		chargeBar = new Image(new Texture(Gdx.files.internal("hp.png")));
		chargeBar.setBounds(280.0f, 645.0f, charge, 50.0f);
		addActor(chargeBar);
		
		chargeCover = new Image(TextureLoader.chargeCover);
		chargeCover.setBounds(180.0f, 635.0f, 1038.0f, 75.0f);
		addActor(chargeCover);
		
		background = new Image(new Texture(Gdx.files.internal("Stage.png")));
		background.setBounds(0, 45.0f, 1360.0f, 350.0f);
		addActor(background);
		
		hp1 = new Label(Integer.toString(player1Hp), skin, "small");
		hp1.setPosition(25.0f, 638.0f);
		addActor(hp1);
		
		hp2 = new Label(Integer.toString(player2Hp), skin, "small");
		hp2.setPosition(1225.0f, 638.0f);
		addActor(hp2);
		
		waiting = new Label("WAITING", skin, "small");
		waiting.setPosition(505.0f,555.0f);
		
		player1 = new Player(1);
		player2 = new Player(2);
		addActor(player1);
		addActor(player2);

		p1 = server.getConnections()[0];
		p2 = server.getConnections()[1];

		this.server.addListener(new ServerGameListener(this));
		
		attacks = new ArrayList<Image>();
		chipQueueP1 = new Queue<Integer>();	
		chipQueueP2 = new Queue<Integer>();
		timer = new Timer();
	}
	
	@Override
	public void act() {
		if(!selecting)
			chargeBar.setWidth(charge += 1.5f);
		if(charge >= 800.0f)
		{
			chipQueueP1.clear();
			chipQueueP2.clear();
			addActor(waiting);
			charge = 0;
			selecting = true;
			for(Image im : attacks)
				im.remove();
			BufferSelectMenu buffer = new BufferSelectMenu();
			p1.sendTCP(buffer);
			p2.sendTCP(buffer);
		}
		hp1.setText(Integer.toString(player1Hp));
		hp2.setText(Integer.toString(player2Hp));
		super.act();
	}
	
	public void disconnect(){
		for(Connection con : server.getConnections())
		{
			BufferEndGame buffer = new BufferEndGame();
			con.sendTCP(buffer);
		}
		Gdx.app.postRunnable(new Runnable() {
			public void run() {
				main.changeStage(new MainMenuStage(main, skin));
			}
		});
	}

	public void finishSelection(BufferFinishSelection chips)
	{
		selections++;
		addChips(chips.playerOne, chips.chips);
		if(selections >= 2)
		{
			selecting = false;
			selections = 0;
			waiting.remove();
			p1.sendTCP(chips);
			p2.sendTCP(chips);
		}
	}

	private void addChips(boolean playerOne, int chip[])
	{
		if(playerOne)
			for(int x = 0; x < chip.length;	x++)
				chipQueueP1.addFirst(chip[x]);
		else
			for(int x = 0; x < chip.length;	x++)
				chipQueueP2.addFirst(chip[x]);
	}

	public void useChip(boolean playerOne)
	{
		if(playerOne)
		{
			if(chipQueueP1.size > 0 && !p1cooldown)
				useAttack(playerOne, chipQueueP1.removeFirst());
		}	
		else
		{
			if(chipQueueP2.size > 0 && !p2cooldown)
				useAttack(playerOne, chipQueueP2.removeFirst());
		}
	}

	public void useAttack(final boolean playerOne, int x)
	{
		switch(x)
		{
		case Chip.BUSTER_SHOT:
				ChipBusterShot shot = new ChipBusterShot(playerOne, playerOne?player1.getPosX():
					player2.getPosX(), playerOne?player1.getPosY():player2.getPosY(), playerOne?player2:player1, this);
				attacks.add(shot);
				addActor(shot);
				break;
		case Chip.WIDE_BLADE:
			ChipWideSword sword = new ChipWideSword(playerOne, playerOne?player1.getPosX():
				player2.getPosX(), playerOne?player1.getPosY():player2.getPosY(), playerOne?player2:player1, this);
			attacks.add(sword);
			addActor(sword);
			break;
		case Chip.LONG_BLADE:
			ChipLongSword longSword = new ChipLongSword(playerOne, playerOne?player1.getPosX():
				player2.getPosX(), playerOne?player1.getPosY():player2.getPosY(), playerOne?player2:player1, this);
			attacks.add(longSword);
			addActor(longSword);
			break;
		case Chip.HEALING:
			if(playerOne)
				player1Hp+=10;
			else
				player2Hp+=10;
		}
		if(playerOne)
		{
			p1cooldown = true;
			player1.shootAnimation();
		}
		else
		{	
			p2cooldown = true;
			player2.shootAnimation();
		}

		timer.scheduleTask(new Task(){
			@Override
			public void run() {
				if(playerOne)
					p1cooldown = false;
				else
					p2cooldown = false;
			}
		}, 0.8f);
	}

	public void move(int msg, int player) {
		switch(msg)
		{
		case BufferPlayerMove.DOWN:
			if (player == 1)
				player1.moveDown();
			else
				player2.moveDown();
			break;
		case BufferPlayerMove.UP:
			if (player == 1)
				player1.moveUp();
			else
				player2.moveUp();
			break;
		case BufferPlayerMove.RIGHT:
			if (player == 1)
				player1.moveRight();
			else
				player2.moveRight();
			break;
		case BufferPlayerMove.LEFT:
			if (player == 1)
				player1.moveLeft();
			else
				player2.moveLeft();
			break;

		}
	}

	public void getHit(boolean playerOne, int amount)
	{
		if(playerOne)
			player2Hp-=amount;
		else
			player1Hp-=amount;
		if(player1Hp <= 0 || player2Hp <= 0)
			disconnect();
	}
	
	public void shoot(boolean playerOne) {
		if(player1.getY() == player2.getY())
			getHit(playerOne, 2);
		if(playerOne)
			player1.shootAnimation();
		else
			player2.shootAnimation();
	}
	
	
}
