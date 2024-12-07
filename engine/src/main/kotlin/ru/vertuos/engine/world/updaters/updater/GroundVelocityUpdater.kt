package ru.vertuos.engine.world.updaters.updater

import ru.vertuos.engine.world.obj.DynamicGameObject

class GroundVelocityUpdater : Updater<DynamicGameObject> {

    override fun update(obj: DynamicGameObject, dt: Float) {
        obj.groundVelocity += obj.groundAcceleration * dt
    }
}
