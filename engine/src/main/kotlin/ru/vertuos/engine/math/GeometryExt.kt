package ru.vertuos.engine.math

import ru.vertuos.engine.world.obj.GameObject
import kotlin.math.cos
import kotlin.math.sin

fun intersects(
    pos1: Vector2f, size1: Vector2f,
    pos2: Vector2f, size2: Vector2f
): Boolean {
    return (pos1.x + size1.x) compareTo pos2.x >= 0 &&
        pos1.x compareTo (pos2.x + size2.x) <= 0 &&
        (pos1.y + size1.y) compareTo pos2.y >= 0 &&
        pos1.y compareTo (pos2.y + size2.y) <= 0
}

fun intersects(
    obj1: GameObject,
    obj2: GameObject
): Boolean {
    return intersects(obj1.position, obj1.size, obj2.position, obj2.size)
}

fun contains(pos: Vector2f, size: Vector2f, point: Vector2f): Boolean {
    return point.x in (pos.x..pos.x + size.x) &&
        point.y in (pos.y..pos.y + size.y)
}

fun rotate(p: Vector2f, p0: Vector2f, angle: Float): Vector2f =
    Vector2f(
        x = (p.x - p0.x) * cos(angle) - (p.y - p0.y) * sin(angle) + p0.x,
        y = (p.x - p0.x) * sin(angle) + (p.y - p0.y) * cos(angle) + p0.y
    )
