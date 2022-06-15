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
public class ScreenDeimos implements Screen {
    final MyGame game;
    final ScreenGame screenGame;
    public Texture imgRamka, imgBack, imgGoRight, imgSearch, imgInfo;
    public  RealButton btnBack, btnGoRight, btnSearch, btnInfo;
    public BitmapFont font;
    public TextMaker tittleDeimos, tittleDeimosEquarorialDiameter, tittleDeimosMass, tittleDeimosDistFromPlanet, tittleDeimosRotationPeriod;
    public TextMaker tittleDeimosOrbitRotation, tittleDeimosSurfaceGravity, tittleDeimosSurfaceTemperature;
    boolean isInfo = false;
    ScreenDeimos(MyGame game, ScreenGame screenGame){
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
        imgSearch = new Texture("imgSearch.png");
        imgInfo = new Texture("imgInfo.png");
        btnBack = new RealButton(SCR_WIDTH-150f, 50f, 100f);
        btnGoRight = new RealButton(SCR_WIDTH-150f, 200f, 100f);
        btnSearch = new RealButton(SCR_WIDTH-150f, 350f, 100f);
        btnInfo = new RealButton(SCR_WIDTH-150f, 500f, 100f);
        tittleDeimos = new TextMaker("DEIMOS", font, SCR_WIDTH/5.5f, SCR_HEIGHT-50f);
        tittleDeimosEquarorialDiameter = new TextMaker("equatorial diameter          15 km", font, 30f, tittleDeimos.y-100f);
        tittleDeimosMass = new TextMaker("mass                   1.48*10^15 kg", font, 30f, tittleDeimosEquarorialDiameter.y-70f);
        tittleDeimosDistFromPlanet = new TextMaker("dist.from planet          23481 km", font, 30f, tittleDeimosMass.y-70f);
        tittleDeimosRotationPeriod = new TextMaker("rotation period            1.26 days", font, 30f, tittleDeimosDistFromPlanet.y-70f);
        tittleDeimosOrbitRotation = new TextMaker("planet orbit rotation    1.26 days", font, 30f, tittleDeimosRotationPeriod.y-70f);
        tittleDeimosSurfaceGravity = new TextMaker("surface gravity        0.003 m/s^2", font, 30f, tittleDeimosOrbitRotation.y-70f);
        tittleDeimosSurfaceTemperature = new TextMaker("surface temperature         233K", font, 30f, tittleDeimosSurfaceGravity.y-70f);
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
        screenGame.modelBatch.render(screenGame.mars);
        screenGame.modelBatch.render(screenGame.deymos);
        screenGame.modelBatch.end();
        if(Gdx.input.justTouched()) {
            game.touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            game.orthographicCamera.unproject(game.touch);
            if(btnBack.hit(game.touch.x, game.touch.y)){
                game.setScreen(game.screenMars);
                screenGame.camera.position.set(1f, 50f, 80f);
                screenGame.camera.lookAt(0f, 0f, 0f);
                isInfo = false;
                game.screenMars.isInfo = false;
                game.screenPhobos.isInfo = false;
            }
            if(btnGoRight.hit(game.touch.x, game.touch.y)){
                game.setScreen(game.screenPhobos);
                screenGame.camera.position.set(1f, 50f, 80f);
                screenGame.camera.lookAt(0f, 0f, 0f);
                game.screenPhobos.isInfo = false;
            }
            if(btnSearch.hit(game.touch.x, game.touch.y)){
                screenGame.camera.position.set(-4f, -2f, 38f);
                screenGame.camera.lookAt(0f, 2f, 42f);
            }
            if(btnInfo.hit(game.touch.x, game.touch.y)){
                isInfo = true;
            }
        }
        game.orthographicCamera.update();
        game.batch.setProjectionMatrix(game.orthographicCamera.combined);
        game.batch.begin();
        game.batch.draw(imgBack, btnBack.x, btnBack.y);
        game.batch.draw(imgGoRight, btnGoRight.x, btnGoRight.y);
        game.batch.draw(imgSearch, btnSearch.x, btnSearch.y);
        game.batch.draw(imgInfo, btnInfo.x, btnInfo.y);
        if(isInfo) {
            font.draw(game.batch, tittleDeimos.text, tittleDeimos.x, tittleDeimos.y);
            font.draw(game.batch, tittleDeimosEquarorialDiameter.text, tittleDeimosEquarorialDiameter.x, tittleDeimosEquarorialDiameter.y);
            font.draw(game.batch, tittleDeimosMass.text, tittleDeimosMass.x, tittleDeimosMass.y);
            font.draw(game.batch, tittleDeimosDistFromPlanet.text, tittleDeimosDistFromPlanet.x, tittleDeimosDistFromPlanet.y);
            font.draw(game.batch, tittleDeimosRotationPeriod.text, tittleDeimosRotationPeriod.x, tittleDeimosRotationPeriod.y);
            font.draw(game.batch, tittleDeimosOrbitRotation.text, tittleDeimosOrbitRotation.x, tittleDeimosOrbitRotation.y);
            font.draw(game.batch, tittleDeimosSurfaceGravity.text, tittleDeimosSurfaceGravity.x, tittleDeimosSurfaceGravity.y);
            font.draw(game.batch, tittleDeimosSurfaceTemperature.text, tittleDeimosSurfaceTemperature.x, tittleDeimosSurfaceTemperature.y);
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
        imgGoRight.dispose();
        imgInfo.dispose();
        imgRamka.dispose();
        imgSearch.dispose();
        font.dispose();
    }
}
