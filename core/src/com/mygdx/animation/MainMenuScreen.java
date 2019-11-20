package com.mygdx.animation;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;

public class MainMenuScreen implements Screen {
    private final CookieConstruct game;
    private Stage stage;
    private OrthographicCamera camera;
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

    @Override
    public void show() {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        //stage.setDebugAll(true);
        skin = new Skin(Gdx.files.internal("uiskin.json"));
        /*tableContainer = new Container<>();

        float sw = Gdx.graphics.getWidth();
        float sh = Gdx.graphics.getHeight();

        float cw = sw * 0.7f;
        float ch = sh * 0.5f;

        tableContainer.setSize(sw, sh);
        tableContainer.setPosition((sw) / 2.0f, (sh) / 2.0f);
        tableContainer.fillX();
        */


        Table table = new Table(skin);
        table.setFillParent(true);

        stage.addActor(table);
        //table.setSize(800,480);
        //table.setPosition(400,200);
        //stage.addActor(table);

        Label topLabel = new Label("COOKIE MONSTER", skin);
        topLabel.setAlignment(Align.center);
        topLabel.setFontScale(7);
        topLabel.setColor(Color.BLUE);

        Label nameLabel = new Label("Name:", skin);
        nameLabel.setFontScale(4);

        TextField nameText = new TextField("",skin);


        TextButton p1Button = new TextButton("Player1",skin);
        p1Button.getLabel().setFontScale(4.5f);
        p1Button.setColor(Color.YELLOW);
        TextButton p2Button = new TextButton("Player2",skin);
        p2Button.getLabel().setFontScale(4.5f);
        p2Button.setColor(Color.YELLOW);
        TextButton p3Button = new TextButton("Player3",skin);
        p3Button.getLabel().setFontScale(4.5f);
        p3Button.setColor(Color.YELLOW);

        Label scoreLabel = new Label("LEADERBOARD", skin);
        scoreLabel.setAlignment(Align.center);
        scoreLabel.setFontScale(4.5f);
        scoreLabel.setColor(Color.ROYAL);

        Label rankLabel = new Label("Rank", skin);
        rankLabel.setAlignment(Align.center);
        rankLabel.setFontScale(3f);
        rankLabel.setColor(Color.GOLDENROD);

        Label playerLabel = new Label("Name", skin);
        playerLabel.setAlignment(Align.center);
        playerLabel.setFontScale(3f);
        playerLabel.setColor(Color.GOLDENROD);

        Label dataLabel = new Label("Points", skin);
        dataLabel.setAlignment(Align.center);
        dataLabel.setFontScale(3f);
        dataLabel.setColor(Color.GOLDENROD);

        Label rank1 = new Label("1", skin);
        rank1.setAlignment(Align.center);
        rank1.setFontScale(2.5f);
        rank1.setColor(Color.GOLD);

        Label rank2 = new Label("2", skin);
        rank2.setAlignment(Align.center);
        rank2.setFontScale(2.5f);
        rank2.setColor(Color.GOLD);

        Label rank3 = new Label("3", skin);
        rank3.setAlignment(Align.center);
        rank3.setFontScale(2.5f);
        rank3.setColor(Color.GOLD);

        //have to get the name from name field
        Label player1 = new Label(p1, skin);
        player1.setAlignment(Align.center);
        player1.setFontScale(2.5f);
        player1.setColor(Color.GOLD);

        Label player2 = new Label(p2, skin);
        player2.setAlignment(Align.center);
        player2.setFontScale(2.5f);
        player2.setColor(Color.GOLD);

        Label player3 = new Label(p3, skin);
        player3.setAlignment(Align.center);
        player3.setFontScale(2.5f);
        player3.setColor(Color.GOLD);

        Label score1 = new Label(String.valueOf(max), skin);
        score1.setAlignment(Align.center);
        score1.setFontScale(2.5f);
        score1.setColor(Color.GOLD);

        Label score2 = new Label(String.valueOf(max2), skin);
        score2.setAlignment(Align.center);
        score2.setFontScale(2.5f);
        score2.setColor(Color.GOLD);

        Label score3 = new Label(String.valueOf(max3), skin);
        score3.setAlignment(Align.center);
        score3.setFontScale(2.5f);
        score3.setColor(Color.GOLD);

        table.row().colspan(3).expandX().fillX();
        table.add(topLabel).expandY();

        table.row();
        table.add(p1Button).size(350,150).uniform();
        table.add(p2Button).size(350,150).uniform();
        table.add(p3Button).size(350,150).uniform();

        table.row().colspan(3).fillX();
        table.add(scoreLabel).expandY().bottom();

        table.row();
        table.add(rankLabel);
        table.add(playerLabel);
        table.add(dataLabel);

        table.row();
        table.add(rank1);
        table.add(player1);
        table.add(score1);

        table.row();
        table.add(rank2);
        table.add(player2);
        table.add(score2);

        table.row().expandY().top();
        table.add(rank3);
        table.add(player3);
        table.add(score3);

        stage.addActor(table);

        p1Button.addListener( new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                player = 1;
                game.setScreen(new GameScreen(game,player));
                dispose();
            }
        });
        p2Button.addListener( new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                player = 2;
                game.setScreen(new GameScreen(game,player));
                dispose();
            }
        });
        p3Button.addListener( new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                player = 3;
                game.setScreen(new GameScreen(game,player));
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
