package ru.vertuos.engine.map.inclines

import ru.vertuos.engine.math.Vector2f
import kotlin.math.PI
import kotlin.math.cos

class CircledDownhillObject(
    position: Vector2f, size: Vector2f
) : InclineObject(position, size) {

    override fun angleFunction(x: Float): Float = ((position.x - x) / size.x) * (PI.toFloat() / 2)

    override fun yFunction(x: Float): Float {
        val deltaX = x - position.x
        val deltaAngle = -(deltaX / size.x) * (PI.toFloat() / 2)
        val radius = size.y
        val deltaY = radius * (1 - cos(deltaAngle))
        return position.y + size.y - deltaY
    }
}
