package ru.myitschool;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.math.Vector3;

public class MyGame extends Game {
	public static final int SCR_WIDTH=1920, SCR_HEIGHT=1080;
	OrthographicCamera orthographicCamera;
	Vector3 touch;
	SpriteBatch batch;
	ScreenMenu screenMenu;
	ScreenGame screenGame;
	ScreenSearch screenSearch;
	ScreenSun screenSun;
	ScreenMercury screenMercury;
	ScreenVenus screenVenus;
	ScreenEarth screenEarth;
	ScreenMoon screenMoon;
	ScreenMars screenMars;
	ScreenDeimos screenDeimos;
	ScreenPhobos screenPhobos;
	ScreenJupiter screenJupiter;
	ScreenGanymede screenGanymede;
	ScreenCallisto screenCallisto;
	ScreenEuropa screenEuropa;
	ScreenIo screenIo;
	ScreenSaturn screenSaturn;
	ScreenTitan screenTitan;
	ScreenRhea screenRhea;
	ScreenIapetus screenIapetus;
	ScreenDione screenDione;
	ScreenTethys screenTethys;
	ScreenEnceladus screenEnceladus;
	ScreenUranus screenUranus;
	ScreenTitania screenTitania;
	ScreenOberon screenOberon;
	ScreenUmbriel screenUmbriel;
	ScreenAriel screenAriel;
	ScreenNeptune screenNeptune;
	ScreenTriton screenTriton;
	ScreenPluto screenPluto;
	ScreenCharon screenCharon;

	public void create () {
		orthographicCamera = new OrthographicCamera();
		orthographicCamera.setToOrtho(false, SCR_WIDTH, SCR_HEIGHT);
		touch = new Vector3();
		batch = new SpriteBatch();

		screenMenu = new ScreenMenu(this);
		screenGame = new ScreenGame(this);
		screenSearch = new ScreenSearch(this);
		screenSun = new ScreenSun(this, screenGame);
		screenMercury = new ScreenMercury(this, screenGame);
		screenVenus = new ScreenVenus(this, screenGame);
		screenEarth = new ScreenEarth(this, screenGame);
		screenMoon = new ScreenMoon(this, screenGame);
		screenMars = new ScreenMars(this, screenGame);
		screenDeimos = new ScreenDeimos(this, screenGame);
		screenPhobos = new ScreenPhobos(this, screenGame);
		screenJupiter = new ScreenJupiter(this, screenGame);
		screenGanymede = new ScreenGanymede(this, screenGame);
		screenCallisto = new ScreenCallisto(this, screenGame);
		screenEuropa = new ScreenEuropa(this, screenGame);
		screenIo = new ScreenIo(this, screenGame);
		screenSaturn = new ScreenSaturn(this, screenGame);
		screenTitan = new ScreenTitan(this, screenGame);
		screenRhea = new ScreenRhea(this, screenGame);
		screenIapetus = new ScreenIapetus(this, screenGame);
		screenDione = new ScreenDione(this, screenGame);
		screenTethys = new ScreenTethys(this, screenGame);
		screenEnceladus = new ScreenEnceladus(this, screenGame);
		screenUranus = new ScreenUranus(this, screenGame);
		screenTitania = new ScreenTitania(this, screenGame);
		screenOberon = new ScreenOberon(this, screenGame);
		screenUmbriel = new ScreenUmbriel(this, screenGame);
		screenAriel = new ScreenAriel(this, screenGame);
		screenNeptune = new ScreenNeptune(this, screenGame);
		screenTriton = new ScreenTriton(this, screenGame);
		screenPluto = new ScreenPluto(this, screenGame);
		screenCharon = new ScreenCharon(this, screenGame);
		setScreen(screenMenu);
	}

	@Override
	public void render () {
		super.render();
	}

	@Override
	public void dispose () {
		batch.dispose();
	}
}
