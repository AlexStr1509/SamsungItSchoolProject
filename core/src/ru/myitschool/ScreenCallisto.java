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
public class ScreenCallisto implements Screen {
    final MyGame game;
    final ScreenGame screenGame;
    public Texture imgRamka, imgBack, imgGoRight, imgGoLeft, imgSearch, imgInfo;
    public  RealButton btnBack, btnGoRight, btnGoLeft, btnSearch, btnInfo;
    public BitmapFont font;
    public TextMaker tittleCallisto, tittleCallistoEquarorialDiameter, tittleCallistoMass, tittleCallistoDistFromPlanet, tittleCallistoRotationPeriod;
    public TextMaker tittleCallistoOrbitRotation, tittleCallistoSurfaceGravity, tittleCallistoSurfaceTemperature;
    boolean isInfo = false;
    ScreenCallisto(MyGame game, ScreenGame screenGame){
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
        tittleCallisto = new TextMaker("CALLISTO", font, SCR_WIDTH/6f, SCR_HEIGHT-50f);
        tittleCallistoEquarorialDiameter = new TextMaker("equatorial diameter       4821 km", font, 30f, tittleCallisto.y-100f);
        tittleCallistoMass = new TextMaker("mass                  1.08*10^23 kg", font, 30f, tittleCallistoEquarorialDiameter.y-70f);
        tittleCallistoDistFromPlanet = new TextMaker("dist.from planet         1.88 m km", font, 30f, tittleCallistoMass.y-70f);
        tittleCallistoRotationPeriod = new TextMaker("rotation period            16.7 days", font, 30f, tittleCallistoDistFromPlanet.y-70f);
        tittleCallistoOrbitRotation = new TextMaker("planet orbit rotation     16.7 days", font, 30f, tittleCallistoRotationPeriod.y-70f);
        tittleCallistoSurfaceGravity = new TextMaker("surface gravity         1.24 m/s^2", font, 30f, tittleCallistoOrbitRotation.y-70f);
        tittleCallistoSurfaceTemperature = new TextMaker("surface temperature          101K", font, 30f, tittleCallistoSurfaceGravity.y-70f);
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
        screenGame.modelBatch.render(screenGame.jupiter);
        screenGame.modelBatch.render(screenGame.calisto);
        screenGame.modelBatch.end();
        if(Gdx.input.justTouched()) {
            game.touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            game.orthographicCamera.unproject(game.touch);
            if(btnBack.hit(game.touch.x, game.touch.y)){
                game.setScreen(game.screenJupiter);
                game.screenJupiter.isInfo=false;
                screenGame.camera.position.set(1f, 50f, 80f);
                screenGame.camera.lookAt(0f, 0f, 0f);
                game.screenJupiter.isInfo = false;
            }
            if(btnGoRight.hit(game.touch.x, game.touch.y)){
                game.setScreen(game.screenEuropa);
                isInfo = false;
                screenGame.camera.position.set(1f, 50f, 80f);
                screenGame.camera.lookAt(0f, 0f, 0f);
            }
            if (btnGoLeft.hit(game.touch.x, game.touch.y)){
                game.setScreen(game.screenGanymede);
                isInfo=false;
                game.screenGanymede.isInfo=false;
                screenGame.camera.position.set(1f, 50f, 80f);
                screenGame.camera.lookAt(0f, 0f, 0f);
            }
            if(btnSearch.hit(game.touch.x, game.touch.y)){
                screenGame.camera.position.set(-80.1f, 5.1f, 14.1f);
                screenGame.camera.lookAt(-80f, 6f, 15f);
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
            font.draw(game.batch, tittleCallisto.text, tittleCallisto.x, tittleCallisto.y);
            font.draw(game.batch, tittleCallistoEquarorialDiameter.text, tittleCallistoEquarorialDiameter.x, tittleCallistoEquarorialDiameter.y);
            font.draw(game.batch, tittleCallistoMass.text, tittleCallistoMass.x, tittleCallistoMass.y);
            font.draw(game.batch, tittleCallistoDistFromPlanet.text, tittleCallistoDistFromPlanet.x, tittleCallistoDistFromPlanet.y);
            font.draw(game.batch, tittleCallistoRotationPeriod.text, tittleCallistoRotationPeriod.x, tittleCallistoRotationPeriod.y);
            font.draw(game.batch, tittleCallistoOrbitRotation.text, tittleCallistoOrbitRotation.x, tittleCallistoOrbitRotation.y);
            font.draw(game.batch, tittleCallistoSurfaceGravity.text, tittleCallistoSurfaceGravity.x, tittleCallistoSurfaceGravity.y);
            font.draw(game.batch, tittleCallistoSurfaceTemperature.text, tittleCallistoSurfaceTemperature.x, tittleCallistoSurfaceTemperature.y);
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
