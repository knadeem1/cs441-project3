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
    private String player;
    private int score;

    public SideMenuScreen(final CookieConstruct gm, int sc, String pl) {
        game = gm;
        player = pl;
        score = sc;

        show();
    }

    @Override
    public void show() {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        //stage.setDebugAll(true);
        skin = new Skin(Gdx.files.internal("uiskin.json"));

        Table table = new Table(skin);
        table.setFillParent(true);

        //stage.addActor(table);

        Label topLabel = new Label("ENTER NAME:", skin);
        topLabel.setAlignment(Align.center);
        topLabel.setFontScale(6);
        topLabel.setColor(Color.GOLDENROD);

        final TextField nameText = new TextField("",skin);
        nameText.setAlignment(Align.center);
        player = nameText.getText();
        nameText.setColor(Color.GOLDENROD);

        TextButton homeButton = new TextButton("HOME",skin);
        homeButton.getLabel().setFontScale(4f);
        homeButton.getLabel().setAlignment(Align.center);
        homeButton.setColor(Color.GOLDENROD);

        table.row().expandY();
        table.add(topLabel);

        table.row();
        table.add(nameText).size(400,130).fill();

        table.row();
        table.add(homeButton).size(250,150).expandY();

        stage.addActor(table);

        homeButton.addListener( new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                player = nameText.getText();
                game.setScreen(new MainMenuScreen(game,score,player));
                dispose();
            }
        });

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

