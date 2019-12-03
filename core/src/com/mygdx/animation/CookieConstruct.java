package com.mygdx.animation;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class CookieConstruct extends Game {
    SpriteBatch batch;
    BitmapFont font;

    public void create() {
        batch = new SpriteBatch();
        font = new BitmapFont();
        this.setScreen(new MainMenuScreen(this, 0, ""));
    }

    public void render() {
        super.render();
    }

    public void dispose() {
        batch.dispose();
        font.dispose();
    }
}

/*
    public MainMenuScreen(final CookieConstruct gm, int score, int pl) {
        game = gm;
        player = pl;
        Preferences prefs = Gdx.app.getPreferences("game preferences");

        if(player == 0){
            highScore1 = prefs.getInteger("highScore1");
            highScore2 = prefs.getInteger("highScore2");
            highScore3 = prefs.getInteger("highScore3");
        }

        if(player == 1){
            if (score > highScore1) {
                prefs.putInteger("highScore1", score);
                prefs.flush();
            }
            highScore1 = prefs.getInteger("highScore1");
            highScore2 = prefs.getInteger("highScore2");
            highScore3 = prefs.getInteger("highScore3");
        }

        if(player == 2){
            if (score > highScore2) {
                prefs.putInteger("highScore2", score);
                prefs.flush();
            }
            highScore1 = prefs.getInteger("highScore1");
            highScore2 = prefs.getInteger("highScore2");
            highScore3 = prefs.getInteger("highScore3");
        }
        if(player == 3){
            if (score > highScore3) {
                prefs.putInteger("highScore3", score);
                prefs.flush();
            }
            highScore1 = prefs.getInteger("highScore1");
            highScore2 = prefs.getInteger("highScore2");
            highScore3 = prefs.getInteger("highScore3");
        }

        p1 = "";
        p2 = "";
        p3 = "";

        max = 0;
        max2 = 0;
        max3 = 0;

        if(highScore1>=highScore3&&highScore1>=highScore2){
            max = highScore1;
            p1 = "Player1";
        }
        if(highScore2>=highScore3&&highScore2>=highScore1){
            max = highScore2;
            p1 = "Player2";
        }
        if(highScore3>=highScore1&&highScore3>=highScore2){
            max = highScore3;
            p1 = "Player3";
        }
        if(max==highScore1){
            if(highScore2>=highScore3){
                max2 = highScore2;
                p2 = "Player2";
            }
            else if(highScore3>highScore2){
                max2 = highScore3;
                p2 = "Player3";
            }
        }
        if(max==highScore2){
            if(highScore1>=highScore3){
                max2 = highScore1;
                p2 = "Player1";
            }
            else if(highScore3>highScore1){
                max2 = highScore3;
                p2 = "Player3";
            }
        }
        if(max==highScore3){
            if(highScore2>=highScore1){
                max2 = highScore2;
                p2 = "Player2";
            }
            else if(highScore1>highScore2){
                max2 = highScore1;
                p2 = "Player1";
            }
        }
        if((max==highScore1 && max2==highScore2) || (max==highScore2 && max2 == highScore1)){
            max3 = highScore3;
            p3 = "Player3";
        }
        if((max==highScore1 && max2==highScore3) || (max==highScore3 && max2 == highScore1)){
            max3 = highScore2;
            p3 = "Player2";
        }
        if((max==highScore3 && max2==highScore2) || (max==highScore2 && max2 == highScore3)){
            max3 = highScore1;
            p3 = "Player1";
        }

        show();
    }

    */