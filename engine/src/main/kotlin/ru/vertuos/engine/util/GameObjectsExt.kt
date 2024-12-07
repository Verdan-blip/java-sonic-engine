package ru.vertuos.engine.util

import ru.vertuos.engine.math.Vector2f
import ru.vertuos.engine.math.copied
import ru.vertuos.engine.world.obj.DynamicGameObject
import ru.vertuos.engine.world.obj.GameObject
import kotlin.math.abs

val DynamicGameObject.absoluteVelocity: Vector2f get() =
    Vector2f(abs(linearVelocity.x), abs(linearVelocity.y))

val DynamicGameObject.absoluteAcceleration: Vector2f get() =
    Vector2f(abs(acceleration.x), abs(acceleration.y))

val DynamicGameObject.absoluteVelocityX: Float get() =
    abs(linearVelocity.x)

val DynamicGameObject.absoluteVelocityY: Float get() =
    abs(linearVelocity.y)

val DynamicGameObject.absoluteAccelerationX: Float get() =
    abs(acceleration.x)

val DynamicGameObject.absoluteAccelerationY: Float get() =
    abs(acceleration.y)

fun GameObject.setPositionX(x: Float) {
    position = position.copied(x = x)
}

fun GameObject.setPositionY(y: Float) {
    position = position.copied(y = y)
}

fun DynamicGameObject.setLinearVelocityX(x: Float) {
    linearVelocity = linearVelocity.copied(x = x)
}

fun DynamicGameObject.setLinearVelocityY(y: Float) {
    linearVelocity = linearVelocity.copied(y = y)
}

fun DynamicGameObject.setAccelerationX(x: Float) {
    acceleration = acceleration.copied(x = x)
}

fun DynamicGameObject.setAccelerationY(y: Float) {
    acceleration = acceleration.copied(y = y)
}
