package com.halfcut.template.screen.transition;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.halfcut.template.App;
import com.halfcut.template.screen.Screen;
import com.halfcut.template.screen.transition.effect.TransitionEffect;
import com.halfcut.template.screen.transition.effect.fade.FadeInEffect;
import com.halfcut.template.screen.transition.effect.fade.FadeOutEffect;
import com.halfcut.template.screen.transition.effect.swipe.SwipeInEffect;
import com.halfcut.template.screen.transition.effect.swipe.SwipeOutEffect;

/**
 * @author halfcutdev
 * @since 10/09/2017
 */
public class TransitionScreen extends Screen {

    private Screen prevScreen;
    private Screen nextScreen;
    private TransitionEffect outEffect;
    private TransitionEffect inEffect;

    private enum TransitionState { IN, OUT }
    private TransitionState currentState;

    public TransitionScreen(App app, Screen prevScreen, Screen nextScreen) {
        super(app);
        this.prevScreen = prevScreen;
        this.nextScreen = nextScreen;
        this.outEffect = new SwipeOutEffect(50000);
        this.inEffect  = new SwipeInEffect(50000);
        this.currentState = TransitionState.OUT;
    }

    @Override
    public void update(float delta) {
        switch(currentState) {
            case OUT:
                updateOutTransition(delta);
                break;
            case IN:
                updateInTransition(delta);
                break;
        }
    }

    @Override
    public void draw(SpriteBatch sb, ShapeRenderer sr) {
        switch(currentState) {
            case OUT:
                drawOutTransition(sb, sr);
                break;
            case IN:
                drawInTransition(sb, sr);
                break;
        }
    }


    // Out transition

    private void updateOutTransition(float delta) {
        prevScreen.update(delta);
        outEffect.update(delta);
        if(outEffect.isFinished()) {
            prevScreen.hide();
            currentState = TransitionState.IN;
            nextScreen.init();
            nextScreen.resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            nextScreen.show();
        }
    }

    private void drawOutTransition(SpriteBatch sb, ShapeRenderer sr) {
        prevScreen.draw(sb, sr);
        outEffect.draw(sb, sr);
    }


    // In transition

    private void updateInTransition(float delta) {
        nextScreen.update(delta);
        inEffect.update(delta);

        if(inEffect.isFinished()) {
            System.out.println(app);
            System.out.println(nextScreen);
            app.setScreen(nextScreen);
        }
    }

    private void drawInTransition(SpriteBatch sb, ShapeRenderer sr) {
        nextScreen.draw(sb, sr);
        inEffect.draw(sb, sr);

        if(inEffect.isFinished()) {
            app.setScreen(nextScreen);
        }
    }

    // Unused
    public void resize(int width, int height) {}
    public void pause() {}
    public void resume() {}
    public void show() {}
    public void hide() {}
    public void dispose() {}

}
