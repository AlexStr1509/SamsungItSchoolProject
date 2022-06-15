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
public class ScreenPhobos implements Screen {
    final MyGame game;
    final ScreenGame screenGame;
    public Texture imgRamka, imgBack, imgGoLeft, imgSearch, imgInfo;
    public RealButton btnBack, btnGoLeft, btnSearch, btnInfo;
    public BitmapFont font;
    public TextMaker tittlePhobos, tittlePhobosEquarorialDiameter, tittlePhobosMass, tittlePhobosDistFromPlanet, tittlePhobosRotationPeriod;
    public TextMaker tittlePhobosOrbitRotation, tittlePhobosSurfaceGravity, tittlePhobosSurfaceTemperature;
    boolean isInfo = false;
    ScreenPhobos(MyGame game, ScreenGame screenGame){
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
        tittlePhobos = new TextMaker("PHOBOS", font, SCR_WIDTH/5.5f, SCR_HEIGHT-50f);
        tittlePhobosEquarorialDiameter = new TextMaker("equatorial diameter          27 km", font, 30f, tittlePhobos.y-100f);
        tittlePhobosMass = new TextMaker("mass                   1.07*10^16 kg", font, 30f, tittlePhobosEquarorialDiameter.y-70f);
        tittlePhobosDistFromPlanet = new TextMaker("dist.from planet          9386 km", font, 30f, tittlePhobosMass.y-70f);
        tittlePhobosRotationPeriod = new TextMaker("rotation period            7 h 39 m", font, 30f, tittlePhobosDistFromPlanet.y-70f);
        tittlePhobosOrbitRotation = new TextMaker("planet orbit rotation     7 h 39 m", font, 30f, tittlePhobosRotationPeriod.y-70f);
        tittlePhobosSurfaceGravity = new TextMaker("surface gravity        0.006 m/s^2", font, 30f, tittlePhobosOrbitRotation.y-70f);
        tittlePhobosSurfaceTemperature = new TextMaker("surface temperature         233K", font, 30f, tittlePhobosSurfaceGravity.y-70f);
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
        screenGame.modelBatch.render(screenGame.mars);
        screenGame.modelBatch.render(screenGame.phobos);
        screenGame.modelBatch.end();
        if(Gdx.input.justTouched()) {
            game.touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            game.orthographicCamera.unproject(game.touch);
            if(btnBack.hit(game.touch.x, game.touch.y)){
                game.setScreen(game.screenMars);
                screenGame.camera.position.set(1f, 50f, 80f);
                screenGame.camera.lookAt(0f, 0f, 0f);
                isInfo=false;
                game.screenMars.isInfo = false;
                game.screenDeimos.isInfo = false;

            }
            if(btnGoLeft.hit(game.touch.x, game.touch.y)){
                game.setScreen(game.screenDeimos);
                game.screenDeimos.isInfo = false;
                screenGame.camera.position.set(1f, 50f, 80f);
                screenGame.camera.lookAt(0f, 0f, 0f);
            }
            if(btnSearch.hit(game.touch.x, game.touch.y)){
                screenGame.camera.position.set(0f, 0f,35f);
                screenGame.camera.lookAt(0f,0f,38f);
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
            font.draw(game.batch, tittlePhobos.text, tittlePhobos.x, tittlePhobos.y);
            font.draw(game.batch, tittlePhobosEquarorialDiameter.text, tittlePhobosEquarorialDiameter.x, tittlePhobosEquarorialDiameter.y);
            font.draw(game.batch, tittlePhobosMass.text, tittlePhobosMass.x, tittlePhobosMass.y);
            font.draw(game.batch, tittlePhobosDistFromPlanet.text, tittlePhobosDistFromPlanet.x, tittlePhobosDistFromPlanet.y);
            font.draw(game.batch, tittlePhobosRotationPeriod.text, tittlePhobosRotationPeriod.x, tittlePhobosRotationPeriod.y);
            font.draw(game.batch, tittlePhobosOrbitRotation.text, tittlePhobosOrbitRotation.x, tittlePhobosOrbitRotation.y);
            font.draw(game.batch, tittlePhobosSurfaceGravity.text, tittlePhobosSurfaceGravity.x, tittlePhobosSurfaceGravity.y);
            font.draw(game.batch, tittlePhobosSurfaceTemperature.text, tittlePhobosSurfaceTemperature.x, tittlePhobosSurfaceTemperature.y);
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