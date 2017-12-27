package com.halfcut.template.screen;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.halfcut.template.App;

import static com.halfcut.template.App.HEIGHT;
import static com.halfcut.template.App.WIDTH;

/**
 * @author halfcutdev
 * @since 22/12/2017
 */
public class GameScreen extends Screen {

    // Floating box example.
    private float boxSize  = 20;
    private float boxX     = (WIDTH - 20) / 2;
    private float boxY     = (HEIGHT - 20) / 2;
    private float boxTheta = 0.0f;

    public GameScreen(App app) {
        super(app);
    }

    @Override
    public void update(float delta) {
        // Rotate the box.
        boxTheta -= 1.5f * delta;
    }

    @Override
    public void draw(SpriteBatch sb, ShapeRenderer sr) {
        sb.setProjectionMatrix(camera.combined);
        sr.setProjectionMatrix(camera.combined);

        // Draw box.
        sr.begin(ShapeRenderer.ShapeType.Filled);
            sr.setColor(Color.PINK);
            sr.rect(boxX, boxY, boxSize * 0.5f, boxSize * 0.5f, boxSize, boxSize, 1, 1, boxTheta);
        sr.end();
    }

}
