package com.halfcut.template.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.halfcut.template.App;
import com.halfcut.template.util.Shader;

import static com.halfcut.template.App.HEIGHT;
import static com.halfcut.template.App.WIDTH;

/**
 * @author halfcutdev
 * @since 22/12/2017
 */
public abstract class Screen implements com.badlogic.gdx.Screen {

    protected App app;
    protected Viewport viewport;
    protected FrameBuffer sceneFrameBuffer;
    protected OrthographicCamera sceneCamera;
    protected OrthographicCamera viewportCamera;
    protected ShaderProgram pixelShader = Shader.PIXEL;

    abstract public void update(float delta);
    abstract public void draw(SpriteBatch sb, ShapeRenderer sr);

    public Screen(App app) {
        this.app = app;

        sceneFrameBuffer = new FrameBuffer(Pixmap.Format.RGB888, WIDTH, HEIGHT, false);
        sceneFrameBuffer.getColorBufferTexture().setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);

        sceneCamera = new OrthographicCamera(WIDTH, HEIGHT);
        sceneCamera.position.set(WIDTH / 2, HEIGHT / 2, 0);
        sceneCamera.update();

        viewportCamera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        viewportCamera.position.set(0.5f * viewportCamera.viewportWidth, 0.5f * viewportCamera.viewportHeight, 0);
        viewportCamera.update();

        viewport = new FitViewport(WIDTH, HEIGHT, viewportCamera);
        viewport.apply();
    }

    @Override
    public void render(float delta) {
        draw(app.sb, app.sr);
        update(delta * 60);
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    public App getApp() {
        return app;
    }

    public OrthographicCamera getSceneCamera() {
        return sceneCamera;
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


