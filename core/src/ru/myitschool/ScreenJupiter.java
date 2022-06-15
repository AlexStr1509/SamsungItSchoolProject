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
public class ScreenJupiter implements Screen {
    final MyGame game;
    final ScreenGame screenGame;
    public Texture imgRamka, imgBack, imgCheck, imgSearch, imgInfo;
    public  RealButton btnBack, btnCheckSattelites, btnSearch, btnInfo;
    public BitmapFont font;
    public TextMaker tittleJupiter, tittleJupiterEquarorialDiameter, tittleJupiterMass, tittleJupiterDistFromSun, tittleJupiterRotationPeriod;
    public TextMaker tittleJupiterSolarOrbitRotation, tittleJupiterSurfaceGravity, tittleJupiterSurfaceTemperature, tittleJupiterSatellites;
    boolean isInfo = false;
    ScreenJupiter(MyGame game, ScreenGame screenGame){
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
        btnSearch = new RealButton(SCR_WIDTH-150f, 200f,100f);
        btnInfo = new RealButton(SCR_WIDTH-150f, 350f, 100f);
        tittleJupiter = new TextMaker("JUPITER", font, SCR_WIDTH/5f, SCR_HEIGHT-50f);
        tittleJupiterEquarorialDiameter = new TextMaker("equatorial diameter     142984 km", font, 30f, tittleJupiter.y-100f);
        tittleJupiterMass = new TextMaker("mass                   1.9*10^27 kg", font, 30f, tittleJupiterEquarorialDiameter.y-70f);
        tittleJupiterDistFromSun = new TextMaker("dist.from sun            779 m km", font, 30f, tittleJupiterMass.y-70f);
        tittleJupiterRotationPeriod = new TextMaker("rotation period           9 h 55 m", font, 30f, tittleJupiterDistFromSun.y-70f);
        tittleJupiterSolarOrbitRotation = new TextMaker("solar orbit rotation     11.9 year", font, 30f, tittleJupiterRotationPeriod.y-70f);
        tittleJupiterSurfaceGravity = new TextMaker("surface gravity          25 m/s^2", font, 30f, tittleJupiterSolarOrbitRotation.y-70f);
        tittleJupiterSurfaceTemperature = new TextMaker("surface temperature         153K", font, 30f, tittleJupiterSurfaceGravity.y-70f);
        tittleJupiterSatellites = new TextMaker("Jupiter`s Satellites", font, SCR_WIDTH/10f, 250f);
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
        screenGame.modelBatch.render(screenGame.ganymede);
        screenGame.modelBatch.render(screenGame.calisto);
        screenGame.modelBatch.render(screenGame.europa);
        screenGame.modelBatch.render(screenGame.io);
        screenGame.modelBatch.end();
        if(Gdx.input.justTouched()) {
            game.touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            game.orthographicCamera.unproject(game.touch);
            if(btnBack.hit(game.touch.x, game.touch.y)){
                game.setScreen(game.screenSearch);
                isInfo=false;
                screenGame.camera.position.set(1f, 50f, 80f);
                screenGame.camera.lookAt(0f, 0f, 0f);
            }
            if(btnCheckSattelites.hit(game.touch.x,game.touch.y)){
                game.setScreen(game.screenGanymede);
                screenGame.camera.position.set(1f, 50f, 80f);
                screenGame.camera.lookAt(0f, 0f, 0f);
                game.screenGanymede.isInfo = false;

            }
            if(btnSearch.hit(game.touch.x, game.touch.y)){
                screenGame.camera.position.set(-90f,0,5f);
                screenGame.camera.lookAt(-80f, 0f,15f);
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
            game.batch.draw(imgCheck, btnCheckSattelites.x, btnCheckSattelites.y);
            font.draw(game.batch, tittleJupiter.text, tittleJupiter.x, tittleJupiter.y);
            font.draw(game.batch, tittleJupiterEquarorialDiameter.text, tittleJupiterEquarorialDiameter.x, tittleJupiterEquarorialDiameter.y);
            font.draw(game.batch, tittleJupiterMass.text, tittleJupiterMass.x, tittleJupiterMass.y);
            font.draw(game.batch, tittleJupiterDistFromSun.text, tittleJupiterDistFromSun.x, tittleJupiterDistFromSun.y);
            font.draw(game.batch, tittleJupiterRotationPeriod.text, tittleJupiterRotationPeriod.x, tittleJupiterRotationPeriod.y);
            font.draw(game.batch, tittleJupiterSolarOrbitRotation.text, tittleJupiterSolarOrbitRotation.x, tittleJupiterSolarOrbitRotation.y);
            font.draw(game.batch, tittleJupiterSurfaceGravity.text, tittleJupiterSurfaceGravity.x, tittleJupiterSurfaceGravity.y);
            font.draw(game.batch, tittleJupiterSurfaceTemperature.text, tittleJupiterSurfaceTemperature.x, tittleJupiterSurfaceTemperature.y);
            font.draw(game.batch, tittleJupiterSatellites.text, tittleJupiterSatellites.x, tittleJupiterSatellites.y);
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
