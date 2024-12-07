package ru.vertuos.engine.math

import kotlin.math.abs

private const val LOOSE_EPSILON = 0.1f

infix fun Float.looseEqualTo(other: Float): Boolean =
    abs(this - other) < LOOSE_EPSILON
