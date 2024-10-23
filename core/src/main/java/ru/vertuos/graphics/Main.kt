package ru.vertuos.graphics;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;
import ru.vertuos.engine.hedgehog.sonic.Sonic;
import ru.vertuos.engine.hedgehog.sonic.SonicMechanics;
import ru.vertuos.engine.map.object.CollisionableGameObject;
import ru.vertuos.engine.world.World;
import ru.vertuos.engine.world.TiledWorld;
import ru.vertuos.graphics.actor.SonicActor;
import ru.vertuos.graphics.camera.FollowingCamera;
import ru.vertuos.graphics.input.KeyboardInputProcessor;
import ru.vertuos.ui.contracts.DimensionUtils;
import ru.vertuos.ui.map.MapParsingContract;
import ru.vertuos.ui.map.TiledMapParser;

import java.util.List;


public class Main extends ApplicationAdapter {

    private static final float UNIT_SCALE_TO_512 = 1 / 1f;

    private SpriteBatch batch;

    private SonicActor sonicActor;

    private FollowingCamera followingCamera;
    private OrthogonalTiledMapRenderer tiledMapRenderer;

    private World world;

    @Override
    public void create() {
        KeyboardInputProcessor processor = new KeyboardInputProcessor();
        Gdx.input.setInputProcessor(processor);

        batch = new SpriteBatch();

        TiledMapParser tiledMapParser = new TiledMapParser();

        TiledMap tiledMap = new TmxMapLoader().load("maps/hill/hill.tmx");
        MapProperties mapProperties = tiledMap.getProperties();
        int mapWidth = mapProperties.get("width", Integer.class);
        int mapTileWidth = mapProperties.get("tilewidth", Integer.class);

        int mapHeight = mapProperties.get("height", Integer.class);
        int mapTileHeight = mapProperties.get("tileheight", Integer.class);

        MapLayers layers = tiledMap.getLayers();
        TiledMapTileLayer mainLayer = (TiledMapTileLayer) layers.get(MapParsingContract.LAYER_MAIN);
        MapLayer collisionLayer = layers.get(MapParsingContract.LAYER_COLLISION);

        List<CollisionableGameObject> collisionables = tiledMapParser
            .parseCollisionObjects(collisionLayer, UNIT_SCALE_TO_512);

        world = new TiledWorld();

        Sonic sonic = new Sonic();
        sonic.setPositionX(DimensionUtils.pixelsToMetres(100f));
        sonic.setPositionY(DimensionUtils.pixelsToMetres(300f));

        SonicMechanics sonicMechanics = new SonicMechanics(sonic);

        sonicActor = new SonicActor(sonic, sonicMechanics, processor);

        for (CollisionableGameObject object : collisionables) { world.add(object); }
        world.add(sonic);

        followingCamera = new FollowingCamera();
        followingCamera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        followingCamera.setFollowingObject(sonic);
        followingCamera.setBoundsWhereToFollow(
            new Rectangle(
                0, 0,
                mapWidth * mapTileWidth * UNIT_SCALE_TO_512,
                mapHeight * mapTileHeight * UNIT_SCALE_TO_512
            )
        );
        followingCamera.update();

        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap, UNIT_SCALE_TO_512);
        tiledMapRenderer.setView(followingCamera);
    }

    @Override
    public void render() {
        float dt = Gdx.graphics.getDeltaTime();
        sonicActor.processInput();
        world.step(dt);
        sonicActor.updateStates();
        followingCamera.update(batch);

        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        batch.begin();
        tiledMapRenderer.setView(followingCamera);
        tiledMapRenderer.render();
        sonicActor.draw(batch, 1f);
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        tiledMapRenderer.dispose();
    }
}
