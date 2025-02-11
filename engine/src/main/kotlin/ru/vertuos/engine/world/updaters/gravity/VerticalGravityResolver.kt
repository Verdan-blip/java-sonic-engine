package ru.vertuos.engine.world.updaters.gravity

import ru.vertuos.engine.util.setLinearVelocityY
import ru.vertuos.engine.world.obj.DynamicGameObject

class VerticalGravityResolver : GravityResolver<DynamicGameObject> {

    override fun resolve(obj: DynamicGameObject, dt: Float) {
        if (!obj.isOnGround) {
            obj.setLinearVelocityY(obj.linearVelocity.y - G * obj.mass * dt)
        }
    }

    companion object {

        private const val G = 15f
    }
}
