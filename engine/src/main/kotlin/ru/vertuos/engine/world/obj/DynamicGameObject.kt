package ru.vertuos.engine.world.obj

import ru.vertuos.engine.math.Vector2f

abstract class DynamicGameObject : GameObject() {
    enum class Direction { FORWARD, BACKWARD }
    var groundVelocity: Float = 0f
    var linearVelocity: Vector2f = Vector2f.Zero
    var acceleration: Vector2f = Vector2f.Zero
    var angle: Float = 0f
    var mass: Float = 0f
    var direction: Direction = Direction.FORWARD
    var isOnGround: Boolean = false
}
