package ru.myitschool;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Vector3;
import static ru.myitschool.MyGame.SCR_HEIGHT;
import static ru.myitschool.MyGame.SCR_WIDTH;
public class ScreenSearch implements Screen {
    final MyGame game;
    public BitmapFont font;
    public Texture imgBG, imgBack, imgCheck;
    public RealButton btnBack,btnCheckMercury, btnCheckVenus, btnCheckEarth, btnCheckMars, btnCheckJupiter, btnCheckSaturn, btnCheckUranus, btnCheckNeptune, btnCheckPluto;
    public  TextMaker tittle, tittleMercury, tittleVenus, tittleEarth, tittleMars, tittleJupiter, tittleSaturn, tittleUranium, tittleNeptune, tittlePluto;
    ScreenSearch(MyGame game){
        this.game=game;
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("SpaceFont.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = SCR_WIDTH/20;
        parameter.borderColor= Color.BLUE;
        parameter.borderWidth = 4f;
        parameter.color = Color.PURPLE;
        font = generator.generateFont(parameter);
        imgBG = new Texture("space.jpg");
        imgBack = new Texture("imgExit.png");
        imgCheck = new Texture("imgCheck.png");
        btnBack = new RealButton(50f,50f,100f);
        btnCheckMercury = new RealButton(800f, SCR_HEIGHT-300f, 200f);
        btnCheckVenus = new RealButton(660f, SCR_HEIGHT-500f, 200f);
        btnCheckEarth = new RealButton(650f, SCR_HEIGHT-700f, 200f);
        btnCheckMars = new RealButton(620f, SCR_HEIGHT-900f, 200f);
        btnCheckJupiter = new RealButton(SCR_WIDTH-340f, SCR_HEIGHT-300f, 200f);
        btnCheckSaturn = new RealButton(SCR_WIDTH-340f, SCR_HEIGHT-500f, 200f);
        btnCheckUranus = new RealButton(SCR_WIDTH-340f, SCR_HEIGHT-700f, 200f);
        btnCheckNeptune = new RealButton(SCR_WIDTH-330f, SCR_HEIGHT-900f, 200f);
        btnCheckPluto = new RealButton(SCR_WIDTH-860f, SCR_HEIGHT-1030f, 200f);
        tittle = new TextMaker("OBJECTS OF SOLAR SYSTEM", font, 220f, SCR_HEIGHT-50f);
        tittleMercury = new TextMaker("MERCURY", font,280f, SCR_HEIGHT-200f );
        tittleVenus = new TextMaker("VENUS", font, 280f, SCR_HEIGHT-400f);
        tittleEarth = new TextMaker("EARTH", font, 280f, SCR_HEIGHT-600f);
        tittleMars = new TextMaker("MARS", font, 280f, SCR_HEIGHT-800f);
        tittleJupiter = new TextMaker("JUPITER", font,SCR_WIDTH-800f, SCR_HEIGHT-200f);
        tittleSaturn = new TextMaker("SATURN", font, SCR_WIDTH-800f, SCR_HEIGHT-400f);
        tittleUranium = new TextMaker("URANUS", font, SCR_WIDTH-800f, SCR_HEIGHT-600f);
        tittleNeptune = new TextMaker("NEPTUNE", font, SCR_WIDTH-825f, SCR_HEIGHT-800f);
        tittlePluto = new TextMaker("PLUTO", font, SCR_WIDTH/2.8f, SCR_HEIGHT-930f);
    }
    @Override
    public void show() {
    }
    @Override
    public void render(float delta) {
        if(Gdx.input.isTouched()){
            game.touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            game.orthographicCamera.unproject(game.touch);
            if(btnBack.hit(game.touch.x, game.touch.y)) {
                game.setScreen(game.screenGame);
                game.screenGame.camera.position.set(1f, 20f,60f);
                game.screenGame.camera.lookAt(0f,0f,0f);
            }
            if (btnCheckMercury.hit(game.touch.x, game.touch.y)){
                game.setScreen(game.screenMercury);
                game.screenGame.camera.position.set(1f, 20f,60f);
                game.screenGame.camera.lookAt(0f,0f,0f);
            }
            if(btnCheckVenus.hit(game.touch.x, game.touch.y)){
                game.setScreen(game.screenVenus);
                game.screenGame.camera.position.set(1f, 20f,60f);
                game.screenGame.camera.lookAt(0f,0f,0f);
            }
            if(btnCheckEarth.hit(game.touch.x, game.touch.y)){
                game.setScreen(game.screenEarth);
                game.screenGame.camera.position.set(1f, 20f,60f);
                game.screenGame.camera.lookAt(0f,0f,0f);
            }
            if(btnCheckMars.hit(game.touch.x,  game.touch.y)){
                game.setScreen(game.screenMars);
                game.screenGame.camera.position.set(1f, 20f,60f);
                game.screenGame.camera.lookAt(0f,0f,0f);
            }
            if(btnCheckJupiter.hit(game.touch.x, game.touch.y)){
                game.setScreen(game.screenJupiter);
                game.screenGame.camera.position.set(1f, 20f,60f);
                game.screenGame.camera.lookAt(0f,0f,0f);
            }
            if(btnCheckSaturn.hit(game.touch.x, game.touch.y)){
                game.setScreen(game.screenSaturn);
                game.screenGame.camera.position.set(1f, 20f,60f);
                game.screenGame.camera.lookAt(0f,0f,0f);
            }
            if(btnCheckUranus.hit(game.touch.x, game.touch.y)){
                game.setScreen(game.screenUranus);
                game.screenGame.camera.position.set(1f, 20f,60f);
                game.screenGame.camera.lookAt(0f,0f,0f);
            }
            if(btnCheckNeptune.hit(game.touch.x, game.touch.y)){
                game.setScreen(game.screenNeptune);
                game.screenGame.camera.position.set(1f, 20f,60f);
                game.screenGame.camera.lookAt(0f,0f,0f);
            }
            if(btnCheckPluto.hit(game.touch.x, game.touch.y)){
                game.setScreen(game.screenPluto);
                game.screenGame.camera.position.set(1f, 20f,60f);
                game.screenGame.camera.lookAt(0f,0f,0f);
            }
        }
        game.orthographicCamera.update();
        game.batch.setProjectionMatrix(game.orthographicCamera.combined);
        game.batch.begin();
        game.batch.draw(imgBG, 0,0, SCR_WIDTH, SCR_HEIGHT);
        font.draw(game.batch, tittle.text, tittle.x, tittle.y);
        font.draw(game.batch, tittleMercury.text, tittleMercury.x, tittleMercury.y);
        font.draw(game.batch, tittleVenus.text, tittleVenus.x, tittleVenus.y);
        font.draw(game.batch, tittleEarth.text, tittleEarth.x, tittleEarth.y);
        font.draw(game.batch, tittleMars.text, tittleMars.x, tittleMars.y);
        font.draw(game.batch, tittleJupiter.text, tittleJupiter.x, tittleJupiter.y);
        font.draw(game.batch, tittleSaturn.text, tittleSaturn.x, tittleSaturn.y);
        font.draw(game.batch, tittleUranium.text, tittleUranium.x, tittleUranium.y);
        font.draw(game.batch, tittleNeptune.text, tittleNeptune.x, tittleNeptune.y);
        font.draw(game.batch, tittlePluto.text, tittlePluto.x, tittlePluto.y);
        game.batch.draw(imgBack, btnBack.x, btnBack.y);
        game.batch.draw(imgCheck, btnCheckMercury.x, btnCheckMercury.y);
        game.batch.draw(imgCheck, btnCheckVenus.x, btnCheckVenus.y);
        game.batch.draw(imgCheck, btnCheckEarth.x, btnCheckEarth.y);
        game.batch.draw(imgCheck, btnCheckMars.x, btnCheckMars.y);
        game.batch.draw(imgCheck, btnCheckJupiter.x, btnCheckJupiter.y);
        game.batch.draw(imgCheck, btnCheckSaturn.x, btnCheckSaturn.y);
        game.batch.draw(imgCheck, btnCheckUranus.x, btnCheckUranus.y);
        game.batch.draw(imgCheck, btnCheckNeptune.x, btnCheckNeptune.y);
        game.batch.draw(imgCheck, btnCheckPluto.x, btnCheckPluto.y);
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
        imgBG.dispose();
        imgBack.dispose();
        font.dispose();
    }
}
