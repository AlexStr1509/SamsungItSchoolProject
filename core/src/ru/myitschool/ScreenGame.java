package ru.myitschool;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
public class ScreenGame implements Screen {
    public static final int SCR_WIDTH=Gdx.graphics.getWidth(), SCR_HEIGHT=Gdx.graphics.getHeight();
    public PerspectiveCamera camera;
    public CameraInputController cameraInputController;
    public ModelBatch modelBatch;
    public AssetManager assets;
    public Environment environment;
    public BitmapFont font;
    public RealButton btnBack, btnSearch, btnSun;
    public Texture imgBack, imgSearch, imgSun;
    public ModelInstance space, sun, mercury, venus, earth, moon, mars,  jupiter, saturn, uran, neptun, pluton;
    public ModelInstance phobos, deymos, ganymede, calisto, europa, io, titan, rhea, yapet, diona, encelad, tefeya, triton, titanya, oberon, umbriel, ariel,  haron;
    public Array<ModelInstance> instances = new Array<ModelInstance>();
    public boolean loading;
    final MyGame game;
    ScreenGame(MyGame game) {
        this.game = game;
        modelBatch = new ModelBatch();
        font = new BitmapFont();
        environment = new Environment();
        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 0.4f));
        environment.add(new DirectionalLight().set(Color.WHITE, 0f, -1f, 0f));
        camera = new PerspectiveCamera(67, SCR_WIDTH, SCR_HEIGHT);
        camera.position.set(1f, 50f, 80f);
        camera.lookAt(0f, 0f, 0f);
        camera.far = 100000f;
        camera.update();
        cameraInputController = new CameraInputController(camera);
        Gdx.input.setInputProcessor(cameraInputController);
        assets = new AssetManager();
        assets.load("space.g3db", Model.class);
        assets.load("sun.g3db", Model.class);
        assets.load("mercury.g3db", Model.class);
        assets.load("venus.g3db", Model.class);
        assets.load("earth.g3db", Model.class);
        assets.load("moon.g3db", Model.class);
        assets.load("mars.g3db", Model.class);
        assets.load("phobos.g3db", Model.class);
        assets.load("deymos.g3db", Model.class);
        assets.load("jupiter.g3db", Model.class);
        assets.load("saturn.g3db", Model.class);
        assets.load("uran.g3db", Model.class);
        assets.load("neptun.g3db", Model.class);
        assets.load("pluton.g3db", Model.class);
        //
        assets.load("ganymede.g3db", Model.class);
        assets.load("calisto.g3db", Model.class);
        assets.load("europa.g3db", Model.class);
        assets.load("io.g3db", Model.class);
        //
        assets.load("titan.g3db", Model.class);
        assets.load("rhea.g3db", Model.class);
        assets.load("yapet.g3db", Model.class);
        assets.load("diona.g3db", Model.class);
        assets.load("tefeya.g3db", Model.class);
        assets.load("encelad.g3db", Model.class);
        //
        assets.load("titanya.g3db", Model.class);
        assets.load("oberon.g3db", Model.class);
        assets.load("umbriel.g3db", Model.class);
        assets.load("ariel.g3db", Model.class);
        //
        assets.load("triton.g3db", Model.class);
        //
        assets.load("haron.g3db", Model.class);
        loading = true;
        assets.finishLoading();

        btnBack = new RealButton(50f, 50f, 100f);
        btnSearch = new RealButton(50f, 200f, 100f);
        btnSun = new RealButton(50f, 350f, 100f);
        imgBack = new Texture("imgExit.png");
        imgSearch = new Texture("imgSearch.png");
        imgSun = new Texture("imgSun.png");
    }
    public void SolarSystemLoading(){
         space = new ModelInstance(assets.get("space.g3db", Model.class));
         instances.add(space);
         sun = new ModelInstance((assets.get("sun.g3db", Model.class)));
         sun.transform.trn(0f,0f,0f);
         instances.add(sun);
         mercury = new ModelInstance((assets.get("mercury.g3db", Model.class)));
         mercury.transform.trn(20f, 0f, 0f);
         instances.add(mercury);
         venus = new ModelInstance(assets.get("venus.g3db", Model.class));
         venus.transform.setToRotation(Vector3.Y, 177.3f).trn(-30f, 0f , -10f);
         instances.add(venus);
         earth = new ModelInstance(assets.get("earth.g3db", Model.class));
         earth.transform.setToRotation(Vector3.Y, 180f).setToRotation(Vector3.X,180f).trn(40f, 0f,-15f);
         instances.add(earth);
         moon = new ModelInstance(assets.get("moon.g3db", Model.class));
         moon.transform.trn(40f, 0f, -12.5f);
         instances.add(moon);
         mars = new ModelInstance(assets.get("mars.g3db", Model.class));
         mars.transform.setToRotation(Vector3.Y, 25.19f).trn(0f, 0f ,40f);
         instances.add(mars);
         phobos = new ModelInstance(assets.get("phobos.g3db", Model.class));
         phobos.transform.trn(0f, 0f,38f);
         instances.add(phobos);
         deymos = new ModelInstance(assets.get("deymos.g3db", Model.class));
         deymos.transform.setToRotation(Vector3.X, 180).trn(0f, 2f,42f);
         instances.add(deymos);
         jupiter = new ModelInstance(assets.get("jupiter.g3db", Model.class));
         jupiter.transform.setToRotation(Vector3.X, 180f).trn(-80f, 0f,15f);
         instances.add(jupiter);
         saturn = new ModelInstance(assets.get("saturn.g3db", Model.class));
         saturn.transform.setToRotation(Vector3.Y,26.73f).trn(95f,0f ,0f);
         instances.add(saturn);
         uran = new ModelInstance(assets.get("uran.g3db", Model.class));
         uran.transform.setToRotation(Vector3.X,180f).trn(50f,-1.5f,-90f);
         instances.add(uran);
         neptun = new ModelInstance(assets.get("neptun.g3db", Model.class));
         neptun.transform.trn(0f,0f,130f);
         instances.add(neptun);
         pluton = new ModelInstance(assets.get("pluton.g3db", Model.class));
         pluton.transform.trn(150f, 0f, 0f);
         instances.add(pluton);
         ganymede = new ModelInstance(assets.get("ganymede.g3db", Model.class));
         ganymede.transform.trn(-80f, 0f, 21f);
         instances.add(ganymede);
         calisto = new ModelInstance(assets.get("calisto.g3db", Model.class));
         calisto.transform.trn(-80f, 6f, 15f);
         instances.add(calisto);
         europa = new ModelInstance(assets.get("europa.g3db", Model.class));
         europa.transform.trn(-80f, 0f, 9f);
         instances.add(europa);
         io = new ModelInstance(assets.get("io.g3db", Model.class));
         io.transform.trn(-80f, -6f, 9f);
         instances.add(io);
         titan = new ModelInstance(assets.get("titan.g3db", Model.class));
         titan.transform.trn(95f, 6f, 0f);
         instances.add(titan);
         rhea = new ModelInstance(assets.get("rhea.g3db", Model.class));
         rhea.transform.trn(95f, -6f, 0f);
         instances.add(rhea);
         yapet = new ModelInstance(assets.get("yapet.g3db", Model.class));
         yapet.transform.trn(95f, 2f, 6f);
         instances.add(yapet);
         diona = new ModelInstance(assets.get("diona.g3db", Model.class));
         diona.transform.trn(95f, 2f, -6f);
         instances.add(diona);
         tefeya = new ModelInstance(assets.get("tefeya.g3db", Model.class));
         tefeya.transform.trn(95f, 2f, 0f);
         instances.add(tefeya);
         encelad = new ModelInstance(assets.get("encelad.g3db", Model.class));
         encelad.transform.trn(90f, -3f, 0f);
         instances.add(encelad);
         titanya = new ModelInstance(assets.get("titanya.g3db", Model.class));
         titanya.transform.trn(50f, -5f, -84f);
         instances.add(titanya);
         oberon = new ModelInstance(assets.get("oberon.g3db", Model.class));
         oberon.transform.trn(50f, 0f, -96f);
         instances.add(oberon);
         umbriel = new ModelInstance(assets.get("umbriel.g3db", Model.class));
         umbriel.transform.trn(50f, -6f, -90f);
         instances.add(umbriel);
         ariel = new ModelInstance(assets.get("ariel.g3db", Model.class));
         ariel.transform.trn(50f, 6f, -90f);
         instances.add(ariel);
         haron = new ModelInstance(assets.get("haron.g3db", Model.class));
         haron.transform.trn(150f, 0f, 2f);
         instances.add(haron);
         triton = new ModelInstance(assets.get("triton.g3db", Model.class));
         triton.transform.trn(0f, 0f, 124f);
         instances.add(triton);
         loading = false;
    }
    @Override
    public void show() { }
    @Override
    public void render(float delta) {
        if(loading && assets.update()){
            SolarSystemLoading();
        }
        cameraInputController.update();
        Gdx.gl.glViewport(0,0, SCR_WIDTH, SCR_HEIGHT);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        modelBatch.begin(camera);
        if(space!=null){
            modelBatch.render(instances);
        }
        modelBatch.end();
        if(Gdx.input.justTouched()){
            game.touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            game.orthographicCamera.unproject(game.touch);
            if(btnBack.hit(game.touch.x, game.touch.y)) {
                game.setScreen(game.screenMenu);
                camera.position.set(1f, 50f, 80f);
                camera.lookAt(0f, 0f, 0f);
            }
            if(btnSearch.hit(game.touch.x, game.touch.y)) {
                game.setScreen(game.screenSearch);
                camera.position.set(1f, 50f, 80f);
                camera.lookAt(0f, 0f, 0f);
            }
            if(btnSun.hit(game.touch.x, game.touch.y)){
                game.setScreen(game.screenSun);
            }
        }
        game.orthographicCamera.update();
        game.batch.setProjectionMatrix(game.orthographicCamera.combined);
        game.batch.begin();
        game.batch.draw(imgBack, btnBack.x, btnBack.y);
        game.batch.draw(imgSearch, btnSearch.x,btnSearch.y);
        game.batch.draw(imgSun, btnSun.x, btnSun.y);
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
        modelBatch.dispose();
        assets.dispose();
        instances.clear();
        imgBack.dispose();
        imgSearch.dispose();
        imgSun.dispose();
        font.dispose();
    }
}
