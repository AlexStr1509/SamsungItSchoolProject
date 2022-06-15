package ru.myitschool;
import static ru.myitschool.MyGame.SCR_HEIGHT;
import static ru.myitschool.MyGame.SCR_WIDTH;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.math.Vector3;
public class ScreenEarth implements Screen {
    final MyGame game;
    final ScreenGame screenGame;
    public Texture imgRamka, imgBack, imgCheck, imgSearch, imgInfo;
    public RealButton btnBack, btnCheckSattelite, btnSearch, btnInfo;
    public BitmapFont font;
    public TextMaker tittleEarth, tittleEarthEquarorialDiameter, tittleEarthMass, tittleEarthDistFromSun, tittleEarthRotationPeriod;
    public TextMaker tittleEarthSolarOrbitRotation, tittleEarthSurfaceGravity, tittleEarthSurfaceTemperature,tittleEarthsMoon;
    boolean isInfo = false;
    ScreenEarth(MyGame game, ScreenGame screenGame){
        this.game = game;
        this.screenGame = screenGame;
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("dscrystal.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = SCR_WIDTH/35;
        parameter.borderColor= Color.BLUE;
        parameter.borderWidth = 2f;
        parameter.color = Color.PURPLE;
        font = generator.generateFont(parameter);
        imgBack = new Texture("imgExit.png");
        imgCheck = new Texture("imgCheck.png");
        imgSearch = new Texture("imgSearch.png");
        imgInfo = new Texture("imgInfo.png");
        btnBack = new RealButton(SCR_WIDTH-150f, 50f, 100f);
        btnCheckSattelite = new RealButton(SCR_WIDTH/4.75f, 100f, 100f);
        btnSearch = new RealButton(SCR_WIDTH-150f, 200f, 150f);
        btnInfo = new RealButton(SCR_WIDTH-150f, 350f, 100f);
        tittleEarth = new TextMaker("EARTH", font, SCR_WIDTH/4.75f, SCR_HEIGHT-50f);
        tittleEarthEquarorialDiameter = new TextMaker("equatorial diameter        12756 km", font, 30f, tittleEarth.y-100f);
        tittleEarthMass = new TextMaker("mass                    6.0*10^24 kg", font, 30f, tittleEarthEquarorialDiameter.y-70f);
        tittleEarthDistFromSun = new TextMaker("dist.from sun              150 m km", font, 30f, tittleEarthMass.y-70f);
        tittleEarthRotationPeriod = new TextMaker("rotation period            23 h 56 m", font, 30f, tittleEarthDistFromSun.y-70f);
        tittleEarthSolarOrbitRotation = new TextMaker("solar orbit rotation          1 year", font, 30f, tittleEarthRotationPeriod.y-70f);
        tittleEarthSurfaceGravity = new TextMaker("surface gravity           9.8 m/s^2", font, 30f, tittleEarthSolarOrbitRotation.y-70f);
        tittleEarthSurfaceTemperature = new TextMaker("surface temperature           288K", font, 30f, tittleEarthSurfaceGravity.y-70f);
        tittleEarthsMoon = new TextMaker("Earth`s Satellite", font, SCR_WIDTH/8f, 250f);
    }
    @Override
    public void show() { }
    @Override
    public void render(float delta) {
        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        screenGame.modelBatch.begin(screenGame.camera);
        screenGame.modelBatch.render(screenGame.space);
        screenGame.modelBatch.render(screenGame.sun);
        screenGame.modelBatch.render(screenGame.earth);
        screenGame.modelBatch.render(screenGame.moon);
        screenGame.modelBatch.end();
        if(Gdx.input.justTouched()) {
            game.touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            game.orthographicCamera.unproject(game.touch);
            if(btnBack.hit(game.touch.x, game.touch.y)){
                game.setScreen(game.screenSearch);
                screenGame.camera.position.set(1f, 50f, 80f);
                screenGame.camera.lookAt(0f, 0f, 0f);
                isInfo = false;
            }
            if(btnCheckSattelite.hit(game.touch.x, game.touch.y)){
                game.setScreen(game.screenMoon);
                screenGame.camera.position.set(1f, 50f, 80f);
                screenGame.camera.lookAt(0f, 0f, 0f);
                isInfo = false;
            }
            if(btnSearch.hit(game.touch.x, game.touch.y)){
                screenGame.camera.position.set(36f,0f,-19f);
                screenGame.camera.lookAt(40f,0f,-15f);
            }
            if(btnInfo.hit(game.touch.x, game.touch.y)){
                isInfo = true;
            }
        }
        game.orthographicCamera.update();
        game.batch.setProjectionMatrix(game.orthographicCamera.combined);
        game.batch.begin();
        game.batch.draw(imgBack, btnBack.x, btnBack.y);
        game.batch.draw(imgSearch, btnSearch.x, btnSearch.y);
        game.batch.draw(imgInfo, btnInfo.x, btnInfo.y);
        if(isInfo) {
            game.batch.draw(imgCheck, btnCheckSattelite.x, btnCheckSattelite.y);
            font.draw(game.batch, tittleEarth.text, tittleEarth.x, tittleEarth.y);
            font.draw(game.batch, tittleEarthEquarorialDiameter.text, tittleEarthEquarorialDiameter.x, tittleEarthEquarorialDiameter.y);
            font.draw(game.batch, tittleEarthMass.text, tittleEarthMass.x, tittleEarthMass.y);
            font.draw(game.batch, tittleEarthDistFromSun.text, tittleEarthDistFromSun.x, tittleEarthDistFromSun.y);
            font.draw(game.batch, tittleEarthRotationPeriod.text, tittleEarthRotationPeriod.x, tittleEarthRotationPeriod.y);
            font.draw(game.batch, tittleEarthSolarOrbitRotation.text, tittleEarthSolarOrbitRotation.x, tittleEarthSolarOrbitRotation.y);
            font.draw(game.batch, tittleEarthSurfaceGravity.text, tittleEarthSurfaceGravity.x, tittleEarthSurfaceGravity.y);
            font.draw(game.batch, tittleEarthSurfaceTemperature.text, tittleEarthSurfaceTemperature.x, tittleEarthSurfaceTemperature.y);
            font.draw(game.batch, tittleEarthsMoon.text, tittleEarthsMoon.x, tittleEarthsMoon.y);
        }
        game.batch.end();
    }
    @Override
    public void resize(int width, int height) { }
    @Override
    public void pause() { }
    @Override
    public void resume() { }
    @Override
    public void hide() { }
    @Override
    public void dispose() {
        imgBack.dispose();
        imgCheck.dispose();
        imgInfo.dispose();
        imgRamka.dispose();
        imgSearch.dispose();
        font.dispose();
    }
}
