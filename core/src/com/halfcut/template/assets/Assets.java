package com.halfcut.template.assets;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;

/**
 * @author halfcutdev
 * @since 22/12/2017
 *
 * Singleton class for accessing various assets
 */
public class Assets {

    static private Assets instance;
    private AssetManager assets;

    static public Assets get() {
        if(instance == null) {
            instance = new Assets();
        }
        return instance;
    }

    public Texture getTexture(String ref) {
        return assets.get(ref, Texture.class);
    }

    public BitmapFont getFont(String ref) {
        return assets.get(ref, BitmapFont.class);
    }

    public TiledMap getTiledMap(String ref) {
        return assets.get(ref, TiledMap.class);
    }

    public Sound getSFX(String ref) {
        return assets.get(ref, Sound.class);
    }

    public Music getMusic(String ref) {
        return assets.get(ref, Music.class);
    }

    public TextureAtlas getAtlas() {
        return assets.get("packed/textures.atlas");
    }

    public void provide(AssetManager newAssets) {
        assets = newAssets;
    }

}
