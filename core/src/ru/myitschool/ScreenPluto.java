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
public class ScreenPluto implements Screen {
    final MyGame game;
    final ScreenGame screenGame;
    public Texture imgRamka, imgBack, imgCheckSatteite, imgSearch, imgInfo;
    public RealButton btnBack, btnCheckSattelite, btnSearch, btnInfo;
    public BitmapFont font;
    public TextMaker tittlePluto, tittlePlutoEquarorialDiameter, tittlePlutoMass, tittlePlutoDistFromSun, tittlePlutoRotationPeriod;
    public TextMaker tittlePlutoSolarOrbitRotation, tittlePlutoSurfaceGravity, tittlePlutoSurfaceTemperature, tittlePlutoSattelites;
    boolean isInfo = false;
    ScreenPluto(MyGame game, ScreenGame screenGame){
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
        imgCheckSatteite = new Texture("imgCheck.png");
        imgSearch = new Texture("imgSearch.png");
        imgInfo = new Texture("imgInfo.png");
        btnBack = new RealButton(SCR_WIDTH-150f, 50f, 100f);
        btnCheckSattelite = new RealButton(SCR_WIDTH/4.75f, 100f, 100f);
        btnSearch = new RealButton(SCR_WIDTH-150f,200f, 100f );
        btnInfo = new RealButton(SCR_WIDTH-150f, 350f, 100f);
        tittlePluto = new TextMaker("PLUTO", font, SCR_WIDTH/5f, SCR_HEIGHT-50f);
        tittlePlutoEquarorialDiameter = new TextMaker("equatorial diameter       2374 km", font, 30f, tittlePluto.y-100f);
        tittlePlutoMass = new TextMaker("mass                   1.31*10^22 kg", font, 30f, tittlePlutoEquarorialDiameter.y-70f);
        tittlePlutoDistFromSun = new TextMaker("dist.from sun               40.8 AU", font, 30f, tittlePlutoMass.y-70f);
        tittlePlutoRotationPeriod = new TextMaker("rotation period           9 h 17 m", font, 30f, tittlePlutoDistFromSun.y-70f);
        tittlePlutoSolarOrbitRotation = new TextMaker("solar orbit rotation     248 years", font, 30f, tittlePlutoRotationPeriod.y-70f);
        tittlePlutoSurfaceGravity = new TextMaker("surface gravity         0.66 m/s^2", font, 30f, tittlePlutoSolarOrbitRotation.y-70f);
        tittlePlutoSurfaceTemperature = new TextMaker("surface temperature           45K", font, 30f, tittlePlutoSurfaceGravity.y-70f);
        tittlePlutoSattelites = new TextMaker("Pluto`s Sattelite", font, SCR_WIDTH/8f, 250f);
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
        screenGame.modelBatch.render(screenGame.pluton);
        screenGame.modelBatch.render(screenGame.haron);
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
            if(btnCheckSattelite.hit(game.touch.x, game.touch.y)){
                game.setScreen(game.screenCharon);
                screenGame.camera.position.set(1f, 50f, 80f);
                screenGame.camera.lookAt(0f, 0f, 0f);
                isInfo = false;
            }
            if(btnSearch.hit(game.touch.x, game.touch.y)){
                screenGame.camera.position.set(146f, 0f,0f);
                screenGame.camera.lookAt(150f,0f,0f);
            }
            if(btnInfo.hit(game.touch.x, game.touch.y)){
                isInfo=true;
            }
        }
        game.orthographicCamera.update();
        game.batch.setProjectionMatrix(game.orthographicCamera.combined);
        game.batch.begin();
        game.batch.draw(imgBack, btnBack.x, btnBack.y);
        game.batch.draw(imgSearch, btnSearch.x, btnSearch.y);
        game.batch.draw(imgInfo, btnInfo.x, btnInfo.y);
        if(isInfo) {
            game.batch.draw(imgCheckSatteite, btnCheckSattelite.x, btnCheckSattelite.y);
            font.draw(game.batch, tittlePluto.text, tittlePluto.x, tittlePluto.y);
            font.draw(game.batch, tittlePlutoEquarorialDiameter.text, tittlePlutoEquarorialDiameter.x, tittlePlutoEquarorialDiameter.y);
            font.draw(game.batch, tittlePlutoMass.text, tittlePlutoMass.x, tittlePlutoMass.y);
            font.draw(game.batch, tittlePlutoDistFromSun.text, tittlePlutoDistFromSun.x, tittlePlutoDistFromSun.y);
            font.draw(game.batch, tittlePlutoRotationPeriod.text, tittlePlutoRotationPeriod.x, tittlePlutoRotationPeriod.y);
            font.draw(game.batch, tittlePlutoSolarOrbitRotation.text, tittlePlutoSolarOrbitRotation.x, tittlePlutoSolarOrbitRotation.y);
            font.draw(game.batch, tittlePlutoSurfaceGravity.text, tittlePlutoSurfaceGravity.x, tittlePlutoSurfaceGravity.y);
            font.draw(game.batch, tittlePlutoSurfaceTemperature.text, tittlePlutoSurfaceTemperature.x, tittlePlutoSurfaceTemperature.y);
            font.draw(game.batch, tittlePlutoSattelites.text, tittlePlutoSattelites.x, tittlePlutoSattelites.y);
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
