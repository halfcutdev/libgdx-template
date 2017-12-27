package com.halfcut.template.client;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import com.badlogic.gdx.backends.gwt.preloader.Preloader;
import com.badlogic.gdx.graphics.Pixmap;
import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Window;
import com.halfcut.template.App;

import static com.halfcut.template.App.WIDTH;
import static com.halfcut.template.App.HEIGHT;
import static com.halfcut.template.App.SCALE;
import static com.halfcut.template.screen.LoadingScreen.*;

public class HtmlLauncher extends GwtApplication {

    private GwtApplicationConfiguration cfg;

    @Override
    public GwtApplicationConfiguration getConfig () {
        cfg = new GwtApplicationConfiguration(WIDTH * SCALE, App.HEIGHT * SCALE);
        cfg.preferFlash = false;
        Window.enableScrolling(false);
        Window.setMargin("0");

        App.mode = App.Mode.HTML;
        return cfg;
    }

    @Override
    public ApplicationListener createApplicationListener () {
        return new App();
    }

    @Override
    public Preloader.PreloaderCallback getPreloaderCallback() {
        final Canvas canvas = Canvas.createIfSupported();
        canvas.setWidth( "" + cfg.width  + "px");
        canvas.setHeight("" + cfg.height + "px");
        canvas.setCoordinateSpaceWidth(cfg.width);
        canvas.setCoordinateSpaceHeight(cfg.height);
        getRootPanel().add(canvas);

        final Context2d context = canvas.getContext2d();

        return new Preloader.PreloaderCallback() {

            @Override
            public void update (Preloader.PreloaderState state) {
                float x, y, width, height;
                String white      = Pixmap.make(255, 255, 255, 1);
                String background = Pixmap.make((int) (BACKGROUND_COLOUR.r * 255), (int) (BACKGROUND_COLOUR.g * 255), (int) (BACKGROUND_COLOUR.b * 255), 1);

                // Background
                context.setFillStyle(background);
                context.fillRect(0, 0, WIDTH * SCALE, HEIGHT * SCALE);

                // Outer
                width  = (BAR_WIDTH  + (BORDER_THICKNESS + BORDER_GAP) * 2);
                height = (BAR_HEIGHT + (BORDER_THICKNESS + BORDER_GAP) * 2);
                x = ((WIDTH  - width)  / 2);
                y = ((App.HEIGHT - height) / 2);
                context.setFillStyle(white);
                context.fillRect(x * SCALE, y * SCALE, width * SCALE, height * SCALE);

                // Inner
                width  = (BAR_WIDTH  + (BORDER_GAP) * 2);
                height = (BAR_HEIGHT + (BORDER_GAP) * 2);
                x = ((WIDTH  - width)  / 2);
                y = ((App.HEIGHT - height) / 2);
                context.setFillStyle(background);
                context.fillRect(x * SCALE, y * SCALE, width * SCALE, height * SCALE);

                // Bar
                width  = BAR_WIDTH ;
                height = BAR_HEIGHT;
                x = ((WIDTH - width)  / 2);
                y = ((App.HEIGHT- height) / 2);
                context.setFillStyle(white);
                width = width * state.getProgress() * 0.5f;
                context.fillRect(x * SCALE, y * SCALE, width * SCALE, height * SCALE);
            }

            @Override
            public void error (String file) {
                System.out.println("error: " + file);
            }

        };

    }

    class ResizeListener implements ResizeHandler {

        @Override
            public void onResize(ResizeEvent event) {
            int width = event.getWidth();
            int height = event.getHeight();

            getApplicationListener().resize(width, height);
            Gdx.graphics.setWindowedMode(width, height);
            Gdx.gl.glViewport(0, 0, width, height);

            Window.scrollTo((cfg.width - width) / 2, (cfg.height - height) / 2);
        }

    }

}