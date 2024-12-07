package ru.vertuos.engine.math

import kotlin.math.PI

const val FLOAT_PI = PI.toFloat()

fun Float.toDegrees() = this / FLOAT_PI * 180

fun Float.toRadians() = this * FLOAT_PI / 180
