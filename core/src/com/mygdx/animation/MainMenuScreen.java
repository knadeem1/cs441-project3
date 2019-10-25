package com.mygdx.animation;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import java.io.File;


public class MainMenuScreen implements Screen {
    private final CookieConstruct game;
    private Stage stage;
    private OrthographicCamera camera;
    private int highScore1;
    private int highScore2;
    private int highScore3;
    private Texture backgroundImg;
    private Texture startImg;
    private Rectangle background;
    private Rectangle start;
    private FileHandle file1;
    private FileHandle file2;
    private FileHandle file3;
    private Skin skin;
    private int player;

    public MainMenuScreen(final CookieConstruct gm, int score, int pl) {
        game = gm;
        player = pl;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800,480);
        /*backgroundImg = new Texture(Gdx.files.internal("icon.png"));
        startImg = new Texture(Gdx.files.internal("start.png"));
        */
        file1 = Gdx.files.local("scores.txt");
        file2 = Gdx.files.local("scores2.txt");
        file3 = Gdx.files.local("scores3.txt");

        //loadScore(score, player);

        /*if(score == 0)highScore = score;
        else{
            loadScore(score, player);
        }

        background = new Rectangle();
        background.width = 300;
        background.height = 300;
        background.x = 250;
        background.y = 180;

        start = new Rectangle();
        start.width = 250;
        start.height = 140;
        start.x = 280;
        start.y = 75;
        */

