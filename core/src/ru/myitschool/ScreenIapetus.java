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
public class ScreenIapetus implements Screen {
    final MyGame game;
    final ScreenGame screenGame;
    public Texture imgRamka, imgBack, imgGoRight, imgGoLeft, imgSearch, imgInfo;
    public  RealButton btnBack, btnGoRight, btnGoLeft, btnSearch, btnInfo;
    public BitmapFont font;
    public TextMaker tittleIapetus, tittleIapetusEquarorialDiameter, tittleIapetusMass, tittleIapetusDistFromPlanet, tittleIapetusRotationPeriod;
    public TextMaker tittleIapetusOrbitRotation, tittleIapetusSurfaceGravity, tittleIapetusSurfaceTemperature;
    boolean isInfo = false;
    ScreenIapetus(MyGame game, ScreenGame screenGame){
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
        tittleIapetus = new TextMaker("IAPETUS", font, SCR_WIDTH/6f, SCR_HEIGHT-50f);
        tittleIapetusEquarorialDiameter = new TextMaker("equatorial diameter       1492 km", font, 30f, tittleIapetus.y-100f);
        tittleIapetusMass = new TextMaker("mass                  1.81*10^21 kg", font, 30f, tittleIapetusEquarorialDiameter.y-70f);
        tittleIapetusDistFromPlanet = new TextMaker("dist.from planet         3.62 m km", font, 30f, tittleIapetusMass.y-70f);
        tittleIapetusRotationPeriod = new TextMaker("rotation period             79 days", font, 30f, tittleIapetusDistFromPlanet.y-70f);
        tittleIapetusOrbitRotation = new TextMaker("planet orbit rotation      79 days", font, 30f, tittleIapetusRotationPeriod.y-70f);
        tittleIapetusSurfaceGravity = new TextMaker("surface gravity         0.22 m/s^2", font, 30f, tittleIapetusOrbitRotation.y-70f);
        tittleIapetusSurfaceTemperature = new TextMaker("surface temperature          110K", font, 30f, tittleIapetusSurfaceGravity.y-70f);
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
        screenGame.modelBatch.render(screenGame.saturn);
        screenGame.modelBatch.render(screenGame.yapet);

        screenGame.modelBatch.end();
        if(Gdx.input.justTouched()) {
            game.touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            game.orthographicCamera.unproject(game.touch);
            if(btnBack.hit(game.touch.x, game.touch.y)){
                game.setScreen(game.screenSaturn);
                screenGame.camera.position.set(1f, 20f,60f);
                screenGame.camera.lookAt(0f,0f,0f);
                isInfo=false;
                game.screenSaturn.isInfo = false;
            }
            if(btnGoRight.hit(game.touch.x, game.touch.y)){
                game.setScreen(game.screenDione);
                screenGame.camera.position.set(1f, 50f, 80f);
                screenGame.camera.lookAt(0f, 0f, 0f);
                game.screenDione.isInfo = false;
            }
            if (btnGoLeft.hit(game.touch.x, game.touch.y)){
                game.setScreen(game.screenRhea);
                screenGame.camera.position.set(1f, 50f, 80f);
                screenGame.camera.lookAt(0f, 0f, 0f);
                game.screenRhea.isInfo = false;

            }
            if(btnSearch.hit(game.touch.x, game.touch.y)){
                screenGame.camera.position.set(94f, 1f, 5f);
                screenGame.camera.lookAt(95f, 2f,6f);
            }
            if(btnInfo.hit(game.touch.x, game.touch.y)){
                isInfo=true;
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
            font.draw(game.batch, tittleIapetus.text, tittleIapetus.x, tittleIapetus.y);
            font.draw(game.batch, tittleIapetusEquarorialDiameter.text, tittleIapetusEquarorialDiameter.x, tittleIapetusEquarorialDiameter.y);
            font.draw(game.batch, tittleIapetusMass.text, tittleIapetusMass.x, tittleIapetusMass.y);
            font.draw(game.batch, tittleIapetusDistFromPlanet.text, tittleIapetusDistFromPlanet.x, tittleIapetusDistFromPlanet.y);
            font.draw(game.batch, tittleIapetusRotationPeriod.text, tittleIapetusRotationPeriod.x, tittleIapetusRotationPeriod.y);
            font.draw(game.batch, tittleIapetusOrbitRotation.text, tittleIapetusOrbitRotation.x, tittleIapetusOrbitRotation.y);
            font.draw(game.batch, tittleIapetusSurfaceGravity.text, tittleIapetusSurfaceGravity.x, tittleIapetusSurfaceGravity.y);
            font.draw(game.batch, tittleIapetusSurfaceTemperature.text, tittleIapetusSurfaceTemperature.x, tittleIapetusSurfaceTemperature.y);
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
