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
public class ScreenUmbriel implements Screen {
    final MyGame game;
    final ScreenGame screenGame;
    public Texture imgRamka, imgBack, imgGoRight, imgGoLeft, imgSearch, imgInfo;
    public  RealButton btnBack, btnGoRight, btnGoLeft, btnSearch, btnInfo;
    public BitmapFont font;
    public TextMaker tittleUmbriel, tittleUmbrielEquarorialDiameter, tittleUmbrielMass, tittleUmbrielDistFromPlanet, tittleUmbrielRotationPeriod;
    public TextMaker tittleUmbrielOrbitRotation, tittleUmbrielSurfaceGravity, tittleUmbrielSurfaceTemperature;
    boolean isInfo = false;
    ScreenUmbriel(MyGame game, ScreenGame screenGame){
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
        tittleUmbriel = new TextMaker("UMBRIEL", font, SCR_WIDTH/5f, SCR_HEIGHT-50f);
        tittleUmbrielEquarorialDiameter = new TextMaker("equatorial diameter       1169 km", font, 30f, tittleUmbriel.y-100f);
        tittleUmbrielMass = new TextMaker("mass                  1.17*10^21 kg", font, 30f, tittleUmbrielEquarorialDiameter.y-70f);
        tittleUmbrielDistFromPlanet = new TextMaker("dist.from planet        267013 km", font, 30f, tittleUmbrielMass.y-70f);
        tittleUmbrielRotationPeriod = new TextMaker("rotation period             4.1 days", font, 30f, tittleUmbrielDistFromPlanet.y-70f);
        tittleUmbrielOrbitRotation = new TextMaker("planet orbit rotation      4.1 days", font, 30f, tittleUmbrielRotationPeriod.y-70f);
        tittleUmbrielSurfaceGravity = new TextMaker("surface gravity          0.2 m/s^2", font, 30f, tittleUmbrielOrbitRotation.y-70f);
        tittleUmbrielSurfaceTemperature = new TextMaker("surface temperature           73K", font, 30f, tittleUmbrielSurfaceGravity.y-70f);
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
        screenGame.modelBatch.render(screenGame.umbriel);
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
                game.setScreen(game.screenAriel);
                screenGame.camera.position.set(1f, 50f, 80f);
                screenGame.camera.lookAt(0f, 0f, 0f);
                isInfo = false;
                game.screenAriel.isInfo = false;
            }
            if (btnGoLeft.hit(game.touch.x, game.touch.y)){
                game.setScreen(game.screenOberon);
                screenGame.camera.position.set(1f, 50f, 80f);
                screenGame.camera.lookAt(0f, 0f, 0f);
                isInfo = false;
                game.screenOberon.isInfo =false;
            }
            if(btnSearch.hit(game.touch.x, game.touch.y)){
                screenGame.camera.position.set(48f,-8f,-92f);
                screenGame.camera.lookAt(50f,-6f,-90f);
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
        if(isInfo) {
            font.draw(game.batch, tittleUmbriel.text, tittleUmbriel.x, tittleUmbriel.y);
            font.draw(game.batch, tittleUmbrielEquarorialDiameter.text, tittleUmbrielEquarorialDiameter.x, tittleUmbrielEquarorialDiameter.y);
            font.draw(game.batch, tittleUmbrielMass.text, tittleUmbrielMass.x, tittleUmbrielMass.y);
            font.draw(game.batch, tittleUmbrielDistFromPlanet.text, tittleUmbrielDistFromPlanet.x, tittleUmbrielDistFromPlanet.y);
            font.draw(game.batch, tittleUmbrielRotationPeriod.text, tittleUmbrielRotationPeriod.x, tittleUmbrielRotationPeriod.y);
            font.draw(game.batch, tittleUmbrielOrbitRotation.text, tittleUmbrielOrbitRotation.x, tittleUmbrielOrbitRotation.y);
            font.draw(game.batch, tittleUmbrielSurfaceGravity.text, tittleUmbrielSurfaceGravity.x, tittleUmbrielSurfaceGravity.y);
            font.draw(game.batch, tittleUmbrielSurfaceTemperature.text, tittleUmbrielSurfaceTemperature.x, tittleUmbrielSurfaceTemperature.y);
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