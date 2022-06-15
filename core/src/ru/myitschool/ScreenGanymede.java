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
public class ScreenGanymede implements Screen {
    final MyGame game;
    final ScreenGame screenGame;
    public Texture imgRamka, imgBack, imgGoRight, imgSearch, imgInfo;
    public  RealButton btnBack, btnGoRight, btnSearch, btnInfo;
    public BitmapFont font;
    public TextMaker tittleGanymede, tittleGanymedeEquarorialDiameter, tittleGanymedeMass, tittleGanymedeDistFromPlanet, tittleGanymedeRotationPeriod;
    public TextMaker tittleGanymedeOrbitRotation, tittleGanymedeSurfaceGravity, tittleMGanymedeSurfaceTemperature;
    boolean isInfo = false;
    ScreenGanymede(MyGame game, ScreenGame screenGame){
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
        btnSearch= new RealButton(SCR_WIDTH-150f, 350f, 100f);
        btnInfo = new RealButton(SCR_WIDTH-150f, 500f,100f);
        tittleGanymede = new TextMaker("GANYMEDE", font, SCR_WIDTH/6f, SCR_HEIGHT-50f);
        tittleGanymedeEquarorialDiameter = new TextMaker("equatorial diameter       5262 km", font, 30f, tittleGanymede.y-100f);
        tittleGanymedeMass = new TextMaker("mass                   1.48*10^23 kg", font, 30f, tittleGanymedeEquarorialDiameter.y-70f);
        tittleGanymedeDistFromPlanet = new TextMaker("dist.from planet         1.07 m km", font, 30f, tittleGanymedeMass.y-70f);
        tittleGanymedeRotationPeriod = new TextMaker("rotation period            7.2 days", font, 30f, tittleGanymedeDistFromPlanet.y-70f);
        tittleGanymedeOrbitRotation = new TextMaker("planet orbit rotation     7.2 days", font, 30f, tittleGanymedeRotationPeriod.y-70f);
        tittleGanymedeSurfaceGravity = new TextMaker("surface gravity         1.43 m/s^2", font, 30f, tittleGanymedeOrbitRotation.y-70f);
        tittleMGanymedeSurfaceTemperature = new TextMaker("surface temperature          110K", font, 30f, tittleGanymedeSurfaceGravity.y-70f);
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
        screenGame.modelBatch.render(screenGame.ganymede);
        screenGame.modelBatch.end();
        if(Gdx.input.justTouched()) {
            game.touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            game.orthographicCamera.unproject(game.touch);
            if(btnBack.hit(game.touch.x, game.touch.y)){
                game.setScreen(game.screenJupiter);
                isInfo=false;
                game.screenJupiter.isInfo = false;
                screenGame.camera.position.set(1f, 50f, 80f);
                screenGame.camera.lookAt(0f, 0f, 0f);
            }
            if(btnGoRight.hit(game.touch.x, game.touch.y)){
                game.setScreen(game.screenCallisto);
                screenGame.camera.position.set(1f, 50f, 80f);
                screenGame.camera.lookAt(0f, 0f, 0f);
                game.screenCallisto.isInfo=false;
            }
            if(btnSearch.hit(game.touch.x, game.touch.y)){
                screenGame.camera.position.set(-84f,0f,17f);
                screenGame.camera.lookAt(-80f,0f,21f);
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
        game.batch.draw(imgSearch, btnSearch.x,btnSearch.y);
        game.batch.draw(imgInfo, btnInfo.x, btnInfo.y);
        if(isInfo) {
            font.draw(game.batch, tittleGanymede.text, tittleGanymede.x, tittleGanymede.y);
            font.draw(game.batch, tittleGanymedeEquarorialDiameter.text, tittleGanymedeEquarorialDiameter.x, tittleGanymedeEquarorialDiameter.y);
            font.draw(game.batch, tittleGanymedeMass.text, tittleGanymedeMass.x, tittleGanymedeMass.y);
            font.draw(game.batch, tittleGanymedeDistFromPlanet.text, tittleGanymedeDistFromPlanet.x, tittleGanymedeDistFromPlanet.y);
            font.draw(game.batch, tittleGanymedeRotationPeriod.text, tittleGanymedeRotationPeriod.x, tittleGanymedeRotationPeriod.y);
            font.draw(game.batch, tittleGanymedeOrbitRotation.text, tittleGanymedeOrbitRotation.x, tittleGanymedeOrbitRotation.y);
            font.draw(game.batch, tittleGanymedeSurfaceGravity.text, tittleGanymedeSurfaceGravity.x, tittleGanymedeSurfaceGravity.y);
            font.draw(game.batch, tittleMGanymedeSurfaceTemperature.text, tittleMGanymedeSurfaceTemperature.x, tittleMGanymedeSurfaceTemperature.y);
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