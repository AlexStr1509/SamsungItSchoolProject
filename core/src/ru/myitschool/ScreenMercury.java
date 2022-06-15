package ru.myitschool;
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

import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.math.Vector3;

import static ru.myitschool.MyGame.SCR_HEIGHT;
import static ru.myitschool.MyGame.SCR_WIDTH;
public class ScreenMercury implements Screen {
    final MyGame game;
    final ScreenGame screenGame;
    public Texture imgRamka, imgBack, imgSearch, imgInfo;
    public RealButton btnBack, btnSearch, btnInfo;
    public BitmapFont font;
    public TextMaker tittleMercury, tittleMercuryEquarorialDiameter, tittleMercuryMass, tittleMercuryDistFromSun, tittleMercuryRotationPeriod;
    public TextMaker tittleMercurySolarOrbitRotation, tittleMercurySurfaceGravity, tittleMercurySurfaceTemperature, tittleMercuryDayTemperature, tittleMercuryNightTemperature;
    public boolean isInfo = false;
    ScreenMercury(MyGame game, ScreenGame screenGame){
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
        imgSearch = new Texture("imgSearch.png");
        imgInfo = new Texture("imgInfo.png");
        btnBack = new RealButton(SCR_WIDTH-150f, 50f, 100f);
        btnSearch = new RealButton(SCR_WIDTH-150f, 200f, 150f);
        btnInfo = new RealButton(SCR_WIDTH-150f, 350f, 100f );
        tittleMercury = new TextMaker("MERCURY", font, SCR_WIDTH/6f, SCR_HEIGHT-50f);
        tittleMercuryEquarorialDiameter = new TextMaker("equatorial diameter         4879 km", font, 30f, tittleMercury.y-100f);
        tittleMercuryMass = new TextMaker("mass                     3.3*10^23 kg", font, 30f, tittleMercuryEquarorialDiameter.y-70f);
        tittleMercuryDistFromSun = new TextMaker("dist.from sun              60.1 m km", font, 30f, tittleMercuryMass.y-70f);
        tittleMercuryRotationPeriod = new TextMaker("rotation period                59 days", font, 30f, tittleMercuryDistFromSun.y-70f);
        tittleMercurySolarOrbitRotation = new TextMaker("solar orbit rotation          88 days", font, 30f, tittleMercuryRotationPeriod.y-70f);
        tittleMercurySurfaceGravity = new TextMaker("surface gravity            3.7 m/s^2", font, 30f, tittleMercurySolarOrbitRotation.y-70f);
        tittleMercurySurfaceTemperature = new TextMaker("surface temperature            440K", font, 30f, tittleMercurySurfaceGravity.y-70f);
        tittleMercuryDayTemperature = new TextMaker("day temperature                 746K", font, 30f, tittleMercurySurfaceTemperature.y-70f);
        tittleMercuryNightTemperature = new TextMaker("night temperature                90K", font, 30f, tittleMercuryDayTemperature.y-70f);
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
        screenGame.modelBatch.render(screenGame.mercury);
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
            if(btnSearch.hit(game.touch.x, game.touch.y)){
               screenGame.camera.position.set(16f,0f,0f);
               screenGame.camera.lookAt(20f, 0f,0f);
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
            font.draw(game.batch, tittleMercury.text, tittleMercury.x, tittleMercury.y);
            font.draw(game.batch, tittleMercuryEquarorialDiameter.text, tittleMercuryEquarorialDiameter.x, tittleMercuryEquarorialDiameter.y);
            font.draw(game.batch, tittleMercuryMass.text, tittleMercuryMass.x, tittleMercuryMass.y);
            font.draw(game.batch, tittleMercuryDistFromSun.text, tittleMercuryDistFromSun.x, tittleMercuryDistFromSun.y);
            font.draw(game.batch, tittleMercuryRotationPeriod.text, tittleMercuryRotationPeriod.x, tittleMercuryRotationPeriod.y);
            font.draw(game.batch, tittleMercurySolarOrbitRotation.text, tittleMercurySolarOrbitRotation.x, tittleMercurySolarOrbitRotation.y);
            font.draw(game.batch, tittleMercurySurfaceGravity.text, tittleMercurySurfaceGravity.x, tittleMercurySurfaceGravity.y);
            font.draw(game.batch, tittleMercurySurfaceTemperature.text, tittleMercurySurfaceTemperature.x, tittleMercurySurfaceTemperature.y);
            font.draw(game.batch, tittleMercuryDayTemperature.text, tittleMercuryDayTemperature.x, tittleMercuryDayTemperature.y);
            font.draw(game.batch, tittleMercuryNightTemperature.text, tittleMercuryNightTemperature.x, tittleMercuryNightTemperature.y);
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
        imgInfo.dispose();
        imgRamka.dispose();
        imgSearch.dispose();
        font.dispose();
    }
}
