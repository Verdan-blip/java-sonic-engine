package ru.vertuos.graphics

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer
import com.badlogic.gdx.maps.tiled.TmxMapLoader
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer
import com.badlogic.gdx.utils.ScreenUtils
import ru.vertuos.engine.hedgehog.sonic.Sonic
import ru.vertuos.engine.hedgehog.sonic.SonicMechanics
import ru.vertuos.engine.math.Vector2f
import ru.vertuos.engine.world.TiledWorld
import ru.vertuos.engine.world.World
import ru.vertuos.graphics.actor.SonicActor
import ru.vertuos.graphics.camera.FollowingCamera
import ru.vertuos.graphics.input.KeyboardInputProcessor
import ru.vertuos.ui.contracts.toMetres
import ru.vertuos.ui.map.MapParsingContract
import ru.vertuos.ui.map.TiledMapParser

class Main : ApplicationAdapter() {

    private lateinit var batch: SpriteBatch

    private lateinit var sonicActor: SonicActor

    private lateinit var followingCamera: FollowingCamera

    private lateinit var tiledMapRenderer: OrthogonalTiledMapRenderer

    private lateinit var world: World

    override fun create() {

        val processor = KeyboardInputProcessor()
        Gdx.input.inputProcessor = processor

        batch = SpriteBatch()

        val tiledMapParser = TiledMapParser()

        val tiledMap = TmxMapLoader().load("maps/hill/hill.tmx")
        val mapProperties = tiledMap.properties
        val mapWidth: Int = mapProperties.get("width", Int::class.java)
        val mapTileWidth: Int = mapProperties.get("tilewidth", Int::class.java)

        val mapHeight: Int = mapProperties.get("height", Int::class.java)
        val mapTileHeight: Int = mapProperties.get("tileheight", Int::class.java)

        val layers = tiledMap.layers
        val mainLayer = layers.get(MapParsingContract.LAYER_MAIN) as TiledMapTileLayer
        val collisionLayer = layers.get(MapParsingContract.LAYER_COLLISION)

        val collisionables = tiledMapParser.parseCollisionObjects(collisionLayer, UNIT_SCALE_TO_512)

        world = TiledWorld()

        val sonic = Sonic()
        sonic.position = Vector2f(100f, 300f).toMetres()

        val sonicMechanics = SonicMechanics(sonic)

        sonicActor = SonicActor(sonic, sonicMechanics, processor)

        for (obj in collisionables) { world.add(obj) }
        world.add(sonic)

        followingCamera = FollowingCamera()
        followingCamera.setToOrtho(false, Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat())
        followingCamera.followingObject = sonic
        followingCamera.boundsWhereToFollow = ru.vertuos.engine.math.Rectangle(
            Vector2f(
                x = 0f,
                y = 0f
            ),
            Vector2f(
                x = mapWidth * mapTileWidth * UNIT_SCALE_TO_512,
                y = mapHeight * mapTileHeight * UNIT_SCALE_TO_512
            )
        )

        followingCamera.update()

        tiledMapRenderer = OrthogonalTiledMapRenderer(tiledMap, UNIT_SCALE_TO_512)
        tiledMapRenderer.setView(followingCamera)
    }

    override fun render() {
        val dt: Float = Gdx.graphics.deltaTime

        sonicActor.processInput()

        world.step(dt)
        sonicActor.updateStates()

        followingCamera.update()

        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f)
        batch.begin()
        tiledMapRenderer.setView(followingCamera)
        tiledMapRenderer.render()
        sonicActor.draw(batch, 1f)
        batch.end()
    }

    override fun dispose() {
        batch.dispose()
        tiledMapRenderer.dispose()
    }

    companion object {

        private const val UNIT_SCALE_TO_512 = 1 / 1f
    }
}
