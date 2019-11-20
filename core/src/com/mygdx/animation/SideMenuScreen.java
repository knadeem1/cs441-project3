package com.mygdx.animation;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;


public class SideMenuScreen extends ApplicationAdapter {


    private Stage stage;

    public void create() {
        stage = new Stage(new ScreenViewport());
        //stage.setDebugAll(true);

        Skin skin = new Skin(Gdx.files.internal("uiskin.json"));
        Container<Table> tableContainer = new Container<>();

        float sw = Gdx.graphics.getWidth();
        float sh = Gdx.graphics.getHeight();

        float cw = sw * 0.7f;
        float ch = sh * 0.5f;

        tableContainer.setSize(cw, ch);
        tableContainer.setPosition((sw - cw) / 2.0f, (sh - ch) / 2.0f);
        tableContainer.fillX();

        Table table = new Table(skin);

        tableContainer.setSize(sw,sh);
        tableContainer.setPosition((sw-100),sh-100);
        tableContainer.fillX();

        Label topLabel = new Label("COOKIE MONSTER", skin);
        topLabel.setAlignment(Align.center);
        Slider slider = new Slider(0, 100, 1, false, skin);
        Label anotherLabel = new Label("LeaderBoard", skin);
        anotherLabel.setAlignment(Align.center);

        CheckBox checkBoxA = new CheckBox("User 1", skin);
        CheckBox checkBoxB = new CheckBox("User 2", skin);
        CheckBox checkBoxC = new CheckBox("User 3", skin);

        Table buttonTable = new Table(skin);

        TextButton buttonA = new TextButton("Start", skin);
        TextButton buttonB = new TextButton("Finish", skin);

        TextButton buttonC = new TextButton("Begin", skin);
        TextButton buttonD = new TextButton("End", skin);

        table.row().colspan(3).expandX().fillX();
        table.add(topLabel).fillX();

        table.row().colspan(3).expandX().fillX();
        table.add(slider).fillX();

        table.row().colspan(3).expandX().fillX();
        table.add(anotherLabel).fillX();

        table.row().expandX().fillX();
        table.add(checkBoxA).expandX().fillX();
        table.add(checkBoxB).expandX().fillX();
        table.add(checkBoxC).expandX().fillX();

        table.row().expandX().fillX();
        table.add(buttonTable).colspan(3);

        buttonTable.pad(16);
        buttonTable.row().fillX().expandX();
        buttonTable.add(buttonA).width(cw/3.0f);
        buttonTable.add(buttonB).width(cw/3.0f);

        tableContainer.setActor(table);
        stage.addActor(tableContainer);

        Gdx.input.setInputProcessor(stage);

    }

    @Override
    public void render() {
        Gdx.gl.glClearColor( 0, 0, 0, 0 );
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

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
