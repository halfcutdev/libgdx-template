package com.halfcut.template.screen;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.halfcut.template.App;

import static com.halfcut.template.App.HEIGHT;
import static com.halfcut.template.App.WIDTH;

/**
 * @author halfcutdev
 * @since 22/12/2017
 */
public abstract class Screen implements com.badlogic.gdx.Screen {

    protected App app;
    protected OrthographicCamera camera;
    protected Viewport viewport;

    abstract public void update(float delta);
    abstract public void draw(SpriteBatch sb, ShapeRenderer sr);

    public Screen(App app) {
        this.app = app;

        // Set up and center the camera.
        camera = new OrthographicCamera();
        camera.position.set(WIDTH / 2, HEIGHT / 2, 0);
        viewport = new FitViewport(WIDTH, HEIGHT, camera);
    }

    @Override
    public void render(float delta) {
        update(delta * 60);
        draw(app.sb, app.sr);
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    public App getApp() {
        return app;
    }

    public OrthographicCamera getCamera() {
        return camera;
    }

    public Viewport getViewport() {
        return viewport;
    }

    // Unused
    public void pause() {}
    public void resume() {}
    public void show() {}
    public void hide() {}
    public void dispose() {}

}


