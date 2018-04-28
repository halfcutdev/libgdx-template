package com.halfcut.template.screen.transition.effect.fade;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.halfcut.template.App;
import com.halfcut.template.screen.transition.effect.TransitionEffect;
import com.halfcut.template.util.Timer;

/**
 * @author halfcutdev
 * @since 10/09/2017
 */
public class FadeOutEffect extends TransitionEffect {

    private Color colour = Color.BLACK;
    private Timer timer;

    public FadeOutEffect(int duration) {
        this.timer = new Timer(duration, true);
    }

    @Override
    public void update(float delta) {
        if(timer.tick(delta)) {
            finished = true;
        }
    }

    @Override
    public void draw(SpriteBatch sb, ShapeRenderer sr) {
        colour.a = (finished) ? 1 : timer.getElapsed() / (float) timer.getDelay();

        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        sr.begin(ShapeRenderer.ShapeType.Filled);
            sr.setColor(colour);
            sr.rect(0, 0, App.WIDTH, App.HEIGHT);
        sr.end();
    }

}
