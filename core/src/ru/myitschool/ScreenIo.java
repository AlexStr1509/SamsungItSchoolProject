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
public class ScreenIo implements Screen {
    final MyGame game;
    final ScreenGame screenGame;
    public Texture imgRamka, imgBack, imgGoLeft, imgSearch, imgInfo;
    public  RealButton btnBack, btnGoLeft, btnSearch, btnInfo;
    public BitmapFont font;
    public TextMaker tittleIo, tittleIoEquarorialDiameter, tittleIoMass, tittleIoDistFromPlanet, tittleIoRotationPeriod;
    public TextMaker tittleIoOrbitRotation, tittleIoSurfaceGravity, tittleIoSurfaceTemperature;
    boolean isInfo = false;
    ScreenIo(MyGame game, ScreenGame screenGame){
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
        imgGoLeft = new Texture("imgGoLeft.png");
        imgSearch = new Texture("imgSearch.png");
        imgInfo = new Texture("imgInfo.png");
        btnBack = new RealButton(SCR_WIDTH-150f, 50f, 100f);
        btnGoLeft = new RealButton(SCR_WIDTH-150f, 200f, 100f);
        btnSearch = new RealButton(SCR_WIDTH-150f, 350f, 100f);
        btnInfo = new RealButton(SCR_WIDTH-150f, 500f, 100f);
        tittleIo = new TextMaker("IO", font, SCR_WIDTH/4f, SCR_HEIGHT-50f);
        tittleIoEquarorialDiameter = new TextMaker("equatorial diameter       3643 km", font, 30f, tittleIo.y-100f);
        tittleIoMass = new TextMaker("mass                  8.9*10^22 kg", font, 30f, tittleIoEquarorialDiameter.y-70f);
        tittleIoDistFromPlanet = new TextMaker("dist.from planet         421807 km", font, 30f, tittleIoMass.y-70f);
        tittleIoRotationPeriod = new TextMaker("rotation period           1.77 days", font, 30f, tittleIoDistFromPlanet.y-70f);
        tittleIoOrbitRotation = new TextMaker("planet orbit rotation    1.77 days", font, 30f, tittleIoRotationPeriod.y-70f);
        tittleIoSurfaceGravity = new TextMaker("surface gravity          1.8 m/s^2", font, 30f, tittleIoOrbitRotation.y-70f);
        tittleIoSurfaceTemperature = new TextMaker("surface temperature          121K", font, 30f, tittleIoSurfaceGravity.y-70f);
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
        screenGame.modelBatch.render(screenGame.io);
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
            if(btnGoLeft.hit(game.touch.x, game.touch.y)){
                game.setScreen(game.screenEuropa);
                isInfo=false;
                screenGame.camera.position.set(1f, 50f, 80f);
                screenGame.camera.lookAt(0f, 0f, 0f);
                game.screenEuropa.isInfo=false;
            }
            if(btnSearch.hit(game.touch.x, game.touch.y)){
                screenGame.camera.position.set(-83f,-9f,6f);
                screenGame.camera.lookAt(-80f,-6f,9f);
            }
            if(btnInfo.hit(game.touch.x, game.touch.y)){
                isInfo=true;
            }
        }
        game.orthographicCamera.update();
        game.batch.setProjectionMatrix(game.orthographicCamera.combined);
        game.batch.begin();
        game.batch.draw(imgBack, btnBack.x, btnBack.y);
        game.batch.draw(imgGoLeft, btnGoLeft.x, btnGoLeft.y);
        game.batch.draw(imgSearch, btnSearch.x, btnSearch.y);
        game.batch.draw(imgInfo,btnInfo.x, btnInfo.y);
        if(isInfo) {
            font.draw(game.batch, tittleIo.text, tittleIo.x, tittleIo.y);
            font.draw(game.batch, tittleIoEquarorialDiameter.text, tittleIoEquarorialDiameter.x, tittleIoEquarorialDiameter.y);
            font.draw(game.batch, tittleIoMass.text, tittleIoMass.x, tittleIoMass.y);
            font.draw(game.batch, tittleIoDistFromPlanet.text, tittleIoDistFromPlanet.x, tittleIoDistFromPlanet.y);
            font.draw(game.batch, tittleIoRotationPeriod.text, tittleIoRotationPeriod.x, tittleIoRotationPeriod.y);
            font.draw(game.batch, tittleIoOrbitRotation.text, tittleIoOrbitRotation.x, tittleIoOrbitRotation.y);
            font.draw(game.batch, tittleIoSurfaceGravity.text, tittleIoSurfaceGravity.x, tittleIoSurfaceGravity.y);
            font.draw(game.batch, tittleIoSurfaceTemperature.text, tittleIoSurfaceTemperature.x, tittleIoSurfaceTemperature.y);
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
        imgInfo.dispose();
        imgRamka.dispose();
        imgSearch.dispose();
        font.dispose();
    }
}
