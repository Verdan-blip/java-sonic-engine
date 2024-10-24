package ru.vertuos.engine.math

class Rectangle(
    val position: Vector2f,
    val size: Vector2f
)

fun Rectangle.contains(point: Vector2f): Boolean {
    return contains(position, size, point)
}
