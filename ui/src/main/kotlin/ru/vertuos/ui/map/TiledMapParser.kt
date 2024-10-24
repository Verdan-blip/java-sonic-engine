package ru.vertuos.ui.map

import com.badlogic.gdx.maps.MapLayer
import com.badlogic.gdx.maps.MapProperties
import com.badlogic.gdx.math.Rectangle
import ru.vertuos.engine.map.CollisionableGameObject
import ru.vertuos.engine.map.InclineObject
import ru.vertuos.engine.map.PlatformObject
import ru.vertuos.engine.math.Vector2f
import ru.vertuos.ui.contracts.toMetres
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
                    MapParsingContract.NAME_INCLINE -> collisionables.add(
                        InclineObject(Vector2f(x, y), Vector2f(width, height))
                    )
                    MapParsingContract.NAME_CIRCLED_INCLINE -> Unit
                    MapParsingContract.NAME_HYPERBOLIC_INCLINE -> Unit
                    else -> {}
                }
            }
        }
        return collisionables
    }

    private fun extractBounds(properties: MapProperties): Rectangle = Rectangle(
        properties.x, properties.y, properties.width, properties.height
    )

    private fun pixelsRectangleToMetresRectangle(outRect: Rectangle) {
        outRect.x = outRect.x.toMetres()
        outRect.y = outRect.y.toMetres()
        outRect.width = outRect.width.toMetres()
        outRect.height = outRect.height.toMetres()
    }

    private fun scale(outRect: Rectangle, scale: Float) {
        outRect.x *= scale
        outRect.y *= scale
        outRect.width *= scale
        outRect.height *= scale
    }
}
