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

public class ScreenEnceladus implements Screen {
    final MyGame game;
    final ScreenGame screenGame;
    public Texture imgRamka, imgBack, imgGoLeft, imgSearch, imgInfo;
    public  RealButton btnBack, btnGoLeft, btnSearch, btnInfo;
    public BitmapFont font;
    public TextMaker tittleEnceladus, tittleEnceladusEquarorialDiameter, tittleEnceladusMass, tittleEnceladusDistFromPlanet, tittleEnceladusRotationPeriod;
    public TextMaker tittleEnceladusOrbitRotation, tittleEnceladusSurfaceGravity, tittleEnceladusSurfaceTemperature;
    boolean isInfo =false;
    ScreenEnceladus(MyGame game, ScreenGame screenGame){
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
        tittleEnceladus = new TextMaker("ENCELADUS", font, SCR_WIDTH/6f, SCR_HEIGHT-50f);
        tittleEnceladusEquarorialDiameter = new TextMaker("equatorial diameter        508 km", font, 30f, tittleEnceladus.y-100f);
        tittleEnceladusMass = new TextMaker("mass                 1.08*10^20 kg", font, 30f, tittleEnceladusEquarorialDiameter.y-70f);
        tittleEnceladusDistFromPlanet = new TextMaker("dist.from planet         241846 km", font, 30f, tittleEnceladusMass.y-70f);
        tittleEnceladusRotationPeriod = new TextMaker("rotation period           1.37 days", font, 30f, tittleEnceladusDistFromPlanet.y-70f);
        tittleEnceladusOrbitRotation = new TextMaker("planet orbit rotation    1.37 days", font, 30f, tittleEnceladusRotationPeriod.y-70f);
        tittleEnceladusSurfaceGravity = new TextMaker("surface gravity         0.11 m/s^2", font, 30f, tittleEnceladusOrbitRotation.y-70f);
        tittleEnceladusSurfaceTemperature = new TextMaker("surface temperature           73K", font, 30f, tittleEnceladusSurfaceGravity.y-70f);
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
        screenGame.modelBatch.render(screenGame.encelad);
        screenGame.modelBatch.end();
        if(Gdx.input.justTouched()) {
            game.touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            game.orthographicCamera.unproject(game.touch);
            if(btnBack.hit(game.touch.x, game.touch.y)){
                game.setScreen(game.screenSaturn);
                screenGame.camera.position.set(1f, 50f, 80f);
                screenGame.camera.lookAt(0f, 0f, 0f);
                isInfo=false;
                game.screenSaturn.isInfo = false;
            }
            if(btnGoLeft.hit(game.touch.x, game.touch.y)){
                game.setScreen(game.screenTethys);
                screenGame.camera.position.set(1f, 50f, 80f);
                screenGame.camera.lookAt(0f, 0f, 0f);
                game.screenTethys.isInfo = false;
            }
            if(btnSearch.hit(game.touch.x, game.touch.y)){
                screenGame.camera.position.set(89f, -4f, 0f);
                screenGame.camera.lookAt(90f, -3f,0f);
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
        game.batch.draw(imgInfo, btnInfo.x, btnInfo.y);
        if(isInfo) {
            font.draw(game.batch, tittleEnceladus.text, tittleEnceladus.x, tittleEnceladus.y);
            font.draw(game.batch, tittleEnceladusEquarorialDiameter.text, tittleEnceladusEquarorialDiameter.x, tittleEnceladusEquarorialDiameter.y);
            font.draw(game.batch, tittleEnceladusMass.text, tittleEnceladusMass.x, tittleEnceladusMass.y);
            font.draw(game.batch, tittleEnceladusDistFromPlanet.text, tittleEnceladusDistFromPlanet.x, tittleEnceladusDistFromPlanet.y);
            font.draw(game.batch, tittleEnceladusRotationPeriod.text, tittleEnceladusRotationPeriod.x, tittleEnceladusRotationPeriod.y);
            font.draw(game.batch, tittleEnceladusOrbitRotation.text, tittleEnceladusOrbitRotation.x, tittleEnceladusOrbitRotation.y);
            font.draw(game.batch, tittleEnceladusSurfaceGravity.text, tittleEnceladusSurfaceGravity.x, tittleEnceladusSurfaceGravity.y);
            font.draw(game.batch, tittleEnceladusSurfaceTemperature.text, tittleEnceladusSurfaceTemperature.x, tittleEnceladusSurfaceTemperature.y);
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

