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
public class ScreenUranus implements Screen {
    final MyGame game;
    final ScreenGame screenGame;
    public Texture imgRamka, imgBack, imgCheck, imgSearch, imgInfo;
    public  RealButton btnBack, btnCheckSattelites, btnSearch, btnInfo;
    public BitmapFont font;
    public TextMaker tittleUranus, tittleUranusEquarorialDiameter, tittleUranusMass, tittleUranusDistFromSun, tittleUranusRotationPeriod;
    public TextMaker tittleUranusSolarOrbitRotation, tittleUranusSurfaceGravity, tittleUranusSurfaceTemperature, tittleUranusSatellites;
    boolean isInfo = false;
    ScreenUranus(MyGame game, ScreenGame screenGame){
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
        tittleUranus = new TextMaker("URANUS", font, SCR_WIDTH/5f, SCR_HEIGHT-50f);
        tittleUranusEquarorialDiameter = new TextMaker("equatorial diameter      51118 km", font, 30f, tittleUranus.y-100f);
        tittleUranusMass = new TextMaker("mass                   8.7*10^25 kg", font, 30f, tittleUranusEquarorialDiameter.y-70f);
        tittleUranusDistFromSun = new TextMaker("dist.from sun              19.2 AU", font, 30f, tittleUranusMass.y-70f);
        tittleUranusRotationPeriod = new TextMaker("rotation period          17 h 14 m", font, 30f, tittleUranusDistFromSun.y-70f);
        tittleUranusSolarOrbitRotation = new TextMaker("solar orbit rotation      84 years", font, 30f, tittleUranusRotationPeriod.y-70f);
        tittleUranusSurfaceGravity = new TextMaker("surface gravity         8.7 m/s^2", font, 30f, tittleUranusSolarOrbitRotation.y-70f);
        tittleUranusSurfaceTemperature = new TextMaker("surface temperature          63K", font, 30f, tittleUranusSurfaceGravity.y-70f);
        tittleUranusSatellites = new TextMaker("Uranus`s Satellites", font, SCR_WIDTH/10f, 250f);
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
        screenGame.modelBatch.render(screenGame.titanya);
        screenGame.modelBatch.render(screenGame.oberon);
        screenGame.modelBatch.render(screenGame.umbriel);
        screenGame.modelBatch.render(screenGame.ariel);
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
                game.setScreen(game.screenTitania);
                screenGame.camera.position.set(1f, 50f, 80f);
                screenGame.camera.lookAt(0f, 0f, 0f);
                isInfo = false;
            }
            if(btnSearch.hit(game.touch.x, game.touch.y)){
                screenGame.camera.position.set(40f,-11.5f,-100f);
                screenGame.camera.lookAt(50f,-1.5f,-90f);
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
            font.draw(game.batch, tittleUranus.text, tittleUranus.x, tittleUranus.y);
            font.draw(game.batch, tittleUranusEquarorialDiameter.text, tittleUranusEquarorialDiameter.x, tittleUranusEquarorialDiameter.y);
            font.draw(game.batch, tittleUranusMass.text, tittleUranusMass.x, tittleUranusMass.y);
            font.draw(game.batch, tittleUranusDistFromSun.text, tittleUranusDistFromSun.x, tittleUranusDistFromSun.y);
            font.draw(game.batch, tittleUranusRotationPeriod.text, tittleUranusRotationPeriod.x, tittleUranusRotationPeriod.y);
            font.draw(game.batch, tittleUranusSolarOrbitRotation.text, tittleUranusSolarOrbitRotation.x, tittleUranusSolarOrbitRotation.y);
            font.draw(game.batch, tittleUranusSurfaceGravity.text, tittleUranusSurfaceGravity.x, tittleUranusSurfaceGravity.y);
            font.draw(game.batch, tittleUranusSurfaceTemperature.text, tittleUranusSurfaceTemperature.x, tittleUranusSurfaceTemperature.y);
            font.draw(game.batch, tittleUranusSatellites.text, tittleUranusSatellites.x, tittleUranusSatellites.y);
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