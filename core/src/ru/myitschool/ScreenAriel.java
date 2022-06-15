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
public class ScreenAriel implements Screen {
    final MyGame game;
    final ScreenGame screenGame;
    public Texture imgRamka, imgBack,  imgGoLeft, imgSearch, imgInfo;
    public RealButton btnBack,  btnGoLeft, btnSearch, btnInfo;
    public BitmapFont font;
    public TextMaker tittleAriel, tittleArielEquarorialDiameter, tittleArielMass, tittleArielDistFromPlanet, tittleArielRotationPeriod;
    public TextMaker tittleArielOrbitRotation, tittleArielSurfaceGravity, tittleArielSurfaceTemperature;
    boolean isInfo = false;
    ScreenAriel(MyGame game, ScreenGame screenGame){
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
        imgGoLeft = new Texture("imgGoLeft.png");
        imgSearch = new Texture("imgSearch.png");
        imgInfo = new Texture("imgInfo.png");
        btnBack = new RealButton(SCR_WIDTH-150f, 50f, 100f);
        btnGoLeft = new RealButton(SCR_WIDTH-150f, 200f, 100f);
        btnSearch = new RealButton(SCR_WIDTH-150f, 350f, 100f);
        btnInfo = new RealButton(SCR_WIDTH-150f, 500f, 100f);
        tittleAriel = new TextMaker("ARIEL", font, SCR_WIDTH/5f, SCR_HEIGHT-50f);
        tittleArielEquarorialDiameter = new TextMaker("equatorial diameter       1159 km", font, 30f, tittleAriel.y-100f);
        tittleArielMass = new TextMaker("mass                  1.35*10^21 kg", font, 30f, tittleArielEquarorialDiameter.y-70f);
        tittleArielDistFromPlanet = new TextMaker("dist.from planet        191625 km", font, 30f, tittleArielMass.y-70f);
        tittleArielRotationPeriod = new TextMaker("rotation period             2.5 days", font, 30f, tittleArielDistFromPlanet.y-70f);
        tittleArielOrbitRotation = new TextMaker("planet orbit rotation      2.5 days", font, 30f, tittleArielRotationPeriod.y-70f);
        tittleArielSurfaceGravity = new TextMaker("surface gravity         0.27 m/s^2", font, 30f, tittleArielOrbitRotation.y-70f);
        tittleArielSurfaceTemperature = new TextMaker("surface temperature           60K", font, 30f, tittleArielSurfaceGravity.y-70f);
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
        screenGame.modelBatch.render(screenGame.uran);
        screenGame.modelBatch.render(screenGame.ariel);
        screenGame.modelBatch.end();
        if(Gdx.input.justTouched()) {
            game.touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            game.orthographicCamera.unproject(game.touch);
            if(btnBack.hit(game.touch.x, game.touch.y)){
                game.setScreen(game.screenUranus);
                screenGame.camera.position.set(1f, 50f, 80f);
                screenGame.camera.lookAt(0f, 0f, 0f);
                game.screenUranus.isInfo = false;
                isInfo = false;
            }
            if (btnGoLeft.hit(game.touch.x, game.touch.y)){
                game.setScreen(game.screenUmbriel);
                screenGame.camera.position.set(1f, 50f, 80f);
                screenGame.camera.lookAt(0f, 0f, 0f);
                isInfo = false;
                game.screenUmbriel.isInfo =false;
            }
            if(btnSearch.hit(game.touch.x, game.touch.y)){
                screenGame.camera.position.set(49f,5f,-91f);
                screenGame.camera.lookAt(50f,6f,-90f);
            }
            if(btnInfo.hit(game.touch.x, game.touch.y)){
                isInfo = true;
            }
        }
        game.orthographicCamera.update();
        game.batch.setProjectionMatrix(game.orthographicCamera.combined);
        game.batch.begin();
        game.batch.draw(imgBack, btnBack.x, btnBack.y);
        game.batch.draw(imgGoLeft, btnGoLeft.x, btnGoLeft.y);
        game.batch.draw(imgSearch, btnSearch.x, btnSearch.y);
        game.batch.draw(imgInfo,btnInfo.x, btnInfo.y);
        if(isInfo) {
            font.draw(game.batch, tittleAriel.text, tittleAriel.x, tittleAriel.y);
            font.draw(game.batch, tittleArielEquarorialDiameter.text, tittleArielEquarorialDiameter.x, tittleArielEquarorialDiameter.y);
            font.draw(game.batch, tittleArielMass.text, tittleArielMass.x, tittleArielMass.y);
            font.draw(game.batch, tittleArielDistFromPlanet.text, tittleArielDistFromPlanet.x, tittleArielDistFromPlanet.y);
            font.draw(game.batch, tittleArielRotationPeriod.text, tittleArielRotationPeriod.x, tittleArielRotationPeriod.y);
            font.draw(game.batch, tittleArielOrbitRotation.text, tittleArielOrbitRotation.x, tittleArielOrbitRotation.y);
            font.draw(game.batch, tittleArielSurfaceGravity.text, tittleArielSurfaceGravity.x, tittleArielSurfaceGravity.y);
            font.draw(game.batch, tittleArielSurfaceTemperature.text, tittleArielSurfaceTemperature.x, tittleArielSurfaceTemperature.y);
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
        imgInfo.dispose();
        imgRamka.dispose();
        imgSearch.dispose();
        font.dispose();
    }
}