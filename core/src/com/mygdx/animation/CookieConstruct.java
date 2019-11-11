package com.mygdx.animation;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import sun.applet.Main;

public class CookieConstruct extends Game {
    SpriteBatch batch;
    BitmapFont font;

    SpriteBatch batch2;
    BitmapFont font2;
    SpriteBatch batch3;
    BitmapFont font3;
    SpriteBatch batch4;
    BitmapFont font4;
    SpriteBatch batch5;
    BitmapFont font5;

    public void create() {
        batch = new SpriteBatch();
        font = new BitmapFont();
        this.setScreen(new MainMenuScreen(this, 0, 0));
    }

    public void render() {
        super.render(); // important!
    }

    public void dispose() {
        batch.dispose();
        font.dispose();
    }
}
