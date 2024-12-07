package ru.vertuos.engine.world

import ru.vertuos.engine.math.*
import ru.vertuos.engine.util.setAccelerationY
import ru.vertuos.engine.world.updaters.calmer.Calmer
import ru.vertuos.engine.world.updaters.calmer.GroundVelocityCalmer
import ru.vertuos.engine.world.updaters.gravity.GravityResolver
import ru.vertuos.engine.world.updaters.gravity.VerticalGravityResolver
import ru.vertuos.engine.world.updaters.updater.PositionUpdater
import ru.vertuos.engine.world.updaters.updater.Updater
import ru.vertuos.engine.world.updaters.updater.LinearVelocityUpdater
import ru.vertuos.engine.world.obj.DynamicGameObject
import ru.vertuos.engine.world.updaters.updater.GroundVelocityUpdater
import kotlin.math.cos
import kotlin.math.sign
import kotlin.math.sin

class TiledWorld : World() {

    private val groundVelocityUpdater: Updater<DynamicGameObject> = GroundVelocityUpdater()

    private val linearVelocityUpdater: Updater<DynamicGameObject> = LinearVelocityUpdater()

    private val positionUpdater: Updater<DynamicGameObject> = PositionUpdater()

    private val groundVelocityCalmer: Calmer<DynamicGameObject> = GroundVelocityCalmer()

    private val gravityResolver: GravityResolver<DynamicGameObject> = VerticalGravityResolver()

    override fun step(dt: Float) {
        dynamicGameObjects.forEach { obj ->

            obj.groundVelocity += obj.groundAcceleration * dt

            groundVelocityCalmer.calm(obj, dt)

            if (!obj.isOnGround) {
                obj.angle = 0f
                obj.setAccelerationY(obj.acceleration.y - 15f)
            }

            if (obj.isOnGround) {
                obj.linearVelocity = Vector2f(
                    x = obj.groundVelocity * cos(obj.angle),
                    y = obj.groundVelocity * sin(obj.angle)
                )
            } else {
                obj.linearVelocity += obj.acceleration * dt
            }

            obj.position += obj.linearVelocity * dt

            obj.isOnGround = false
            for (mapObject in mapObjects) {
                if (intersects(obj, mapObject)) {
                    mapObject.onCollide(obj)
                }
            }

            obj.groundAcceleration = 0f
            obj.acceleration = Vector2f.Zero
        }
    }
}
