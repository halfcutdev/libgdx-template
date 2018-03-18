package com.halfcut.template.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.HdpiUtils;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.halfcut.template.App;
import sun.plugin.services.WIExplorerBrowserService;

import static com.halfcut.template.App.WIDTH;
import static com.halfcut.template.App.HEIGHT;
import static com.halfcut.template.App.SCALE;

/**
 * @author halfcutdev
 * @since 22/12/2017
 */
public class GameScreen extends Screen {

    // Floating box example.
    private float boxSize  = 10;
    private float boxX     = (WIDTH  - boxSize) / 2;
    private float boxY     = (HEIGHT - boxSize) / 2;
    private float boxTheta = 0.0f;

    public GameScreen(App app) {
        super(app);
    }

    @Override
    public void update(float delta) {
        boxTheta -= 2f * delta;
    }

    @Override
    public void draw(SpriteBatch sb, ShapeRenderer sr) {

        sceneFrameBuffer.begin();
            Gdx.gl.glClearColor(0, 0, 0, 1);
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

            sb.setProjectionMatrix(sceneCamera.combined);
            sr.setProjectionMatrix(sceneCamera.combined);

            // Draw box.
            sr.begin(ShapeRenderer.ShapeType.Filled);
                sr.setColor(Color.GREEN);
                sr.rect(0, 0, WIDTH, HEIGHT);
                sr.setColor(Color.PINK);
                sr.rect(boxX, boxY, boxSize * 0.5f, boxSize * 0.5f, boxSize, boxSize, 1, 1, boxTheta);
            sr.end();
        sceneFrameBuffer.end();

//        HdpiUtils.glViewport(SCALE / 2, SCALE / 2, WIDTH * SCALE, HEIGHT * SCALE);
//        HdpiUtils.glScissor(SCALE / 2, SCALE / 2, WIDTH * SCALE - SCALE, HEIGHT * SCALE - SCALE);

        sb.begin();
            sb.setProjectionMatrix(viewportCamera.combined);
            pixelShader.setUniformf("u_textureSizes", WIDTH, HEIGHT, SCALE, 0.0f);
            pixelShader.setUniformf("u_sampleProperties", subpixelX, subpixelY, upscaleOffsetX, upscaleOffsetY);
            sb.draw(sceneFrameBuffer.getColorBufferTexture(), (SCALE * WIDTH - WIDTH) / 2, (SCALE * HEIGHT - HEIGHT) / 2, WIDTH, HEIGHT,0, 0, 1f, 1f);
        sb.end();

        sb.setShader(null);
        HdpiUtils.glScissor(0, 0, WIDTH * SCALE, HEIGHT * SCALE);
    }

}
