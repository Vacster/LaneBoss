package com.mygdx.game;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import android.content.pm.ActivityInfo;

public class Ori {
	
	static AndroidApplication ap;
	
	public Ori(AndroidApplication ap) {
		this.ap = ap;
	}
	
	public static void change(boolean portrait, Stage stage)
	{
		ap.setRequestedOrientation(portrait?ActivityInfo.SCREEN_ORIENTATION_PORTRAIT:ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

		if(portrait)
		{
			stage.setViewport(new ExtendViewport(720, 1360));
			stage.getViewport().update(720, 1360, true);
		}else{
			stage.setViewport(new ExtendViewport(1360, 720));
			stage.getViewport().update(1360, 720, true);
		}
	}
	
}

