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

public class ScreenOberon implements Screen {
    final MyGame game;
    final ScreenGame screenGame;
    public Texture imgRamka, imgBack, imgGoRight, imgGoLeft, imgSearch, imgInfo;
    public  RealButton btnBack, btnGoRight, btnGoLeft, btnSearch, btnInfo;
    public BitmapFont font;
    public TextMaker tittleOberon, tittleOberonEquarorialDiameter, tittleOberonMass, tittleOberonDistFromPlanet, tittleOberonRotationPeriod;
    public TextMaker tittleOberonOrbitRotation, tittleOberonSurfaceGravity, tittleOberonSurfaceTemperature;
    boolean isInfo = false;
    ScreenOberon(MyGame game, ScreenGame screenGame){
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
        btnSearch = new RealButton(SCR_WIDTH-150f, 500f, 100f);
        btnInfo = new RealButton(SCR_WIDTH-150f, 650f, 100f);
        tittleOberon = new TextMaker("OBERON", font, SCR_WIDTH/5f, SCR_HEIGHT-50f);
        tittleOberonEquarorialDiameter = new TextMaker("equatorial diameter       3122 km", font, 30f, tittleOberon.y-100f);
        tittleOberonMass = new TextMaker("mass                   4.8*10^22 kg", font, 30f, tittleOberonEquarorialDiameter.y-70f);
        tittleOberonDistFromPlanet = new TextMaker("dist.from planet        671148 km", font, 30f, tittleOberonMass.y-70f);
        tittleOberonRotationPeriod = new TextMaker("rotation period             3.6 days", font, 30f, tittleOberonDistFromPlanet.y-70f);
        tittleOberonOrbitRotation = new TextMaker("planet orbit rotation      3.6 days", font, 30f, tittleOberonRotationPeriod.y-70f);
        tittleOberonSurfaceGravity = new TextMaker("surface gravity         1.31 m/s^2", font, 30f, tittleOberonOrbitRotation.y-70f);
        tittleOberonSurfaceTemperature = new TextMaker("surface temperature          102K", font, 30f, tittleOberonSurfaceGravity.y-70f);
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
        screenGame.modelBatch.render(screenGame.oberon);
        screenGame.modelBatch.end();
        if(Gdx.input.justTouched()) {
            game.touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            game.orthographicCamera.unproject(game.touch);
            if(btnBack.hit(game.touch.x, game.touch.y)){
                game.setScreen(game.screenUranus);
                screenGame.camera.position.set(1f, 50f, 80f);
                screenGame.camera.lookAt(0f, 0f, 0f);
                game.screenUranus.isInfo = false;
                isInfo = false;
            }
            if(btnGoRight.hit(game.touch.x, game.touch.y)){
                game.setScreen(game.screenUmbriel);
                screenGame.camera.position.set(1f, 50f, 80f);
                screenGame.camera.lookAt(0f, 0f, 0f);
                isInfo = false;
                game.screenUmbriel.isInfo =false;
            }
            if (btnGoLeft.hit(game.touch.x, game.touch.y)){
                game.setScreen(game.screenTitania);
                screenGame.camera.position.set(1f, 50f, 80f);
                screenGame.camera.lookAt(0f, 0f, 0f);
                isInfo = false;
                game.screenTitania.isInfo =false;
            }
            if(btnSearch.hit(game.touch.x, game.touch.y)){
                screenGame.camera.position.set(47f,0f,-99f);
                screenGame.camera.lookAt(50f,0f,-96f);
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
        game.batch.draw(imgInfo,btnInfo.x, btnInfo.y);
        if (isInfo) {
            font.draw(game.batch, tittleOberon.text, tittleOberon.x, tittleOberon.y);
            font.draw(game.batch, tittleOberonEquarorialDiameter.text, tittleOberonEquarorialDiameter.x, tittleOberonEquarorialDiameter.y);
            font.draw(game.batch, tittleOberonMass.text, tittleOberonMass.x, tittleOberonMass.y);
            font.draw(game.batch, tittleOberonDistFromPlanet.text, tittleOberonDistFromPlanet.x, tittleOberonDistFromPlanet.y);
            font.draw(game.batch, tittleOberonRotationPeriod.text, tittleOberonRotationPeriod.x, tittleOberonRotationPeriod.y);
            font.draw(game.batch, tittleOberonOrbitRotation.text, tittleOberonOrbitRotation.x, tittleOberonOrbitRotation.y);
            font.draw(game.batch, tittleOberonSurfaceGravity.text, tittleOberonSurfaceGravity.x, tittleOberonSurfaceGravity.y);
            font.draw(game.batch, tittleOberonSurfaceTemperature.text, tittleOberonSurfaceTemperature.x, tittleOberonSurfaceTemperature.y);
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
