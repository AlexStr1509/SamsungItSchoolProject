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
public class ScreenRhea implements Screen {
    final MyGame game;
    final ScreenGame screenGame;
    public Texture imgRamka, imgBack, imgGoRight, imgGoLeft, imgSearch, imgInfo;
    public  RealButton btnBack, btnGoRight, btnGoLeft, btnSearch, btnInfo;
    public BitmapFont font;
    public TextMaker tittleRhea, tittleRheaEquarorialDiameter, tittleRheaMass, tittleRheaDistFromPlanet, tittleRheaRotationPeriod;
    public TextMaker tittleRheaOrbitRotation, tittleRheaSurfaceGravity, tittleRheaSurfaceTemperature;
    boolean isInfo = false;
    ScreenRhea(MyGame game, ScreenGame screenGame){
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
        btnInfo = new RealButton(SCR_WIDTH-150f, 650f, 100f);
        tittleRhea = new TextMaker("RHEA", font, SCR_WIDTH/5f, SCR_HEIGHT-50f);
        tittleRheaEquarorialDiameter = new TextMaker("equatorial diameter       1528 km", font, 30f, tittleRhea.y-100f);
        tittleRheaMass = new TextMaker("mass                   2.3*10^21 kg", font, 30f, tittleRheaEquarorialDiameter.y-70f);
        tittleRheaDistFromPlanet = new TextMaker("dist.from planet         535491 km", font, 30f, tittleRheaMass.y-70f);
        tittleRheaRotationPeriod = new TextMaker("rotation period             4.5 days", font, 30f, tittleRheaDistFromPlanet.y-70f);
        tittleRheaOrbitRotation = new TextMaker("planet orbit rotation      4.5 days", font, 30f, tittleRheaRotationPeriod.y-70f);
        tittleRheaSurfaceGravity = new TextMaker("surface gravity         0.26 m/s^2", font, 30f, tittleRheaOrbitRotation.y-70f);
        tittleRheaSurfaceTemperature = new TextMaker("surface temperature           73K", font, 30f, tittleRheaSurfaceGravity.y-70f);
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
        screenGame.modelBatch.render(screenGame.rhea);
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
            if(btnGoRight.hit(game.touch.x, game.touch.y)){
                game.setScreen(game.screenIapetus);
                screenGame.camera.position.set(1f, 50f, 80f);
                screenGame.camera.lookAt(0f, 0f, 0f);
                game.screenIapetus.isInfo = false;
            }
            if (btnGoLeft.hit(game.touch.x, game.touch.y)){
                game.setScreen(game.screenTitan);
                screenGame.camera.position.set(1f, 50f, 80f);
                screenGame.camera.lookAt(0f, 0f, 0f);
                game.screenTitan.isInfo = false;
            }
            if(btnSearch.hit(game.touch.x, game.touch.y)){
                screenGame.camera.position.set(91f, -10f,0f);
                screenGame.camera.lookAt(95f, -6f, 0f);
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
            font.draw(game.batch, tittleRhea.text, tittleRhea.x, tittleRhea.y);
            font.draw(game.batch, tittleRheaEquarorialDiameter.text, tittleRheaEquarorialDiameter.x, tittleRheaEquarorialDiameter.y);
            font.draw(game.batch, tittleRheaMass.text, tittleRheaMass.x, tittleRheaMass.y);
            font.draw(game.batch, tittleRheaDistFromPlanet.text, tittleRheaDistFromPlanet.x, tittleRheaDistFromPlanet.y);
            font.draw(game.batch, tittleRheaRotationPeriod.text, tittleRheaRotationPeriod.x, tittleRheaRotationPeriod.y);
            font.draw(game.batch, tittleRheaOrbitRotation.text, tittleRheaOrbitRotation.x, tittleRheaOrbitRotation.y);
            font.draw(game.batch, tittleRheaSurfaceGravity.text, tittleRheaSurfaceGravity.x, tittleRheaSurfaceGravity.y);
            font.draw(game.batch, tittleRheaSurfaceTemperature.text, tittleRheaSurfaceTemperature.x, tittleRheaSurfaceTemperature.y);
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
