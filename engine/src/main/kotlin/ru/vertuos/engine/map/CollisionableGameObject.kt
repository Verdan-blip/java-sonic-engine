package ru.vertuos.engine.map

import ru.vertuos.engine.math.Vector2f
import ru.vertuos.engine.world.obj.DynamicGameObject
import ru.vertuos.engine.world.obj.GameObject

abstract class CollisionableGameObject(
    position: Vector2f,
    size: Vector2f
) : GameObject() {

    init {
        this.position = position
        this.size = size
    }

    abstract fun onCollide(obj: DynamicGameObject)
}
