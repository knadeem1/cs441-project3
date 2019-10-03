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


public class MainMenuScreen implements Screen {
    private final CookieConstruct game;
    private OrthographicCamera camera;
    private int highScore;
    private Texture backgroundImg;
    private Texture startImg;
    private Rectangle background;
    private Rectangle start;
    private FileHandle file;

    public MainMenuScreen(final CookieConstruct gm, int score) {
        game = gm;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800,480);
        backgroundImg = new Texture(Gdx.files.internal("icon.png"));
        startImg = new Texture(Gdx.files.internal("start.png"));

        file = Gdx.files.local("scores.txt");
        String text = file.readString();

        if(score == 0)highScore = score;
        else{
            loadScore(score);
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
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

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
    }

    public void loadScore(int score){
        if(file.exists()) {
            try {
                //reads the first line of the file and reads it as an integer
                highScore = Integer.parseInt(file.readString());
                file.writeString(String.valueOf(score),false);
                if(score > highScore){
                    highScore = score;
                }
                file.writeString(String.valueOf(highScore),false);
            } catch (NumberFormatException e){
                //if there was no high score found, set it to 0
                highScore = 0;
            }
        }
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void show() {

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
