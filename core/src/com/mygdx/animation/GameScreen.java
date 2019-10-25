package com.mygdx.animation;

import java.util.Iterator;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class GameScreen implements Screen {
	private OrthographicCamera camera;
	private final CookieConstruct game;

	private int player;

	private Texture grassImg;
	private Texture cloudImg;
	private Rectangle grass;
	private Rectangle cloud;

	private Sound crunchSound;
	private Music music;
	private Sound explosion;
	private Texture cookieImg;
	private Texture bombImg;
	private Texture monsterImg;

	private Rectangle monster;
	private Array<Rectangle> cookies;
	private Array<Rectangle> bombs;

	private long time;
	private long timebomb;
	private int total;
	private int level;
	private String penalty;
	private int tracker = 10;
	private int speed = 50;

	public GameScreen(final CookieConstruct gm, int pl) {
		this.game = gm;
		this.player = pl;
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);

		cloudImg = new Texture(Gdx.files.internal("cloud.png"));
		grassImg = new Texture(Gdx.files.internal("grass.png"));
		grass = new Rectangle();
		grass.width = 800;
		grass.height = 150;
		grass.x = 0;
		grass.y = 0;
		cloud = new Rectangle();
		cloud.width = 800;
		cloud.height = 130;
		cloud.x = 0;
		cloud.y = 350;

		cookieImg = new Texture(Gdx.files.internal("cookie.png"));
		bombImg = new Texture(Gdx.files.internal("bomb.png"));
		monsterImg = new Texture(Gdx.files.internal("cookiemonster.png"));
		crunchSound = Gdx.audio.newSound(Gdx.files.internal("crunch.wav"));
		explosion = Gdx.audio.newSound(Gdx.files.internal("explosion.wav"));
		music = Gdx.audio.newMusic(Gdx.files.internal("soundtrack.mp3"));
		music.setLooping(true);

		penalty = "";
		monster = new Rectangle();
		monster.x = (800 / 2) - (64 / 2);
		monster.y = 50;
		monster.width = 64;
		monster.height = 64;
		cookies = new Array<>();
		bombs = new Array<>();
		constructCookie();
		constructBomb();

	}

	private void constructCookie() {
		Rectangle cookie = new Rectangle();
		cookie.x = MathUtils.random(100, 700 - 64);
		cookie.y = 480;
		cookie.width = 64;
		cookie.height = 64;
		cookies.add(cookie);
		time = TimeUtils.nanoTime();
	}

	private void constructBomb() {
		Rectangle bomb = new Rectangle();
		bomb.x = MathUtils.random(100, 700 - 64);
		bomb.y = 480;
		bomb.width = 64;
		bomb.height = 64;
		bombs.add(bomb);
		timebomb = TimeUtils.nanoTime();
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0.1f, 0.1f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update();

		game.batch.setProjectionMatrix(camera.combined);
		game.batch.begin();
		game.batch.draw(cloudImg,cloud.x,cloud.y);
		game.batch.draw(monsterImg, monster.x, monster.y);
		game.batch.draw(grassImg,grass.x,grass.y);

		game.font.setColor(Color.WHITE);
		game.font.draw(game.batch, "TOTAL: " + total, 50, 475);
		game.font.setColor(Color.YELLOW);
		game.font.draw(game.batch, "LEVEL: " + level, 320,475);
		game.font.setColor(Color.RED);
		game.font.draw(game.batch, "PENALTY:" + penalty, 540, 475);
		game.font.getData().setScale(2);

		for (Rectangle cookie:cookies){
			game.batch.draw(cookieImg, cookie.x, cookie.y);
		}

		if(level >= 1){
			for (Rectangle bomb: bombs){
				game.batch.draw(bombImg, bomb.x, bomb.y);
			}
		}

		game.batch.end();

		//touch/click compatibility
		if (Gdx.input.isTouched()) {
			Vector3 touchPos = new Vector3();
			touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(touchPos);
			monster.x = touchPos.x - 64 / 2;
		}

		//keyboard compatibility
		if (Gdx.input.isKeyPressed(Keys.UP)) monster.x -= 200 * Gdx.graphics.getDeltaTime();
		if (Gdx.input.isKeyPressed(Keys.DOWN)) monster.x += 200 * Gdx.graphics.getDeltaTime();
		if (monster.x < 0) monster.x = 0;
		if (monster.x > 800 - 64) monster.x = 800 - 64;

		if(level == 0) {
			if (TimeUtils.nanoTime() - time > 1999999999) constructCookie();
		}
		if(level == 1) {
			if (TimeUtils.nanoTime() - time > 1999999999) constructCookie();
			if (TimeUtils.nanoTime() - timebomb > 2147479999) constructBomb();
		}
		if(level == 2){
			if (TimeUtils.nanoTime() - time > 1000000000) constructCookie();
			if (TimeUtils.nanoTime() - timebomb > 2147479999) constructBomb();
		}
		if(level == 3){
			if (TimeUtils.nanoTime() - time > 1999999999) constructCookie();
			if (TimeUtils.nanoTime() - timebomb > 2000000000) constructBomb();
		}
		if(level == 4){
			if (TimeUtils.nanoTime() - timebomb > 1000000000) constructBomb();
		}
		if(level >= 5){
			if (TimeUtils.nanoTime() - time > 1000000000) constructCookie();
		}

		//to iterate over all cookies in array
		//if reached a certain total, a new level is achieved
		Iterator<Rectangle> i = cookies.iterator();

		while (i.hasNext()) {
			Rectangle cookie = i.next();
			cookie.y -= speed * Gdx.graphics.getDeltaTime();

			if (cookie.overlaps(monster)) {
				total++;
				crunchSound.play();
				i.remove();

				if(total == tracker){
					level++;
					speed += 10;
					tracker += 10;
				}
			}

			if(cookie.y + 64 < 0 && level < 5) {
				i.remove();
			}

			if(cookie.y + 64 < 0 && level >= 5){
				i.remove();
				penalty = penalty + " X";

				if(penalty.equals(" X X X")) {
					game.setScreen(new MainMenuScreen(game, total, this.player));
					dispose();
				}
			}
		}

		if(level >= 1) {
			Iterator<Rectangle> j = bombs.iterator();

			while (j.hasNext()) {
				Rectangle bomb = j.next();
				bomb.y -= speed * Gdx.graphics.getDeltaTime();

				if (bomb.overlaps(monster)) {
					penalty = penalty + " X";
					explosion.play();
					j.remove();
					speed -= 8;

					if(penalty.equals(" X X X")){
						//game.font.draw(game.batch, "High Score: " + total, 100, 50);
						game.setScreen(new MainMenuScreen(game, total, this.player));
						//game.font.draw(game.batch, "High Score: " + total, 100, 50);
						dispose();
					}
				}
				if(bomb.y + 64 < 0 && level != 4) {
					j.remove();
				}

				if(bomb.y + 64 < 0 && level == 4){
					j.remove();
					total++;
					speed += 2;

					if(total == tracker) {
						level++;
						speed += 10;
						tracker += 10;
					}
				}

			}
		}


	}


	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void show() {
		music.play();
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
		cookieImg.dispose();
		monsterImg.dispose();
		bombImg.dispose();
		explosion.dispose();
		crunchSound.dispose();
		music.dispose();
	}
}
