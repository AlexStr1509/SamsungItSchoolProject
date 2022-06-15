package ru.myitschool;
import static ru.myitschool.MyGame.SCR_HEIGHT;
import static ru.myitschool.MyGame.SCR_WIDTH;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.math.Vector3;
public class ScreenVenus implements Screen {
    final MyGame game;
    final ScreenGame screenGame;
    public Texture imgRamka, imgBack, imgSearch, imgInfo;
    public RealButton btnBack, btnSearch, btnInfo;
    public BitmapFont font;
    public TextMaker tittleVenus, tittleVenusEquarorialDiameter, tittleVenusMass, tittleVenusDistFromSun, tittleVenusRotationPeriod;
    public TextMaker tittleVenusSolarOrbitRotation, tittleVenusSurfaceGravity, tittleVenusSurfaceTemperature;
    boolean isInfo = false;
    ScreenVenus(MyGame game, ScreenGame screenGame){
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
        btnSearch = new RealButton(SCR_WIDTH-150f, 200f, 100f);
        btnBack = new RealButton(SCR_WIDTH-150f, 50f, 150f);
        btnInfo = new RealButton(SCR_WIDTH-150f, 350f, 100f);
        tittleVenus = new TextMaker("VENUS", font, SCR_WIDTH/5f, SCR_HEIGHT-50f);
        tittleVenusEquarorialDiameter = new TextMaker("equatorial diameter         12104 km", font, 30f, tittleVenus.y-100f);
        tittleVenusMass = new TextMaker("mass                     4.9*10^24 kg", font, 30f, tittleVenusEquarorialDiameter.y-70f);
        tittleVenusDistFromSun = new TextMaker("dist.from sun               108 m km", font, 30f, tittleVenusMass.y-70f);
        tittleVenusRotationPeriod = new TextMaker("rotation period              243 days", font, 30f, tittleVenusDistFromSun.y-70f);
        tittleVenusSolarOrbitRotation = new TextMaker("solar orbit rotation        225 days", font, 30f, tittleVenusRotationPeriod.y-70f);
        tittleVenusSurfaceGravity = new TextMaker("surface gravity            8.9 m/s^2", font, 30f, tittleVenusSolarOrbitRotation.y-70f);
        tittleVenusSurfaceTemperature = new TextMaker("surface temperature            735K", font, 30f, tittleVenusSurfaceGravity.y-70f);
    }
    @Override
    public void show() { }
    @Override
    public void render(float delta) {
        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        screenGame.modelBatch.begin(screenGame.camera);
        screenGame.modelBatch.render(screenGame.space);
        screenGame.modelBatch.render(screenGame.sun);
        screenGame.modelBatch.render(screenGame.venus);
        screenGame.modelBatch.end();
        if(Gdx.input.justTouched()) {
            game.touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            game.orthographicCamera.unproject(game.touch);
            if(btnBack.hit(game.touch.x, game.touch.y)){
                game.setScreen(game.screenSearch);
                screenGame.camera.position.set(1f, 50f, 80f);
                screenGame.camera.lookAt(0f, 0f, 0f);
                isInfo = false;
            }
            if(btnSearch.hit(game.touch.x, game.touch.y)){
                screenGame.camera.position.set(-34f,0f,-14f);
                screenGame.camera.lookAt(-30f, 0f,-10f);
            }
            if(btnInfo.hit(game.touch.x, game.touch.y)){
                isInfo = true;
            }
        }
        game.orthographicCamera.update();
        game.batch.setProjectionMatrix(game.orthographicCamera.combined);
        game.batch.begin();
        game.batch.draw(imgBack, btnBack.x, btnBack.y);
        game.batch.draw(imgSearch, btnSearch.x, btnSearch.y);
        game.batch.draw(imgInfo, btnInfo.x, btnInfo.y);
        if(isInfo) {
            font.draw(game.batch, tittleVenus.text, tittleVenus.x, tittleVenus.y);
            font.draw(game.batch, tittleVenusEquarorialDiameter.text, tittleVenusEquarorialDiameter.x, tittleVenusEquarorialDiameter.y);
            font.draw(game.batch, tittleVenusMass.text, tittleVenusMass.x, tittleVenusMass.y);
            font.draw(game.batch, tittleVenusDistFromSun.text, tittleVenusDistFromSun.x, tittleVenusDistFromSun.y);
            font.draw(game.batch, tittleVenusRotationPeriod.text, tittleVenusRotationPeriod.x, tittleVenusRotationPeriod.y);
            font.draw(game.batch, tittleVenusSolarOrbitRotation.text, tittleVenusSolarOrbitRotation.x, tittleVenusSolarOrbitRotation.y);
            font.draw(game.batch, tittleVenusSurfaceGravity.text, tittleVenusSurfaceGravity.x, tittleVenusSurfaceGravity.y);
            font.draw(game.batch, tittleVenusSurfaceTemperature.text, tittleVenusSurfaceTemperature.x, tittleVenusSurfaceTemperature.y);
        }
        game.batch.end();
    }
    @Override
    public void resize(int width, int height) { }
    @Override
    public void pause() { }
    @Override
    public void resume() { }
    @Override
    public void hide() { }
    @Override
    public void dispose() {
        imgBack.dispose();
        imgInfo.dispose();
        imgRamka.dispose();
        imgSearch.dispose();
        font.dispose();
    }
}

