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
public class ScreenSun implements Screen {
    final MyGame game;
    final ScreenGame screenGame;
    public Texture imgRamka, imgBack, imgSearch, imgInfo;
    public RealButton btnBack, btnSearch, btnInfo;
    public BitmapFont font;
    public TextMaker tittleSun, tittleSunEquatorialDiameter, tittleSunMass, tittleSunDistFromGalaxyCenter, tittleSunRotationPeriod;
    public TextMaker tittleSunGalaxyOrbitRotation, tittleSunSurfaceGravity, tittleSunSurfaceTemperature;
    public boolean isInfo = false;
    ScreenSun(MyGame game, ScreenGame screenGame){
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
        imgSearch = new Texture("imgSearch.png");
        imgInfo = new Texture("imgInfo.png");
        btnBack = new RealButton(SCR_WIDTH-150f, 50f, 100f);
        btnSearch = new RealButton(SCR_WIDTH-150f, 200f, 150f);
        btnInfo = new RealButton(SCR_WIDTH-150f, 350f, 100f );
        tittleSun = new TextMaker("SUN", font, SCR_WIDTH/4f, SCR_HEIGHT-50f);
        tittleSunEquatorialDiameter = new TextMaker("equatorial diameter     1.38 m km", font, 30f, tittleSun.y-100f);
        tittleSunMass = new TextMaker("mass                 1.99*10^30 kg", font, 30f, tittleSunEquatorialDiameter.y-70f);
        tittleSunDistFromGalaxyCenter = new TextMaker("gal.center distance       26038 ly", font, 30f, tittleSunMass.y-70f);
        tittleSunRotationPeriod = new TextMaker("rotation period             25 days", font, 30f, tittleSunDistFromGalaxyCenter.y-70f);
        tittleSunGalaxyOrbitRotation = new TextMaker("gal.orbit rotation    225 m years", font, 30f, tittleSunRotationPeriod.y-70f);
        tittleSunSurfaceGravity = new TextMaker("surface gravity         274 m/s^2", font, 30f, tittleSunGalaxyOrbitRotation.y-70f);
        tittleSunSurfaceTemperature = new TextMaker("surface temperature        5778K", font, 30f, tittleSunSurfaceGravity.y-70f);
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
        screenGame.modelBatch.end();
        if(Gdx.input.justTouched()) {
            game.touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            game.orthographicCamera.unproject(game.touch);
        }
        if(btnBack.hit(game.touch.x, game.touch.y)){
            game.setScreen(game.screenGame);
            isInfo = false;
        }
        if(btnSearch.hit(game.touch.x, game.touch.y)){
            screenGame.camera.position.set(1f, 50f, 80f);
            screenGame.camera.lookAt(0f, 0f, 0f);
        }
        if(btnInfo.hit(game.touch.x, game.touch.y)){
            isInfo = true;
        }
        game.orthographicCamera.update();
        game.batch.setProjectionMatrix(game.orthographicCamera.combined);
        game.batch.begin();
        game.batch.draw(imgBack, btnBack.x, btnBack.y);
        game.batch.draw(imgSearch, btnSearch.x, btnSearch.y);
        game.batch.draw(imgInfo, btnInfo.x, btnInfo.y);
        if(isInfo) {
            font.draw(game.batch, tittleSun.text, tittleSun.x, tittleSun.y);
            font.draw(game.batch, tittleSunEquatorialDiameter.text, tittleSunEquatorialDiameter.x, tittleSunEquatorialDiameter.y);
            font.draw(game.batch, tittleSunMass.text, tittleSunMass.x, tittleSunMass.y);
            font.draw(game.batch, tittleSunDistFromGalaxyCenter.text, tittleSunDistFromGalaxyCenter.x, tittleSunDistFromGalaxyCenter.y);
            font.draw(game.batch, tittleSunRotationPeriod.text, tittleSunRotationPeriod.x, tittleSunRotationPeriod.y);
            font.draw(game.batch, tittleSunGalaxyOrbitRotation.text, tittleSunGalaxyOrbitRotation.x, tittleSunGalaxyOrbitRotation.y);
            font.draw(game.batch, tittleSunSurfaceGravity.text, tittleSunSurfaceGravity.x, tittleSunSurfaceGravity.y);
            font.draw(game.batch, tittleSunSurfaceTemperature.text, tittleSunSurfaceTemperature.x, tittleSunSurfaceTemperature.y);
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
        imgInfo.dispose();
        imgRamka.dispose();
        imgSearch.dispose();
        font.dispose();
    }
}
