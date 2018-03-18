package com.halfcut.template;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.halfcut.template.screen.LoadingScreen;

public class App extends Game {

    static final public String TITLE = "libGDX Template";
    static final public int WIDTH  = 200;
    static final public int HEIGHT = 150;
    static final public int SCALE  = 2;
    static public boolean DEV_MODE;

    public enum Mode { DESKTOP, HTML }
    static public Mode mode;

    public SpriteBatch   sb;
    public ShapeRenderer sr;

    @Override
    public void create () {
        sb = new SpriteBatch();
        sr = new ShapeRenderer();
        sr.setAutoShapeType(true);
        setScreen(new LoadingScreen(this));
    }

    @Override
    public void render () {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        super.render();
    }

    @Override
    public void dispose () {
        sb.dispose();
        sr.dispose();
    }

}
