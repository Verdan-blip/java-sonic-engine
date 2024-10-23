package ru.vertuos.engine.world

import ru.vertuos.engine.math.Vector2f
import ru.vertuos.engine.math.intersects
import ru.vertuos.engine.world.updaters.calmer.Calmer
import ru.vertuos.engine.world.updaters.calmer.VelocityCalmer
import ru.vertuos.engine.world.updaters.gravity.GravityResolver
import ru.vertuos.engine.world.updaters.gravity.VerticalGravityResolver
import ru.vertuos.engine.world.updaters.updater.PositionUpdater
import ru.vertuos.engine.world.updaters.updater.Updater
import ru.vertuos.engine.world.updaters.updater.VelocityUpdater
import ru.vertuos.engine.world.obj.DynamicGameObject

class TiledWorld : World() {

    private val velocityUpdater: Updater<DynamicGameObject> = VelocityUpdater()

    private val positionUpdater: Updater<DynamicGameObject> = PositionUpdater()

    private val velocityCalmer: Calmer<DynamicGameObject> = VelocityCalmer()

    private val gravityResolver: GravityResolver<DynamicGameObject> = VerticalGravityResolver()

    override fun step(dt: Float) {
        dynamicGameObjects.forEach { obj ->

            velocityCalmer.calm(obj, dt)
            gravityResolver.resolve(obj, dt)

            velocityUpdater.update(obj, dt)
            positionUpdater.update(obj, dt)

            obj.isOnGround = false

            for (mapObject in mapObjects) {
                if (intersects(obj, mapObject)) {
                    mapObject.onCollide(obj)
                }
            }

            obj.acceleration = Vector2f.Zero
        }
    }
}
