package com.halfcut.template.screen.transition.effect;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * @author halfcutdev
 * @since 10/09/2017
 */
public abstract class TransitionEffect {

    protected boolean finished;

    abstract public void update(float delta);
    abstract public void draw(SpriteBatch sb, ShapeRenderer sr);

    public boolean isFinished() {
        return this.finished;
    }

}
