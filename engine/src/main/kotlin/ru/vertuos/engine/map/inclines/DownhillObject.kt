package ru.vertuos.engine.map.inclines

import ru.vertuos.engine.math.Vector2f
import kotlin.math.asin

class DownhillObject(
    position: Vector2f, size: Vector2f
) : InclineObject(position, size) {

    override fun angleFunction(x: Float): Float = asin(size.y / size.x)

    override fun yFunction(x: Float): Float {
        val deltaX = x - position.x
        val deltaY = deltaX * size.y / size.x
        return position.y + size.y - deltaY
    }
}
