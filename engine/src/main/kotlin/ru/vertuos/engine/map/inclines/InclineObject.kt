package ru.vertuos.engine.map.inclines

import ru.vertuos.engine.map.CollisionableGameObject
import ru.vertuos.engine.math.Vector2f
import ru.vertuos.engine.math.div
import ru.vertuos.engine.math.looseEqualTo
import ru.vertuos.engine.math.plus
import ru.vertuos.engine.util.setPositionY
import ru.vertuos.engine.world.obj.DynamicGameObject

abstract class InclineObject(
    position: Vector2f,
    size: Vector2f
) : CollisionableGameObject(position, size) {

    abstract fun yFunction(x: Float): Float

    abstract fun angleFunction(x: Float): Float

    override fun onCollide(obj: DynamicGameObject) {
        val point = Vector2f(x = obj.position.x + obj.size.x, y = obj.position.y)
        val yInIncline = yFunction(point.x)
        if (point.x compareTo position.x >= 0 && point.x compareTo (position.x + size.x) <= 0) {
            if (point.y looseEqualTo yInIncline) {
                obj.isOnGround = true
            } else if (point.y compareTo yInIncline < 0) {
                obj.setPositionY(yInIncline)
                obj.angle = angleFunction(point.x)
                obj.isOnGround = true
            }
        }
    }
}
