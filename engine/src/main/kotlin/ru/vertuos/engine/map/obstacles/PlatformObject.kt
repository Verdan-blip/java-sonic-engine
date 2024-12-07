package ru.vertuos.engine.map.obstacles

import ru.vertuos.engine.map.CollisionableGameObject
import ru.vertuos.engine.math.Vector2f
import ru.vertuos.engine.util.setLinearVelocityY
import ru.vertuos.engine.util.setPositionY
import ru.vertuos.engine.world.obj.DynamicGameObject

class PlatformObject(
    position: Vector2f,
    size: Vector2f
) : CollisionableGameObject(position, size) {

    override fun onCollide(obj: DynamicGameObject) {
        if (obj.linearVelocity.y compareTo 0f <= 0) {
            handleFalling(obj)
        }
    }

    private fun handleFalling(obj: DynamicGameObject) {
        val deltaY = position.y + size.y - obj.position.y
        if (deltaY < JUMP_ON_COEFFICIENT * size.y) {
            obj.setPositionY(position.y + size.y)
            obj.setLinearVelocityY(0f)
            obj.angle = 0f
            obj.isOnGround = true
        }
    }

    companion object {
        private const val JUMP_ON_COEFFICIENT = 1 / 2f
    }
}
