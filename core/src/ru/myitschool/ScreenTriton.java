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
public class ScreenTriton implements Screen {
    final MyGame game;
    final ScreenGame screenGame;
    public Texture imgRamka, imgBack, imgSearch, imgInfo;
    public  RealButton btnBack, btnSearch, btnInfo;
    public BitmapFont font;
    public TextMaker tittleTriton, tittleTritonEquarorialDiameter, tittleTritonMass, tittleTritonDistFromPlanet, tittleTritonRotationPeriod;
    public TextMaker tittleTritonOrbitRotation, tittleTritonSurfaceGravity, tittleTritonSurfaceTemperature;
    boolean isInfo = false;
    ScreenTriton(MyGame game, ScreenGame screenGame){
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
        tittleTriton = new TextMaker("TRITON", font, SCR_WIDTH/4.8f, SCR_HEIGHT-50f);
        tittleTritonEquarorialDiameter = new TextMaker("equatorial diameter       2707 km", font, 30f, tittleTriton.y-100f);
        tittleTritonMass = new TextMaker("mass                   2.1*10^22 kg", font, 30f, tittleTritonEquarorialDiameter.y-70f);
        tittleTritonDistFromPlanet = new TextMaker("dist.from planet         355771 km", font, 30f, tittleTritonMass.y-70f);
        tittleTritonRotationPeriod = new TextMaker("rotation period             5.9 days", font, 30f, tittleTritonDistFromPlanet.y-70f);
        tittleTritonOrbitRotation = new TextMaker("planet orbit rotation      5.9 days", font, 30f, tittleTritonRotationPeriod.y-70f);
        tittleTritonSurfaceGravity = new TextMaker("surface gravity         0.78 m/s^2", font, 30f, tittleTritonOrbitRotation.y-70f);
        tittleTritonSurfaceTemperature = new TextMaker("surface temperature           68K", font, 30f, tittleTritonSurfaceGravity.y-70f);
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
                game.setScreen(game.screenNeptune);
                screenGame.camera.position.set(1f, 50f, 80f);
                screenGame.camera.lookAt(0f, 0f, 0f);
                game.screenNeptune.isInfo = false;
                isInfo = false;
            }
            if(btnSearch.hit(game.touch.x, game.touch.y)){
                screenGame.camera.position.set(0, 0f,120f);
                screenGame.camera.lookAt(0f, 0f,124f);
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
            font.draw(game.batch, tittleTriton.text, tittleTriton.x, tittleTriton.y);
            font.draw(game.batch, tittleTritonEquarorialDiameter.text, tittleTritonEquarorialDiameter.x, tittleTritonEquarorialDiameter.y);
            font.draw(game.batch, tittleTritonMass.text, tittleTritonMass.x, tittleTritonMass.y);
            font.draw(game.batch, tittleTritonDistFromPlanet.text, tittleTritonDistFromPlanet.x, tittleTritonDistFromPlanet.y);
            font.draw(game.batch, tittleTritonRotationPeriod.text, tittleTritonRotationPeriod.x, tittleTritonRotationPeriod.y);
            font.draw(game.batch, tittleTritonOrbitRotation.text, tittleTritonOrbitRotation.x, tittleTritonOrbitRotation.y);
            font.draw(game.batch, tittleTritonSurfaceGravity.text, tittleTritonSurfaceGravity.x, tittleTritonSurfaceGravity.y);
            font.draw(game.batch, tittleTritonSurfaceTemperature.text, tittleTritonSurfaceTemperature.x, tittleTritonSurfaceTemperature.y);
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
        imgInfo.dispose();
        imgRamka.dispose();
        imgSearch.dispose();
        font.dispose();
    }
}
