package ru.myitschool;
import static ru.myitschool.MyGame.SCR_HEIGHT;
import static ru.myitschool.MyGame.SCR_WIDTH;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class ScreenMenu implements Screen {
    final MyGame game;
    Texture imgBG, imgPlay, imgExit;
    BitmapFont font;
    TextMaker tittle;
    RealButton btnPlay, btnExit;
    ScreenMenu(MyGame game) {
        this.game = game;
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("SpaceFont.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = SCR_WIDTH/20;
        parameter.borderColor= Color.BLUE;
        parameter.borderWidth = 4f;
        parameter.color = Color.PURPLE;
        font = generator.generateFont(parameter);
        imgBG = new Texture("spaceprev.jpg");
        imgPlay = new Texture("imgPlay.png");
        imgExit = new Texture("imgExit.png");
        tittle = new TextMaker("SPACE ODYSSEY", font, 540f, SCR_HEIGHT-50f);
        btnPlay = new RealButton(SCR_WIDTH/2f, SCR_HEIGHT/30f, 300);
        btnExit = new RealButton(SCR_WIDTH-150, SCR_WIDTH/45f, 300);
    }
    @Override
    public void show() {}
    @Override
    public void render(float delta) {
        if(Gdx.input.justTouched()){
            game.touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            game.orthographicCamera.unproject(game.touch);
            if(btnPlay.hit(game.touch.x, game.touch.y)) {
                game.setScreen(game.screenGame);
            }
            if(btnExit.hit(game.touch.x, game.touch.y)){
                Gdx.app.exit();
            }
        }
        game.orthographicCamera.update();
        game.batch.setProjectionMatrix(game.orthographicCamera.combined);
        game.batch.begin();
        game.batch.draw(imgBG, 0, 0, SCR_WIDTH, SCR_HEIGHT );
        font.draw(game.batch, tittle.text, tittle.x, tittle.y);
        game.batch.draw(imgPlay, btnPlay.x, btnPlay.y);
        game.batch.draw(imgExit, btnExit.x, btnExit.y);
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
        imgPlay.dispose();
        imgExit.dispose();
        font.dispose();
    }
}