        show();
    }

    @Override
    public void show() {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        stage.setDebugAll(true);
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
        String p1 = "Player1";
        String p2 = "Player2";
        String p3 = "Player3";
        int max = 0;
        int max2 = 0;
        int max3 = 0;
        /*if(highScore1>=highScore3&&highScore1>=highScore2){
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
        */
        Table table = new Table(skin);
        table.setFillParent(true);

        stage.addActor(table);
        //table.setSize(800,480);
        //table.setPosition(400,200);
        //stage.addActor(table);

        Label topLabel = new Label("COOKIE MONSTER", skin);
        topLabel.setAlignment(Align.center);
        topLabel.setFontScale(6);

        Label nameLabel = new Label("Name:", skin);
        nameLabel.setFontScale(4);

        TextField nameText = new TextField("",skin);

        TextButton p1Button = new TextButton("Player1",skin);
        TextButton p2Button = new TextButton("Player2",skin);
        TextButton p3Button = new TextButton("Player3",skin);

        Label scoreLabel = new Label("LeaderBoard", skin);
        scoreLabel.setAlignment(Align.center);
        scoreLabel.setFontScale(3.5f);

        Label rankLabel = new Label("Rank", skin);
        rankLabel.setAlignment(Align.center);
        rankLabel.setFontScale(2.5f);

        Label playerLabel = new Label("Name", skin);
        playerLabel.setAlignment(Align.center);
        playerLabel.setFontScale(2.5f);

        Label dataLabel = new Label("Points", skin);
        dataLabel.setAlignment(Align.center);
        dataLabel.setFontScale(2.5f);

        Label rank1 = new Label("1", skin);
        rank1.setAlignment(Align.center);
        rank1.setFontScale(2);

        Label rank2 = new Label("2", skin);
        rank2.setAlignment(Align.center);
        rank2.setFontScale(2);

        Label rank3 = new Label("3", skin);
        rank3.setAlignment(Align.center);
        rank3.setFontScale(2);

        //have to get the name from name field
        Label player1 = new Label(p1, skin);
        player1.setAlignment(Align.center);
        player1.setFontScale(2);

        Label player2 = new Label(p2, skin);
        player2.setAlignment(Align.center);
        player2.setFontScale(2);

        Label player3 = new Label(p3, skin);
        player3.setAlignment(Align.center);
        player3.setFontScale(2);

        Label score1 = new Label(String.valueOf(max), skin);
        score1.setAlignment(Align.center);
        score1.setFontScale(2);

        Label score2 = new Label(String.valueOf(max2), skin);
        score2.setAlignment(Align.center);
        score2.setFontScale(2);

        Label score3 = new Label(String.valueOf(max3), skin);
        score3.setAlignment(Align.center);
        score3.setFontScale(2);

        //checkBoxA.setTransform(true);
        //checkBoxA.scaleBy(2f);

        table.row().colspan(3).expandX().fillX();
        table.add(topLabel).expandY();

        table.row();
        table.add(p1Button).uniform();
        table.add(p2Button).uniform();
        table.add(p3Button).uniform();

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

        /*
        table.row().expandX().fillX();
        table.add(buttonTable).colspan(3);

        buttonTable.row().fillX().expandX();
        buttonTable.add(buttonA).width(400);

        */
        stage.addActor(table);

        p1Button.addListener( new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                player = 1;
                game.setScreen(new GameScreen(game,player));
            }
        });
        p2Button.addListener( new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                player = 2;
                game.setScreen(new GameScreen(game,player));
            }
        });
        p3Button.addListener( new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                player = 3;
                game.setScreen(new GameScreen(game,player));
            }
        });
    }



    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        stage.act();
        stage.draw();

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        /*
        game.batch.begin();

        game.batch.draw(backgroundImg,background.x,background.y);
        game.batch.draw(startImg,start.x,start.y);

        game.font.setColor(Color.WHITE);
        game.font.getData().setScale(2);
        //game.font.draw(game.batch, "COOKIE MONSTER", 200, 150);
        //game.font.draw(game.batch, "START", 320, 100);
        game.font.setColor(Color.BLUE);
        game.font.draw(game.batch, "HIGH SCORE: " + highScore, 285, 60);
        game.font.getData().setScale(2);

        game.batch.end();

        if (Gdx.input.isTouched()) {
            game.setScreen(new GameScreen(game));
            dispose();
        }
         */
    }

    //*******NOT WORKING*******************
    public void loadScore(int score, int player){
        if(player == 0){
            //if the game started from scratch: called from CookieConstruct, player not selected yet
            //scores don't change, only read from file and loaded into variables
            for(int i=1;i<4;i++){
                if(i==1){
                    highScore1 = Integer.parseInt(file1.readString());
                }
                if(i==2){
                    highScore2 = Integer.parseInt(file2.readString());
                }
                if(i==3){
                    highScore3 = Integer.parseInt(file3.readString());
                }
            }
        }
        else{
            //if returned a score for a player from GameScreen()
            //figure out what player was playing
            //determine if the high core for that player changed
            //read and load scores from all players
            if(player==1){
                if(file1.exists()) {
                    try {
                        //reads the first line of the file and reads it as an integer
                        highScore1 = Integer.parseInt(file1.readString());
                        file1.writeString(String.valueOf(score), false);
                        if (score > highScore1) {
                            highScore1 = score;
                        }
                        file1.writeString(String.valueOf(highScore1), false);
                    } catch (NumberFormatException e) {
                        //if there was no high score found, set it to 0
                        highScore1 = 0;
                    }
                }
                highScore2 = Integer.parseInt(file2.readString());
                highScore3 = Integer.parseInt(file3.readString());
            }
            if(player==2){
                if(file2.exists()) {
                    try {
                        //reads the first line of the file and reads it as an integer
                        highScore2 = Integer.parseInt(file2.readString());
                        file2.writeString(String.valueOf(score), false);
                        if (score > highScore2) {
                            highScore2 = score;
                        }
                        file2.writeString(String.valueOf(highScore2), false);
                    } catch (NumberFormatException e) {
                        //if there was no high score found, set it to 0
                        highScore2 = 0;
                    }
                }
                highScore1 = Integer.parseInt(file1.readString());
                highScore3 = Integer.parseInt(file3.readString());
            }
            if(player==3){
                if(file3.exists()) {
                    try {
                        //reads the first line of the file and reads it as an integer
                        highScore3 = Integer.parseInt(file3.readString());
                        file3.writeString(String.valueOf(score), false);
                        if (score > highScore3) {
                            highScore3 = score;
                        }
                        file3.writeString(String.valueOf(highScore3), false);
                    } catch (NumberFormatException e) {
                        //if there was no high score found, set it to 0
                        highScore3 = 0;
                    }
                }
                highScore2 = Integer.parseInt(file2.readString());
                highScore1 = Integer.parseInt(file1.readString());
            }
        }
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
