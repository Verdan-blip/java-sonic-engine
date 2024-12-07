package ru.vertuos.engine.world.obj

import ru.vertuos.engine.math.Vector2f

abstract class DynamicGameObject : GameObject() {
    enum class Direction { FORWARD, BACKWARD }

    open var groundVelocity: Float = 0f
    open var groundAcceleration: Float = 0f
    open var linearVelocity: Vector2f = Vector2f.Zero
    open var acceleration: Vector2f = Vector2f.Zero
    open var angle: Float = 0f
    open var angularVelocity: Float = 0f
    open var mass: Float = 0f
    open var direction: Direction = Direction.FORWARD
    open var isOnGround: Boolean = false
}
