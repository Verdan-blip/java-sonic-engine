package ru.vertuos.ui.contracts

import ru.vertuos.engine.math.Vector2f


private const val METRES_TO_PIXELS = 32f

private const val PIXELS_TO_METRES = 1f / METRES_TO_PIXELS

fun Float.metresToPixels(): Float = this * METRES_TO_PIXELS

fun Float.pixelsToMetres(): Float = this * PIXELS_TO_METRES;

fun Vector2f.metresToPixels(): Vector2f = Vector2f(x.metresToPixels(), y.metresToPixels())

fun Vector2f.pixelsToMetres(): Vector2f = Vector2f(x.pixelsToMetres(), y.pixelsToMetres())
