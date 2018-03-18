package com.halfcut.template.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;
import com.halfcut.template.App;

import static com.halfcut.template.App.*;

public class DesktopLauncher {

    public static void main (String[] arg) {
	    LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

	    config.title  = TITLE;
	    config.width  = WIDTH  * SCALE;
	    config.height = HEIGHT * SCALE;
	    config.resizable = false;

	    TexturePacker.Settings settings = new TexturePacker.Settings();
	    settings.maxWidth   = 1024;
	    settings.maxHeight  = 1024;
	    settings.useIndexes = false;
	    settings.bleed = true;
	    settings.paddingX = 1;
	    settings.paddingY = 1;
	    TexturePacker.process(settings, "sprites", "packed", "textures");

	    App.mode = Mode.DESKTOP;

	    new LwjglApplication(new App(), config);
    }

}
