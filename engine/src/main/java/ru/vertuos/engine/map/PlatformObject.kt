package ru.vertuos.engine.map

import ru.vertuos.engine.math.Vector2f
import ru.vertuos.engine.util.setLinearVelocityX
import ru.vertuos.engine.util.setLinearVelocityY
import ru.vertuos.engine.util.setPositionX
import ru.vertuos.engine.util.setPositionY
import ru.vertuos.engine.world.obj.DynamicGameObject

class PlatformObject(
    position: Vector2f, size: Vector2f
) : CollisionableGameObject(position, size) {

    override fun onCollide(obj: DynamicGameObject) {
        if (obj.linearVelocity.y < 0f) {
            handleFalling(obj)
            return
        }
        if (obj.linearVelocity.y > 0f) {
            handleJumping(obj)
            return
        }
        if (obj.linearVelocity.x > 0f) {
            obj.setPositionX(obj.position.x - obj.size.x)
            obj.setLinearVelocityX(0f)
            return
        }
        if (obj.linearVelocity.x < 0f) {
            obj.setPositionX(obj.position.x + obj.size.x)
            obj.setLinearVelocityX(0f)
        }
    }

    private fun handleFalling(obj: DynamicGameObject) {
        val deltaY: Float = position.y + size.y - obj.position.y
        if (deltaY < JUMP_ON_COEFFICIENT * size.y) {
            obj.setPositionY(position.y + size.y)
            obj.setLinearVelocityY(0f)
            obj.isOnGround = true
        }
    }

    private fun handleJumping(obj: DynamicGameObject) {

    }

    companion object {
        private const val JUMP_ON_COEFFICIENT = 1 / 8f
    }
}
