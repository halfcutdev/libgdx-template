package com.halfcut.template.screen;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.halfcut.template.App;

import static com.halfcut.template.App.WIDTH;
import static com.halfcut.template.App.HEIGHT;

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
        sb.setProjectionMatrix(getSceneCamera().combined);
        sr.setProjectionMatrix(getSceneCamera().combined);

        // Draw box.
        sr.begin(ShapeRenderer.ShapeType.Filled);
            sr.setColor(Color.PINK);
            sr.rect(boxX, boxY, boxSize * 0.5f, boxSize * 0.5f, boxSize, boxSize, 1, 1, boxTheta);
        sr.end();
    }

}
