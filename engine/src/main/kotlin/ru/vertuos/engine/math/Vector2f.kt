package ru.vertuos.engine.math

import java.lang.Float.intBitsToFloat
import kotlin.math.sqrt

@JvmInline
value class Vector2f(private val packedValue: Long) {

    constructor(x: Float, y: Float) : this(
        (x.toBits().toLong() shl 32) or (y.toBits().toLong() and 0xFFFFFFFFL)
    )

    val x: Float get() = intBitsToFloat((packedValue shr 32).toInt())

    val y: Float get() = intBitsToFloat((packedValue and 0xFFFFFFFFL).toInt())

    val length: Float get() = sqrt(x * x + y * y)

    val squaredLength: Float get() = x * x + y * y

    infix fun skew(other: Vector2f): Float = x * other.x - y * other.y

    infix fun dot(other: Vector2f): Float = x * other.x + y * other.y

    fun negate(): Vector2f = Vector2f(-x, -y)

    infix fun proj(axis: Vector2f): Float = dot(axis) / axis.length

    override fun toString(): String = "x: $x, y: $y"

    companion object {

        val Zero = Vector2f(0f, 0f)
    }
}

operator fun Vector2f.plus(other: Vector2f): Vector2f = Vector2f(x = x + other.x, y = y + other.y)

operator fun Vector2f.minus(other: Vector2f): Vector2f = Vector2f(x = x - other.x, y = y - other.y)

operator fun Vector2f.div(number: Float): Vector2f = Vector2f(x = x / number, y = y / number)

operator fun Vector2f.times(number: Float): Vector2f = Vector2f(x = x * number, y = y * number)

val Vector2f.isZero: Boolean get() = isZeroByX && isZeroByY

val Vector2f.isZeroByX: Boolean get() = x compareTo 0f == 0

val Vector2f.isZeroByY: Boolean get() = y compareTo 0f == 0

fun Vector2f.copied(x: Float = this.x, y: Float = this.y): Vector2f =
    Vector2f(x, y)
