package ru.vertuos.engine.world

import ru.vertuos.engine.map.CollisionableGameObject
import ru.vertuos.engine.world.obj.DynamicGameObject
import ru.vertuos.engine.world.obj.GameObject

abstract class World {

    protected val mapObjects: MutableList<CollisionableGameObject> = mutableListOf()

    protected val dynamicGameObjects: MutableList<DynamicGameObject> = mutableListOf()

    fun add(obj: GameObject) {
        if (obj is CollisionableGameObject) {
            mapObjects.add(obj)
        } else if (obj is DynamicGameObject) {
            dynamicGameObjects.add(obj)
        }
    }

    abstract fun step(dt: Float)
}
