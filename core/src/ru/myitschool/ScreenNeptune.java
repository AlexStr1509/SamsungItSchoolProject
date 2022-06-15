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
public class ScreenNeptune implements Screen {
    final MyGame game;
    final ScreenGame screenGame;
    public Texture imgRamka, imgBack, imgCheck, imgSearch, imgInfo;
    public RealButton btnBack, btnCheckSattelite, btnSearch, btnInfo;
    public BitmapFont font;
    public TextMaker tittleNeptune, tittleNeptuneEquarorialDiameter, tittleNeptuneMass, tittleNeptuneDistFromSun, tittleNeptuneRotationPeriod;
    public TextMaker tittleNeptuneSolarOrbitRotation, tittleNeptuneSurfaceGravity, tittleNeptuneSurfaceTemperature, tittleNeptunsMoon;
    boolean isInfo = false;
    ScreenNeptune(MyGame game, ScreenGame screenGame){
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
        btnSearch = new RealButton(SCR_WIDTH-150f, 200f, 100f);
        btnInfo = new RealButton(SCR_WIDTH-150f, 350f, 100f);
        tittleNeptune = new TextMaker("NEPTUNE", font, SCR_WIDTH/5f, SCR_HEIGHT-50f);
        tittleNeptuneEquarorialDiameter = new TextMaker("equatorial diameter      49528 km", font, 30f, tittleNeptune.y-100f);
        tittleNeptuneMass = new TextMaker("mass                  1.0.2*10^26 kg", font, 30f, tittleNeptuneEquarorialDiameter.y-70f);
        tittleNeptuneDistFromSun = new TextMaker("dist.from sun                30.1 AU", font, 30f, tittleNeptuneMass.y-70f);
        tittleNeptuneRotationPeriod = new TextMaker("rotation period            16 h 6 m", font, 30f, tittleNeptuneDistFromSun.y-70f);
        tittleNeptuneSolarOrbitRotation = new TextMaker("solar orbit rotation      165 year", font, 30f, tittleNeptuneRotationPeriod.y-70f);
        tittleNeptuneSurfaceGravity = new TextMaker("surface gravity         11.2 m/s^2", font, 30f, tittleNeptuneSolarOrbitRotation.y-70f);
        tittleNeptuneSurfaceTemperature = new TextMaker("surface temperature           73K", font, 30f, tittleNeptuneSurfaceGravity.y-70f);
        tittleNeptunsMoon = new TextMaker("Neptune`s Satellite", font, SCR_WIDTH/9.5f, 250f);
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
        screenGame.modelBatch.render(screenGame.neptun);
        screenGame.modelBatch.render(screenGame.triton);
        screenGame.modelBatch.end();
        if(Gdx.input.justTouched()) {
            game.touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            game.orthographicCamera.unproject(game.touch);
            if(btnBack.hit(game.touch.x, game.touch.y)){
                game.setScreen(game.screenSearch);
                screenGame.camera.position.set(1f, 50f, 80f);
                screenGame.camera.lookAt(0f, 0f, 0f);
                isInfo=false;
            }
            if(btnCheckSattelite.hit(game.touch.x, game.touch.y)){
                game.setScreen(game.screenTriton);
                screenGame.camera.position.set(1f, 50f, 80f);
                screenGame.camera.lookAt(0f, 0f, 0f);
            }
            if(btnSearch.hit(game.touch.x, game.touch.y)){
                screenGame.camera.position.set(0f,0f,120f);
                screenGame.camera.lookAt(0,0f,130f);
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
            font.draw(game.batch, tittleNeptune.text, tittleNeptune.x, tittleNeptune.y);
            font.draw(game.batch, tittleNeptuneEquarorialDiameter.text, tittleNeptuneEquarorialDiameter.x, tittleNeptuneEquarorialDiameter.y);
            font.draw(game.batch, tittleNeptuneMass.text, tittleNeptuneMass.x, tittleNeptuneMass.y);
            font.draw(game.batch, tittleNeptuneDistFromSun.text, tittleNeptuneDistFromSun.x, tittleNeptuneDistFromSun.y);
            font.draw(game.batch, tittleNeptuneRotationPeriod.text, tittleNeptuneRotationPeriod.x, tittleNeptuneRotationPeriod.y);
            font.draw(game.batch, tittleNeptuneSolarOrbitRotation.text, tittleNeptuneSolarOrbitRotation.x, tittleNeptuneSolarOrbitRotation.y);
            font.draw(game.batch, tittleNeptuneSurfaceGravity.text, tittleNeptuneSurfaceGravity.x, tittleNeptuneSurfaceGravity.y);
            font.draw(game.batch, tittleNeptuneSurfaceTemperature.text, tittleNeptuneSurfaceTemperature.x, tittleNeptuneSurfaceTemperature.y);
            font.draw(game.batch, tittleNeptunsMoon.text, tittleNeptunsMoon.x, tittleNeptunsMoon.y);
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