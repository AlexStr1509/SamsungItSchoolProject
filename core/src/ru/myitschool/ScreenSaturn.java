package ru.myitschool;
import static ru.myitschool.MyGame.SCR_HEIGHT;
import static ru.myitschool.MyGame.SCR_WIDTH;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
public class ScreenSaturn implements Screen {
    final MyGame game;
    final ScreenGame screenGame;
    public Texture imgRamka, imgBack, imgCheck, imgSearch, imgInfo;
    public  RealButton btnBack, btnCheckSattelites, btnSearch, btnInfo;
    public BitmapFont font;
    public TextMaker tittleSaturn, tittleSaturnEquarorialDiameter, tittleSaturnMass, tittleSaturnDistFromSun, tittleSaturnRotationPeriod;
    public TextMaker tittleSaturnSolarOrbitRotation, tittleSaturnSurfaceGravity, tittleSaturnSurfaceTemperature, tittleSaturnSatellites;
    boolean isInfo = false;
    ScreenSaturn(MyGame game, ScreenGame screenGame){
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
        btnCheckSattelites = new RealButton(SCR_WIDTH/4.5f, 100f, 100f);
        btnSearch = new RealButton(SCR_WIDTH-150f, 200f, 100f);
        btnInfo = new RealButton(SCR_WIDTH-150f, 350f, 100f);
        tittleSaturn = new TextMaker("SATURN", font, SCR_WIDTH/5f, SCR_HEIGHT-50f);
        tittleSaturnEquarorialDiameter = new TextMaker("equatorial diameter     120536 km", font, 30f, tittleSaturn.y-100f);
        tittleSaturnMass = new TextMaker("mass                   5.7*10^26 kg", font, 30f, tittleSaturnEquarorialDiameter.y-70f);
        tittleSaturnDistFromSun = new TextMaker("dist.from sun              9.57 AU", font, 30f, tittleSaturnMass.y-70f);
        tittleSaturnRotationPeriod = new TextMaker("rotation period          10 h 39 m", font, 30f, tittleSaturnDistFromSun.y-70f);
        tittleSaturnSolarOrbitRotation = new TextMaker("solar orbit rotation      29 years", font, 30f, tittleSaturnRotationPeriod.y-70f);
        tittleSaturnSurfaceGravity = new TextMaker("surface gravity        10.4 m/s^2", font, 30f, tittleSaturnSolarOrbitRotation.y-70f);
        tittleSaturnSurfaceTemperature = new TextMaker("surface temperature         132K", font, 30f, tittleSaturnSurfaceGravity.y-70f);
        tittleSaturnSatellites = new TextMaker("Saturn`s Satellites", font, SCR_WIDTH/10f, 250f);
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
        screenGame.modelBatch.render(screenGame.rhea);
        screenGame.modelBatch.render(screenGame.titan);
        screenGame.modelBatch.render(screenGame.tefeya);
        screenGame.modelBatch.render(screenGame.encelad);
        screenGame.modelBatch.render(screenGame.yapet);
        screenGame.modelBatch.render(screenGame.diona);
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
            if(btnCheckSattelites.hit(game.touch.x,game.touch.y)){
                game.setScreen(game.screenTitan);
                screenGame.camera.position.set(1f, 50f, 80f);
                screenGame.camera.lookAt(0f, 0f, 0f);
            }
            if(btnSearch.hit(game.touch.x, game.touch.y)){
                screenGame.camera.position.set(80f,0f,0f);
                screenGame.camera.lookAt(95f,0f,0f);
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
        game.batch.draw(imgInfo,btnInfo.x, btnInfo.y);
        if(isInfo) {
            game.batch.draw(imgCheck, btnCheckSattelites.x, btnCheckSattelites.y);
            font.draw(game.batch, tittleSaturn.text, tittleSaturn.x, tittleSaturn.y);
            font.draw(game.batch, tittleSaturnEquarorialDiameter.text, tittleSaturnEquarorialDiameter.x, tittleSaturnEquarorialDiameter.y);
            font.draw(game.batch, tittleSaturnMass.text, tittleSaturnMass.x, tittleSaturnMass.y);
            font.draw(game.batch, tittleSaturnDistFromSun.text, tittleSaturnDistFromSun.x, tittleSaturnDistFromSun.y);
            font.draw(game.batch, tittleSaturnRotationPeriod.text, tittleSaturnRotationPeriod.x, tittleSaturnRotationPeriod.y);
            font.draw(game.batch, tittleSaturnSolarOrbitRotation.text, tittleSaturnSolarOrbitRotation.x, tittleSaturnSolarOrbitRotation.y);
            font.draw(game.batch, tittleSaturnSurfaceGravity.text, tittleSaturnSurfaceGravity.x, tittleSaturnSurfaceGravity.y);
            font.draw(game.batch, tittleSaturnSurfaceTemperature.text, tittleSaturnSurfaceTemperature.x, tittleSaturnSurfaceTemperature.y);
            font.draw(game.batch, tittleSaturnSatellites.text, tittleSaturnSatellites.x, tittleSaturnSatellites.y);
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
        imgCheck.dispose();
        imgInfo.dispose();
        imgRamka.dispose();
        imgSearch.dispose();
        font.dispose();
    }
}

