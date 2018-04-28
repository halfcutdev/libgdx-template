package com.halfcut.template.screen.transition.effect.swipe;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.halfcut.template.App;
import com.halfcut.template.screen.transition.effect.TransitionEffect;
import com.halfcut.template.util.Timer;

/**
 * @author halfcutdev
 * @since 10/09/2017
 */
public class SwipeOutEffect extends TransitionEffect {

    private Timer timer;

    public SwipeOutEffect(int duration) {
        this.timer = new Timer(duration, true);
    }

    @Override
    public void update(float delta) {
        if(timer.tick(delta)) {
            finished = true;
            timer.setElapsed(timer.getDelay());
        }
    }

    @Override
    public void draw(SpriteBatch sb, ShapeRenderer sr) {
        float percent = timer.percent();
        float triangle = 90;
        float x = (percent * (App.WIDTH + triangle)) - triangle;
        float width  = percent * (App.WIDTH + triangle);
        float height = App.HEIGHT;

        sr.begin(ShapeRenderer.ShapeType.Filled);
        sr.setColor(Color.BLACK);
        sr.rect(-triangle, 0, width, height);
        sr.triangle(
            x, 0,
            x + triangle, 0,
            x, height
        );
        sr.end();
    }

}
