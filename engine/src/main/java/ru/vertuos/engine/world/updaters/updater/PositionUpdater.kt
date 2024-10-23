package ru.vertuos.engine.world.updaters.updater

import ru.vertuos.engine.math.plus
import ru.vertuos.engine.math.times
import ru.vertuos.engine.world.obj.DynamicGameObject

class PositionUpdater : Updater<DynamicGameObject> {

    override fun update(obj: DynamicGameObject, dt: Float) {
        obj.position += obj.linearVelocity * dt
    }
}
