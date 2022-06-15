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
public class ScreenCharon implements Screen {
    final MyGame game;
    final ScreenGame screenGame;
    public Texture imgRamka, imgBack, imgSearch, imgInfo;
    public  RealButton btnBack, btnSearch, btnInfo;
    public BitmapFont font;
    public TextMaker tittleCharon, tittleCharonEquarorialDiameter, tittleCharonMass, tittleCharonDistFromPlanet, tittleCharonRotationPeriod;
    public TextMaker tittleCharonOrbitRotation, tittleCharonSurfaceGravity, tittleCharonSurfaceTemperature;
    boolean isInfo = false;
    ScreenCharon(MyGame game, ScreenGame screenGame){
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
        btnSearch = new RealButton(SCR_WIDTH-150f, 200f, 100f);
        btnInfo = new RealButton(SCR_WIDTH-150f, 350f, 100f);
        tittleCharon = new TextMaker("CHARON", font, SCR_WIDTH/5f, SCR_HEIGHT-50f);
        tittleCharonEquarorialDiameter = new TextMaker("equatorial diameter       1212 km", font, 30f, tittleCharon.y-100f);
        tittleCharonMass = new TextMaker("mass                  1.52*10^21 kg", font, 30f, tittleCharonEquarorialDiameter.y-70f);
        tittleCharonDistFromPlanet = new TextMaker("dist.from planet          20000 km", font, 30f, tittleCharonMass.y-70f);
        tittleCharonRotationPeriod = new TextMaker("rotation period             27 days", font, 30f, tittleCharonDistFromPlanet.y-70f);
        tittleCharonOrbitRotation = new TextMaker("planet orbit rotation       27 days", font, 30f, tittleCharonRotationPeriod.y-70f);
        tittleCharonSurfaceGravity = new TextMaker("surface gravity        0.278 m/s^2", font, 30f, tittleCharonOrbitRotation.y-70f);
        tittleCharonSurfaceTemperature = new TextMaker("surface temperature           53K", font, 30f, tittleCharonSurfaceGravity.y-70f);
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
        screenGame.modelBatch.render(screenGame.pluton);
        screenGame.modelBatch.render(screenGame.haron);

        screenGame.modelBatch.end();
        if(Gdx.input.justTouched()) {
            game.touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            game.orthographicCamera.unproject(game.touch);
            if(btnBack.hit(game.touch.x, game.touch.y)){
                game.setScreen(game.screenPluto);
                screenGame.camera.position.set(1f, 50f, 80f);
                screenGame.camera.lookAt(0f, 0f, 0f);
                isInfo = false;
                game.screenPluto.isInfo=false;
            }
            if(btnSearch.hit(game.touch.x, game.touch.y)){
                screenGame.camera.position.set(149f, 0f,1f);
                screenGame.camera.lookAt(150f,0f,2f);
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
            font.draw(game.batch, tittleCharon.text, tittleCharon.x, tittleCharon.y);
            font.draw(game.batch, tittleCharonEquarorialDiameter.text, tittleCharonEquarorialDiameter.x, tittleCharonEquarorialDiameter.y);
            font.draw(game.batch, tittleCharonMass.text, tittleCharonMass.x, tittleCharonMass.y);
            font.draw(game.batch, tittleCharonDistFromPlanet.text, tittleCharonDistFromPlanet.x, tittleCharonDistFromPlanet.y);
            font.draw(game.batch, tittleCharonRotationPeriod.text, tittleCharonRotationPeriod.x, tittleCharonRotationPeriod.y);
            font.draw(game.batch, tittleCharonOrbitRotation.text, tittleCharonOrbitRotation.x, tittleCharonOrbitRotation.y);
            font.draw(game.batch, tittleCharonSurfaceGravity.text, tittleCharonSurfaceGravity.x, tittleCharonSurfaceGravity.y);
            font.draw(game.batch, tittleCharonSurfaceTemperature.text, tittleCharonSurfaceTemperature.x, tittleCharonSurfaceTemperature.y);
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
