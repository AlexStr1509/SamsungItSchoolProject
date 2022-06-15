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
public class ScreenEuropa implements Screen {
    final MyGame game;
    final ScreenGame screenGame;
    public Texture imgRamka, imgBack, imgGoRight, imgGoLeft, imgSearch, imgInfo;
    public  RealButton btnBack, btnGoRight, btnGoLeft, btnSearch, btnInfo;
    public BitmapFont font;
    public TextMaker tittleEuropa, tittleEuropaEquarorialDiameter, tittleEuropaMass, tittleEuropaDistFromPlanet, tittleEuropaRotationPeriod;
    public TextMaker tittleEuropaOrbitRotation, tittleEuropaSurfaceGravity, tittleEuropaSurfaceTemperature;
    boolean isInfo = false;
    ScreenEuropa(MyGame game, ScreenGame screenGame){
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
        btnSearch = new RealButton(SCR_WIDTH-150f, 500f,100f);
        btnInfo = new RealButton(SCR_WIDTH-150f, 650f,100f);
        tittleEuropa = new TextMaker("EUROPA", font, SCR_WIDTH/5f, SCR_HEIGHT-50f);
        tittleEuropaEquarorialDiameter = new TextMaker("equatorial diameter       3122 km", font, 30f, tittleEuropa.y-100f);
        tittleEuropaMass = new TextMaker("mass                   4.8*10^22 kg", font, 30f, tittleEuropaEquarorialDiameter.y-70f);
        tittleEuropaDistFromPlanet = new TextMaker("dist.from planet      671148 m km", font, 30f, tittleEuropaMass.y-70f);
        tittleEuropaRotationPeriod = new TextMaker("rotation period             3.6 days", font, 30f, tittleEuropaDistFromPlanet.y-70f);
        tittleEuropaOrbitRotation = new TextMaker("planet orbit rotation      3.6 days", font, 30f, tittleEuropaRotationPeriod.y-70f);
        tittleEuropaSurfaceGravity = new TextMaker("surface gravity         1.31 m/s^2", font, 30f, tittleEuropaOrbitRotation.y-70f);
        tittleEuropaSurfaceTemperature = new TextMaker("surface temperature          102K", font, 30f, tittleEuropaSurfaceGravity.y-70f);
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
        screenGame.modelBatch.render(screenGame.europa);
        screenGame.modelBatch.end();
        if(Gdx.input.justTouched()) {
            game.touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            game.orthographicCamera.unproject(game.touch);
            if(btnBack.hit(game.touch.x, game.touch.y)){
                game.setScreen(game.screenJupiter);
                screenGame.camera.position.set(1f, 50f, 80f);
                screenGame.camera.lookAt(0f, 0f, 0f);
                game.screenJupiter.isInfo = false;
            }
            if(btnGoRight.hit(game.touch.x, game.touch.y)){
                game.setScreen(game.screenIo);
                isInfo = false;
                screenGame.camera.position.set(1f, 50f, 80f);
                screenGame.camera.lookAt(0f, 0f, 0f);
            }
            if (btnGoLeft.hit(game.touch.x, game.touch.y)){
                game.setScreen(game.screenCallisto);
                isInfo=false;
                screenGame.camera.position.set(1f, 50f, 80f);
                screenGame.camera.lookAt(0f, 0f, 0f);
                game.screenCallisto.isInfo=false;
            }
            if(btnSearch.hit(game.touch.x, game.touch.y)){
                screenGame.camera.position.set(-83f,0f,6f);
                screenGame.camera.lookAt(-80f,0f,9f);
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
        game.batch.draw(imgGoLeft, btnGoLeft.x, btnGoLeft.y);
        game.batch.draw(imgSearch, btnSearch.x, btnSearch.y);
        game.batch.draw(imgInfo, btnInfo.x, btnInfo.y);
        if(isInfo) {
            font.draw(game.batch, tittleEuropa.text, tittleEuropa.x, tittleEuropa.y);
            font.draw(game.batch, tittleEuropaEquarorialDiameter.text, tittleEuropaEquarorialDiameter.x, tittleEuropaEquarorialDiameter.y);
            font.draw(game.batch, tittleEuropaMass.text, tittleEuropaMass.x, tittleEuropaMass.y);
            font.draw(game.batch, tittleEuropaDistFromPlanet.text, tittleEuropaDistFromPlanet.x, tittleEuropaDistFromPlanet.y);
            font.draw(game.batch, tittleEuropaRotationPeriod.text, tittleEuropaRotationPeriod.x, tittleEuropaRotationPeriod.y);
            font.draw(game.batch, tittleEuropaOrbitRotation.text, tittleEuropaOrbitRotation.x, tittleEuropaOrbitRotation.y);
            font.draw(game.batch, tittleEuropaSurfaceGravity.text, tittleEuropaSurfaceGravity.x, tittleEuropaSurfaceGravity.y);
            font.draw(game.batch, tittleEuropaSurfaceTemperature.text, tittleEuropaSurfaceTemperature.x, tittleEuropaSurfaceTemperature.y);
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
