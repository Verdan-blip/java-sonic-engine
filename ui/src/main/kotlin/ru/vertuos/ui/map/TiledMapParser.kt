package ru.vertuos.ui.map

import com.badlogic.gdx.maps.MapLayer
import com.badlogic.gdx.maps.MapProperties
import com.badlogic.gdx.math.Rectangle
import ru.vertuos.engine.map.CollisionableGameObject
import ru.vertuos.engine.map.inclines.CircledDownhillObject
import ru.vertuos.engine.map.inclines.CircledUphillObject
import ru.vertuos.engine.map.inclines.DownhillObject
import ru.vertuos.engine.map.inclines.UphillObject
import ru.vertuos.engine.map.obstacles.PlatformObject
import ru.vertuos.engine.math.Vector2f
import ru.vertuos.ui.contracts.pixelsToMetres
import java.util.ArrayList

class TiledMapParser {
    fun parseCollisionObjects(
        collisionLayer: MapLayer,
        unitScale: Float
    ): List<CollisionableGameObject> {

        val gdxMapObjects = collisionLayer.objects
        val collisionables = ArrayList<CollisionableGameObject>(gdxMapObjects.count)

        for (gdxMapObject in gdxMapObjects) {
            val bounds = extractBounds(gdxMapObject.properties)
            scale(bounds, unitScale)
            pixelsRectangleToMetresRectangle(bounds)
            bounds.apply {
                when (gdxMapObject.name) {
                    MapParsingContract.NAME_PLATFORM -> collisionables.add(
                        PlatformObject(Vector2f(x, y), Vector2f(width, height))
                    )
                    MapParsingContract.NAME_UPHILL -> collisionables.add(
                        UphillObject(Vector2f(x, y), Vector2f(width, height))
                    )
                    MapParsingContract.NAME_DOWNHILL -> collisionables.add(
                        DownhillObject(Vector2f(x, y), Vector2f(width, height))
                    )
                    MapParsingContract.NAME_CIRCLED_UPHILL -> collisionables.add(
                        CircledUphillObject(Vector2f(x, y), Vector2f(width, height))
                    )
                    MapParsingContract.NAME_CIRCLED_DOWNHILL -> collisionables.add(
                        CircledDownhillObject(Vector2f(x, y), Vector2f(width, height))
                    )
                    MapParsingContract.NAME_HYPERBOLIC_UPHILL -> collisionables.add(
                        UphillObject(Vector2f(x, y), Vector2f(width, height))
                    )
                    MapParsingContract.NAME_HYPERBOLIC_DOWNHILL -> collisionables.add(
                        DownhillObject(Vector2f(x, y), Vector2f(width, height))
                    )
                    else -> Unit
                }
            }
        }
        return collisionables
    }

    private fun extractBounds(properties: MapProperties): Rectangle = Rectangle(
        properties.x, properties.y, properties.width, properties.height
    )

    private fun pixelsRectangleToMetresRectangle(outRect: Rectangle) {
        outRect.x = outRect.x.pixelsToMetres()
        outRect.y = outRect.y.pixelsToMetres()
        outRect.width = outRect.width.pixelsToMetres()
        outRect.height = outRect.height.pixelsToMetres()
    }

    private fun scale(outRect: Rectangle, scale: Float) {
        outRect.x *= scale
        outRect.y *= scale
        outRect.width *= scale
        outRect.height *= scale
    }
}
