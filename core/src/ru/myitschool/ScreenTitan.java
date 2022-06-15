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
public class ScreenTitan implements Screen {
    final MyGame game;
    final ScreenGame screenGame;
    public Texture imgRamka, imgBack, imgGoRight, imgSearch, imgInfo;
    public  RealButton btnBack, btnGoRight, btnSearch, btnInfo;
    public BitmapFont font;
    public TextMaker tittleTitan, tittleTitanEquarorialDiameter, tittleTitanMass, tittleTitanDistFromPlanet, tittleTitanRotationPeriod;
    public TextMaker tittleTitanOrbitRotation, tittleTitanSurfaceGravity, tittleTitanSurfaceTemperature;
    boolean isInfo = false;
    ScreenTitan(MyGame game, ScreenGame screenGame){
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
        imgSearch = new Texture("imgSearch.png");
        imgInfo = new Texture("imgInfo.png");
        btnBack = new RealButton(SCR_WIDTH-150f, 50f, 100f);
        btnGoRight = new RealButton(SCR_WIDTH-150f, 200f, 100f);
        btnSearch = new RealButton(SCR_WIDTH-150f, 350f, 100f);
        btnInfo = new RealButton(SCR_WIDTH-150f, 500f, 100f);
        tittleTitan = new TextMaker("TITAN", font, SCR_WIDTH/5f, SCR_HEIGHT-50f);
        tittleTitanEquarorialDiameter = new TextMaker("equatorial diameter       5150 km", font, 30f, tittleTitan.y-100f);
        tittleTitanMass = new TextMaker("mass                  1.35*10^23 kg", font, 30f, tittleTitanEquarorialDiameter.y-70f);
        tittleTitanDistFromPlanet = new TextMaker("dist.from planet         1.24 m km", font, 30f, tittleTitanMass.y-70f);
        tittleTitanRotationPeriod = new TextMaker("rotation period             16 days", font, 30f, tittleTitanDistFromPlanet.y-70f);
        tittleTitanOrbitRotation = new TextMaker("planet orbit rotation      16 days", font, 30f, tittleTitanRotationPeriod.y-70f);
        tittleTitanSurfaceGravity = new TextMaker("surface gravity         1.35 m/s^2", font, 30f, tittleTitanOrbitRotation.y-70f);
        tittleTitanSurfaceTemperature = new TextMaker("surface temperature           93K", font, 30f, tittleTitanSurfaceGravity.y-70f);
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
        screenGame.modelBatch.render(screenGame.titan);
        screenGame.modelBatch.end();
        if(Gdx.input.justTouched()) {
            game.touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            game.orthographicCamera.unproject(game.touch);
            if(btnBack.hit(game.touch.x, game.touch.y)){
                game.setScreen(game.screenSaturn);
                screenGame.camera.position.set(1f, 50f, 80f);
                screenGame.camera.lookAt(0f, 0f, 0f);
                game.screenSaturn.isInfo = false;
                isInfo = false;
            }
            if(btnGoRight.hit(game.touch.x, game.touch.y)){
                game.setScreen(game.screenRhea);
                screenGame.camera.position.set(1f, 50f, 80f);
                screenGame.camera.lookAt(0f, 0f, 0f);
                game.screenRhea.isInfo = false;
            }
            if(btnSearch.hit(game.touch.x, game.touch.y)){
                screenGame.camera.position.set(94f, 4f,0f);
                screenGame.camera.lookAt(95f, 6f,0f);
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
        game.batch.draw(imgSearch, btnSearch.x, btnSearch.y);
        game.batch.draw(imgInfo, btnInfo.x, btnInfo.y);
        if(isInfo) {
            font.draw(game.batch, tittleTitan.text, tittleTitan.x, tittleTitan.y);
            font.draw(game.batch, tittleTitanEquarorialDiameter.text, tittleTitanEquarorialDiameter.x, tittleTitanEquarorialDiameter.y);
            font.draw(game.batch, tittleTitanMass.text, tittleTitanMass.x, tittleTitanMass.y);
            font.draw(game.batch, tittleTitanDistFromPlanet.text, tittleTitanDistFromPlanet.x, tittleTitanDistFromPlanet.y);
            font.draw(game.batch, tittleTitanRotationPeriod.text, tittleTitanRotationPeriod.x, tittleTitanRotationPeriod.y);
            font.draw(game.batch, tittleTitanOrbitRotation.text, tittleTitanOrbitRotation.x, tittleTitanOrbitRotation.y);
            font.draw(game.batch, tittleTitanSurfaceGravity.text, tittleTitanSurfaceGravity.x, tittleTitanSurfaceGravity.y);
            font.draw(game.batch, tittleTitanSurfaceTemperature.text, tittleTitanSurfaceTemperature.x, tittleTitanSurfaceTemperature.y);
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
        imgGoRight.dispose();
        imgInfo.dispose();
        imgRamka.dispose();
        imgSearch.dispose();
        font.dispose();
    }
}
