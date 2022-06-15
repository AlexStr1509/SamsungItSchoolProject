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
public class ScreenMoon implements Screen {
    final MyGame game;
    final ScreenGame screenGame;
    public Texture imgRamka, imgBack, imgSearch, imgInfo;
    public RealButton btnBack, btnSearch, btnInfo;
    public BitmapFont font;
    public TextMaker tittleMoon, tittleMoonEquarorialDiameter, tittleMoonMass, tittleMoonDistFromPlanet, tittleMoonRotationPeriod;
    public TextMaker tittleMoonOrbitRotation, tittleMoonSurfaceGravity, tittleMoonSurfaceTemperature;
    boolean isInfo = false;
    ScreenMoon(MyGame game, ScreenGame screenGame){
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
        tittleMoon = new TextMaker("MOON", font, SCR_WIDTH/5f, SCR_HEIGHT-50f);
        tittleMoonEquarorialDiameter = new TextMaker("equatorial diameter       3476 km", font, 30f, tittleMoon.y-100f);
        tittleMoonMass = new TextMaker("mass                   7.3*10^22 kg", font, 30f, tittleMoonEquarorialDiameter.y-70f);
        tittleMoonDistFromPlanet = new TextMaker("dist.from planet         384834 km", font, 30f, tittleMoonMass.y-70f);
        tittleMoonRotationPeriod = new TextMaker("rotation period             27 days", font, 30f, tittleMoonDistFromPlanet.y-70f);
        tittleMoonOrbitRotation = new TextMaker("planet orbit rotation     27 days", font, 30f, tittleMoonRotationPeriod.y-70f);
        tittleMoonSurfaceGravity = new TextMaker("surface gravity         1.62 m/s^2", font, 30f, tittleMoonOrbitRotation.y-70f);
        tittleMoonSurfaceTemperature = new TextMaker("surface temperature          250K", font, 30f, tittleMoonSurfaceGravity.y-70f);
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
        screenGame.modelBatch.render(screenGame.earth);
        screenGame.modelBatch.render(screenGame.moon);
        screenGame.modelBatch.end();
        if(Gdx.input.justTouched()) {
            game.touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            game.orthographicCamera.unproject(game.touch);
            if(btnBack.hit(game.touch.x, game.touch.y)){
                game.setScreen(game.screenEarth);
                screenGame.camera.position.set(1f, 50f, 80f);
                screenGame.camera.lookAt(0f, 0f, 0f);
                isInfo = false;
                game.screenEarth.isInfo = false;
            }
            if(btnSearch.hit(game.touch.x, game.touch.y)){
                screenGame.camera.position.set(36f, 0f, -16.5f);
                screenGame.camera.lookAt(40f, 0f,-12.5f);
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
            font.draw(game.batch, tittleMoon.text, tittleMoon.x, tittleMoon.y);
            font.draw(game.batch, tittleMoonEquarorialDiameter.text, tittleMoonEquarorialDiameter.x, tittleMoonEquarorialDiameter.y);
            font.draw(game.batch, tittleMoonMass.text, tittleMoonMass.x, tittleMoonMass.y);
            font.draw(game.batch, tittleMoonDistFromPlanet.text, tittleMoonDistFromPlanet.x, tittleMoonDistFromPlanet.y);
            font.draw(game.batch, tittleMoonRotationPeriod.text, tittleMoonRotationPeriod.x, tittleMoonRotationPeriod.y);
            font.draw(game.batch, tittleMoonOrbitRotation.text, tittleMoonOrbitRotation.x, tittleMoonOrbitRotation.y);
            font.draw(game.batch, tittleMoonSurfaceGravity.text, tittleMoonSurfaceGravity.x, tittleMoonSurfaceGravity.y);
            font.draw(game.batch, tittleMoonSurfaceTemperature.text, tittleMoonSurfaceTemperature.x, tittleMoonSurfaceTemperature.y);
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