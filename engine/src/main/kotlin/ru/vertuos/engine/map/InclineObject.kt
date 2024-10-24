package ru.vertuos.engine.map

import ru.vertuos.engine.math.Vector2f
import ru.vertuos.engine.util.setPositionY
import ru.vertuos.engine.world.obj.DynamicGameObject

class InclineObject(
    position: Vector2f, size: Vector2f
) : CollisionableGameObject(position, size) {

    override fun onCollide(obj: DynamicGameObject) {
        val deltaX: Float = position.x - obj.position.x
        val deltaY: Float = deltaX * size.y / size.x
        obj.setPositionY(position.y - deltaY)
        obj.isOnGround = true
    }
}
