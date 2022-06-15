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
public class ScreenDione implements Screen {
    final MyGame game;
    final ScreenGame screenGame;
    public Texture imgRamka, imgBack, imgGoRight, imgGoLeft, imgSearch, imgInfo;
    public  RealButton btnBack, btnGoRight, btnGoLeft, btnSearch, btnInfo;
    public BitmapFont font;
    public TextMaker tittleDione, tittleDioneEquarorialDiameter, tittleDioneMass, tittleDioneDistFromPlanet, tittleDioneRotationPeriod;
    public TextMaker tittleDioneOrbitRotation, tittleDioneSurfaceGravity, tittleDioneSurfaceTemperature;
    boolean isInfo = false;
    ScreenDione(MyGame game, ScreenGame screenGame){
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
        imgGoRight = new Texture("imgGoRight.png");
        imgGoLeft = new Texture("imgGoLeft.png");
        imgSearch = new Texture("imgSearch.png");
        imgInfo = new Texture("imgInfo.png");
        btnBack = new RealButton(SCR_WIDTH-150f, 50f, 100f);
        btnGoRight = new RealButton(SCR_WIDTH-150f, 200f, 100f);
        btnGoLeft = new RealButton(SCR_WIDTH-150f, 350f, 100f);
        btnSearch = new RealButton(SCR_WIDTH-150f, 500f, 100f);
        btnInfo = new RealButton(SCR_WIDTH-150f, 650f, 100f);
        tittleDione = new TextMaker("DIONE", font, SCR_WIDTH/5f, SCR_HEIGHT-50f);
        tittleDioneEquarorialDiameter = new TextMaker("equatorial diameter       1124 km", font, 30f, tittleDione.y-100f);
        tittleDioneMass = new TextMaker("mass                   1.1*10^21 kg", font, 30f, tittleDioneEquarorialDiameter.y-70f);
        tittleDioneDistFromPlanet = new TextMaker("dist.from planet         383447 km", font, 30f, tittleDioneMass.y-70f);
        tittleDioneRotationPeriod = new TextMaker("rotation period            2.7 days", font, 30f, tittleDioneDistFromPlanet.y-70f);
        tittleDioneOrbitRotation = new TextMaker("planet orbit rotation     2.7 days", font, 30f, tittleDioneRotationPeriod.y-70f);
        tittleDioneSurfaceGravity = new TextMaker("surface gravity         0.23 m/s^2", font, 30f, tittleDioneOrbitRotation.y-70f);
        tittleDioneSurfaceTemperature = new TextMaker("surface temperature           87K", font, 30f, tittleDioneSurfaceGravity.y-70f);
    }
    @Override
    public void show() {}
    @Override
    public void render(float delta) {
        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        screenGame.modelBatch.begin(screenGame.camera);
        screenGame.modelBatch.render(screenGame.space);
        screenGame.modelBatch.render(screenGame.sun);
        screenGame.modelBatch.render(screenGame.saturn);
        screenGame.modelBatch.render(screenGame.diona);
        screenGame.modelBatch.end();
        if(Gdx.input.justTouched()) {
            game.touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            game.orthographicCamera.unproject(game.touch);
            if(btnBack.hit(game.touch.x, game.touch.y)){
                game.setScreen(game.screenSaturn);
                screenGame.camera.position.set(1f, 50f, 80f);
                screenGame.camera.lookAt(0f, 0f, 0f);
                isInfo=false;
                game.screenSaturn.isInfo = false;
            }
            if(btnGoRight.hit(game.touch.x, game.touch.y)){
                game.setScreen(game.screenTethys);
                screenGame.camera.position.set(1f, 50f, 80f);
                screenGame.camera.lookAt(0f, 0f, 0f);
                game.screenTethys.isInfo = false;
            }
            if (btnGoLeft.hit(game.touch.x, game.touch.y)){
                game.setScreen(game.screenIapetus);
                screenGame.camera.position.set(1f, 50f, 80f);
                screenGame.camera.lookAt(0f, 0f, 0f);
                game.screenIapetus.isInfo = false;
            }
            if(btnSearch.hit(game.touch.x, game.touch.y)){
                screenGame.camera.position.set(92f, -1f, -9f);
                screenGame.camera.lookAt(95f, 2f,-6f);
            }
            if(btnInfo.hit(game.touch.x, game.touch.y)){
                isInfo=true;
            }
        }
        game.orthographicCamera.update();
        game.batch.setProjectionMatrix(game.orthographicCamera.combined);
        game.batch.begin();
        game.batch.draw(imgBack, btnBack.x, btnBack.y);
        game.batch.draw(imgGoRight, btnGoRight.x, btnGoRight.y);
        game.batch.draw(imgGoLeft, btnGoLeft.x, btnGoLeft.y);
        game.batch.draw(imgSearch, btnSearch.x, btnSearch.y);
        game.batch.draw(imgInfo, btnInfo.x, btnInfo.y);
        if(isInfo) {
            font.draw(game.batch, tittleDione.text, tittleDione.x, tittleDione.y);
            font.draw(game.batch, tittleDioneEquarorialDiameter.text, tittleDioneEquarorialDiameter.x, tittleDioneEquarorialDiameter.y);
            font.draw(game.batch, tittleDioneMass.text, tittleDioneMass.x, tittleDioneMass.y);
            font.draw(game.batch, tittleDioneDistFromPlanet.text, tittleDioneDistFromPlanet.x, tittleDioneDistFromPlanet.y);
            font.draw(game.batch, tittleDioneRotationPeriod.text, tittleDioneRotationPeriod.x, tittleDioneRotationPeriod.y);
            font.draw(game.batch, tittleDioneOrbitRotation.text, tittleDioneOrbitRotation.x, tittleDioneOrbitRotation.y);
            font.draw(game.batch, tittleDioneSurfaceGravity.text, tittleDioneSurfaceGravity.x, tittleDioneSurfaceGravity.y);
            font.draw(game.batch, tittleDioneSurfaceTemperature.text, tittleDioneSurfaceTemperature.x, tittleDioneSurfaceTemperature.y);
        }
        game.batch.end();
    }
    @Override
    public void resize(int width, int height) {}
    @Override
    public void pause() {}
    @Override
    public void resume() {}
    @Override
    public void hide() {}
    @Override
    public void dispose() {
        imgBack.dispose();
        imgGoLeft.dispose();
        imgGoRight.dispose();
        imgInfo.dispose();
        imgRamka.dispose();
        imgSearch.dispose();
        font.dispose();
    }
}