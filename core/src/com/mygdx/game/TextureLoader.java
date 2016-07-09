package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

public class TextureLoader {

	public static Texture barrel, shootingP1, playerP1, shootingP2, playerP2, normalShotP1, normalShotP2,
						cannonIcon, wideBladeIcon, healingIcon, longBladeIcon, wideBladeAreaBot, wideBladeAreaTop, wideBladeAreaMid,
						chargeCover, gameBackground;
	public static Drawable shootingDrawableP1, playerDrawableP1, shootingDrawableP2, playerDrawableP2, 
						wideBladeP21, wideBladeP22, wideBladeP23, wideBladeP24,wideBladeP11, wideBladeP12, wideBladeP13, wideBladeP14,
						longBlade;
	
	public TextureLoader() {
		barrel = new Texture(Gdx.files.internal("barrel.png"));
		
		cannonIcon = new Texture(Gdx.files.internal("cannon.png"));
		wideBladeIcon = new Texture(Gdx.files.internal("wideBladeIcon.png"));
		healingIcon = new Texture(Gdx.files.internal("healingIcon.png"));
		longBladeIcon = new Texture(Gdx.files.internal("longBladeIcon.png"));
		
		wideBladeAreaMid = new Texture(Gdx.files.internal("wideBladeAreaMid.png"));
		wideBladeAreaBot = new Texture(Gdx.files.internal("wideBladeAreaBot.png"));
		wideBladeAreaTop = new Texture(Gdx.files.internal("wideBladeAreaTop.png"));
		
		normalShotP1 = new Texture(Gdx.files.internal("normalShotP1.png"));
		normalShotP2 = new Texture(Gdx.files.internal("normalShotP2.png"));
		
		playerP1 = new Texture(Gdx.files.internal("player.png"));
		playerP2 = new Texture(Gdx.files.internal("player2.png"));
		
		shootingP1 = new Texture(Gdx.files.internal("playerShooting.png"));
		shootingP2 = new Texture(Gdx.files.internal("player2Shooting.png"));
		
		chargeCover = new Texture(Gdx.files.internal("chargeCover.png"));
		
		gameBackground = new Texture(Gdx.files.internal("gameBackground.png"));
		
		shootingDrawableP1 = new SpriteDrawable(new Sprite(shootingP1));
		shootingDrawableP2 = new SpriteDrawable(new Sprite(shootingP2));
		
		playerDrawableP1 = new SpriteDrawable(new Sprite(playerP1));
		playerDrawableP2 = new SpriteDrawable(new Sprite(playerP2));
		
		wideBladeP21 = new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal("wideBladeP21.png"))));
		wideBladeP22 = new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal("wideBladeP22.png"))));
		wideBladeP23 = new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal("wideBladeP23.png"))));
		wideBladeP24 = new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal("wideBladeP24.png"))));

		wideBladeP11 = new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal("wideBladeP11.png"))));
		wideBladeP12 = new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal("wideBladeP12.png"))));
		wideBladeP13 = new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal("wideBladeP13.png"))));
		wideBladeP14 = new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal("wideBladeP14.png"))));
		
		longBlade = new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal("longBlade.png"))));
	}
	
	public static Texture getWideBladeArea(int x)
	{
		switch(x)
		{
		case 0:
			return wideBladeAreaBot;
		case 1:
			return wideBladeAreaMid;
		case 2:
			return wideBladeAreaTop;
		default:
			return null;
		}
	}

	public static Texture getIcon(int randy) {
		switch(randy)
		{
		case Chip.BUSTER_SHOT:
			return cannonIcon;
		case Chip.WIDE_BLADE:
			return wideBladeIcon;
		case Chip.HEALING:
			return healingIcon;
		case Chip.LONG_BLADE:
			return longBladeIcon;
		default:
			return null;
		}
	}
	
}
