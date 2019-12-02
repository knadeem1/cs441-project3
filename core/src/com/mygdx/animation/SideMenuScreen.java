package com.mygdx.animation;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
//import com.badlogic.gdx.graphics.OrthographicCamera;
//import com.badlogic.gdx.graphics.Texture;
//import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.net.HttpRequestBuilder;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Json;

public class SideMenuScreen implements Screen {
    private final CookieConstruct game;
    private Stage stage;
    //private OrthographicCamera camera;
    private int highScore1;
    private int highScore2;
    private int highScore3;
    private String p1;
    private String p2;
    private String p3;
    private int max;
    private int max2;
    private int max3;
    private Skin skin;
    private int player;

    public SideMenuScreen(final CookieConstruct gm, int score, int pl) {
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

    @Override
    public void show() {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        skin = new Skin(Gdx.files.internal("uiskin.json"));

        Table table = new Table(skin);
        table.setFillParent(true);

        stage.addActor(table);

    }



    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        //stage.getViewport().update(width, height);
    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
    }
}

