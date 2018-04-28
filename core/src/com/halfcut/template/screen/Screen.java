package com.halfcut.template.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.graphics.glutils.HdpiUtils;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.halfcut.template.App;
import com.halfcut.template.screen.transition.TransitionScreen;
import com.halfcut.template.util.Palette;
import com.halfcut.template.util.Shader;

import static com.halfcut.template.App.HEIGHT;
import static com.halfcut.template.App.SCALE;
import static com.halfcut.template.App.WIDTH;

/**
 * @author halfcutdev
 * @since 22/12/2017
 */
public abstract class Screen implements com.badlogic.gdx.Screen {

    protected App app;
    private Viewport viewport;
    private FrameBuffer sceneFrameBuffer;
    private OrthographicCamera sceneCamera;
    private OrthographicCamera viewportCamera;
    private ShaderProgram pixelShader = Shader.PIXEL;

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
        update(delta * 60);

        sceneFrameBuffer.begin();
            Gdx.gl.glClearColor(Palette.INK.r, Palette.INK.g, Palette.INK.b, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            Gdx.gl.glEnable(GL20.GL_BLEND);
            Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

            float sceneX = sceneCamera.position.x;
            float sceneY = sceneCamera.position.y;
            float sceneXI = MathUtils.floor(sceneX);
            float sceneYI = MathUtils.floor(sceneY);
            float upscaleOffsetX = (sceneX - sceneXI) * SCALE;
            float upscaleOffsetY = (sceneY - sceneYI) * SCALE;
            float subpixelX = upscaleOffsetX - MathUtils.floor(upscaleOffsetX);
            float subpixelY = upscaleOffsetY - MathUtils.floor(upscaleOffsetY);
            upscaleOffsetX -= subpixelX;
            upscaleOffsetY -= subpixelY;

            sceneCamera.position.set(sceneXI, sceneYI, 0.0f);
            sceneCamera.update();
            draw(app.sb, app.sr);
        sceneFrameBuffer.end();

        app.sb.begin();
            app.sb.setProjectionMatrix(viewportCamera.combined);
            pixelShader.setUniformf("u_textureSizes", WIDTH, HEIGHT, SCALE, 0.0f);
            pixelShader.setUniformf("u_sampleProperties", subpixelX, subpixelY, upscaleOffsetX, upscaleOffsetY);
            app.sb.draw(sceneFrameBuffer.getColorBufferTexture(), (SCALE * WIDTH - WIDTH) / 2, (SCALE * HEIGHT - HEIGHT) / 2, WIDTH, HEIGHT,0, 0, 1f, 1f);
        app.sb.end();

        app.sb.setShader(null);
        HdpiUtils.glScissor(0, 0, WIDTH * SCALE, HEIGHT * SCALE);
    }

    public void transitionToScreen(Screen next) {
        app.setScreen(new TransitionScreen(app, this, next));
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
    public void init()    {}
    public void pause()   {}
    public void resume()  {}
    public void show()    {}
    public void hide()    {}
    public void dispose() {}

}


