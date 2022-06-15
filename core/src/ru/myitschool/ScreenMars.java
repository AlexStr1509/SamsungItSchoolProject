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
public class ScreenMars implements Screen {
    final MyGame game;
    final ScreenGame screenGame;
    public Texture imgRamka, imgBack, imgCheck, imgSearch, imgInfo;
    public  RealButton btnBack, btnCheckSattelites, btnSearch, btnInfo;
    public BitmapFont font;
    public TextMaker tittleMars, tittleMarsEquarorialDiameter, tittleMarsMass, tittleMarsDistFromSun, tittleMarsRotationPeriod;
    public TextMaker tittleMarsSolarOrbitRotation, tittleMarsSurfaceGravity, tittleMarsSurfaceTemperature, tittleMarsSummerTemperature, tittleMarsWinterTemperature, tittleMarsSatellites;
    public boolean isInfo = false;
    ScreenMars(MyGame game, ScreenGame screenGame){
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
        imgInfo =new Texture("imgInfo.png");
        btnBack = new RealButton(SCR_WIDTH-150f, 50f, 100f);
        btnCheckSattelites = new RealButton(SCR_WIDTH/5f, 100f, 100f);
        btnSearch = new RealButton(SCR_WIDTH-150f, 200f, 100f);
        btnInfo = new RealButton(SCR_WIDTH-150f, 350f, 100f);
        tittleMars = new TextMaker("MARS", font, SCR_WIDTH/4.75f, SCR_HEIGHT-50f);
        tittleMarsEquarorialDiameter = new TextMaker("equatorial diameter       6792 km", font, 30f, tittleMars.y-100f);
        tittleMarsMass = new TextMaker("mass                   6.4*10^23 kg", font, 30f, tittleMarsEquarorialDiameter.y-70f);
        tittleMarsDistFromSun = new TextMaker("dist.from sun            228 m km", font, 30f, tittleMarsMass.y-70f);
        tittleMarsRotationPeriod = new TextMaker("rotation period           1.03 days", font, 30f, tittleMarsDistFromSun.y-70f);
        tittleMarsSolarOrbitRotation = new TextMaker("solar orbit rotation     1.88 year", font, 30f, tittleMarsRotationPeriod.y-70f);
        tittleMarsSurfaceGravity = new TextMaker("surface gravity         3.7 m/s^2", font, 30f, tittleMarsSolarOrbitRotation.y-70f);
        tittleMarsSurfaceTemperature = new TextMaker("surface temperature         210K", font, 30f, tittleMarsSurfaceGravity.y-70f);
        tittleMarsSummerTemperature = new TextMaker("summer temperature         290K", font, 30f, tittleMarsSurfaceTemperature.y-70f);
        tittleMarsWinterTemperature = new TextMaker("night temperature             133K", font, 30f, tittleMarsSummerTemperature.y-70f);
        tittleMarsSatellites = new TextMaker("Mars`s Satellites", font, SCR_WIDTH/9f, 250f);
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
        screenGame.modelBatch.render(screenGame.phobos);
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
                game.setScreen(game.screenDeimos);
                screenGame.camera.position.set(1f, 50f, 80f);
                screenGame.camera.lookAt(0f, 0f, 0f);
            }
            if(btnSearch.hit(game.touch.x, game.touch.y)){
                screenGame.camera.position.set(0f, 0f,36f);
                screenGame.camera.lookAt(0f,0f,40f);
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
            font.draw(game.batch, tittleMars.text, tittleMars.x, tittleMars.y);
            font.draw(game.batch, tittleMarsEquarorialDiameter.text, tittleMarsEquarorialDiameter.x, tittleMarsEquarorialDiameter.y);
            font.draw(game.batch, tittleMarsMass.text, tittleMarsMass.x, tittleMarsMass.y);
            font.draw(game.batch, tittleMarsDistFromSun.text, tittleMarsDistFromSun.x, tittleMarsDistFromSun.y);
            font.draw(game.batch, tittleMarsRotationPeriod.text, tittleMarsRotationPeriod.x, tittleMarsRotationPeriod.y);
            font.draw(game.batch, tittleMarsSolarOrbitRotation.text, tittleMarsSolarOrbitRotation.x, tittleMarsSolarOrbitRotation.y);
            font.draw(game.batch, tittleMarsSurfaceGravity.text, tittleMarsSurfaceGravity.x, tittleMarsSurfaceGravity.y);
            font.draw(game.batch, tittleMarsSurfaceTemperature.text, tittleMarsSurfaceTemperature.x, tittleMarsSurfaceTemperature.y);
            font.draw(game.batch, tittleMarsSummerTemperature.text, tittleMarsSummerTemperature.x, tittleMarsSummerTemperature.y);
            font.draw(game.batch, tittleMarsWinterTemperature.text, tittleMarsWinterTemperature.x, tittleMarsWinterTemperature.y);
            font.draw(game.batch, tittleMarsSatellites.text, tittleMarsSatellites.x, tittleMarsSatellites.y);
        }
        game.batch.end();
    }
    @Override
    public void resize(int width, int height) {
    }
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

