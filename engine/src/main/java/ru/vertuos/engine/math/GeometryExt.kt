package ru.vertuos.engine.math

import ru.vertuos.engine.world.obj.GameObject

fun intersects(
    pos1: Vector2f, size1: Vector2f,
    pos2: Vector2f, size2: Vector2f
): Boolean {
    return pos1.x + size1.x > pos2.x &&
        pos1.x < pos2.x + size2.x &&
        pos1.y + size1.y > pos2.y &&
        pos1.y < pos2.y + size2.y
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
