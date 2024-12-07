package ru.vertuos.engine.world.updaters.updater

import ru.vertuos.engine.math.Vector2f
import ru.vertuos.engine.world.obj.DynamicGameObject
import kotlin.math.cos
import kotlin.math.sin

class LinearVelocityUpdater : Updater<DynamicGameObject> {

    override fun update(obj: DynamicGameObject, dt: Float) {
        obj.linearVelocity = Vector2f(
            x = obj.groundVelocity * cos(obj.angle),
            y = obj.groundVelocity * sin(obj.angle)
        )
    }
}
