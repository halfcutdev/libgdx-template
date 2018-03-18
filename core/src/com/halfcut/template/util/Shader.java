package com.halfcut.template.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;

/**
 * @author halfcutdev
 */
public class Shader {

    static final public ShaderProgram DEFAULT;
    static final public ShaderProgram PIXEL;

    static {
        ShaderProgram.pedantic = false;
        DEFAULT = SpriteBatch.createDefaultShader();
        PIXEL = new ShaderProgram(
                Gdx.files.internal("shaders/pixel.vert").readString(),
                Gdx.files.internal("shaders/pixel.frag").readString()
        );
    }

}
