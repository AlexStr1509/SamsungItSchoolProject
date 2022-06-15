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
public class ScreenTitania implements Screen {
    final MyGame game;
    final ScreenGame screenGame;
    public Texture imgRamka, imgBack, imgGoRight, imgSearch, imgInfo;
    public  RealButton btnBack, btnGoRight, btnSearch, btnInfo;
    public BitmapFont font;
    public TextMaker tittleTitania, tittleTitaniaEquarorialDiameter, tittleTitaniaMass, tittleTitaniaDistFromPlanet, tittleTitaniaRotationPeriod;
    public TextMaker tittleTitaniaOrbitRotation, tittleTitaniaSurfaceGravity, tittleTitaniaSurfaceTemperature;
    boolean isInfo = false;
    ScreenTitania(MyGame game, ScreenGame screenGame){
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
        tittleTitania = new TextMaker("TITANIA", font, SCR_WIDTH/6f, SCR_HEIGHT-50f);
        tittleTitaniaEquarorialDiameter = new TextMaker("equatorial diameter       1523 km", font, 30f, tittleTitania.y-100f);
        tittleTitaniaMass = new TextMaker("mass                     3*10^21 kg", font, 30f, tittleTitaniaEquarorialDiameter.y-70f);
        tittleTitaniaDistFromPlanet = new TextMaker("dist.from planet         585719 km", font, 30f, tittleTitaniaMass.y-70f);
        tittleTitaniaRotationPeriod = new TextMaker("rotation period            13.5 days", font, 30f, tittleTitaniaDistFromPlanet.y-70f);
        tittleTitaniaOrbitRotation = new TextMaker("planet orbit rotation     13.5 days", font, 30f, tittleTitaniaRotationPeriod.y-70f);
        tittleTitaniaSurfaceGravity = new TextMaker("surface gravity         0.35 m/s^2", font, 30f, tittleTitaniaOrbitRotation.y-70f);
        tittleTitaniaSurfaceTemperature = new TextMaker("surface temperature           73K", font, 30f, tittleTitaniaSurfaceGravity.y-70f);
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
        screenGame.modelBatch.render(screenGame.titanya);
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
                game.setScreen(game.screenOberon);
                screenGame.camera.position.set(1f, 50f, 80f);
                screenGame.camera.lookAt(0f, 0f, 0f);
                game.screenOberon.isInfo=false;
                isInfo = false;
            }
            if(btnSearch.hit(game.touch.x, game.touch.y)){
                screenGame.camera.position.set(46f,-9f,-88f);
                screenGame.camera.lookAt(50f,-5f,-84f);
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
        game.batch.draw(imgInfo,btnInfo.x, btnInfo.y);
        if(isInfo) {
            font.draw(game.batch, tittleTitania.text, tittleTitania.x, tittleTitania.y);
            font.draw(game.batch, tittleTitaniaEquarorialDiameter.text, tittleTitaniaEquarorialDiameter.x, tittleTitaniaEquarorialDiameter.y);
            font.draw(game.batch, tittleTitaniaMass.text, tittleTitaniaMass.x, tittleTitaniaMass.y);
            font.draw(game.batch, tittleTitaniaDistFromPlanet.text, tittleTitaniaDistFromPlanet.x, tittleTitaniaDistFromPlanet.y);
            font.draw(game.batch, tittleTitaniaRotationPeriod.text, tittleTitaniaRotationPeriod.x, tittleTitaniaRotationPeriod.y);
            font.draw(game.batch, tittleTitaniaOrbitRotation.text, tittleTitaniaOrbitRotation.x, tittleTitaniaOrbitRotation.y);
            font.draw(game.batch, tittleTitaniaSurfaceGravity.text, tittleTitaniaSurfaceGravity.x, tittleTitaniaSurfaceGravity.y);
            font.draw(game.batch, tittleTitaniaSurfaceTemperature.text, tittleTitaniaSurfaceTemperature.x, tittleTitaniaSurfaceTemperature.y);
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
