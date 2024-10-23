package ru.vertuos.ui.contracts

import ru.vertuos.engine.math.Vector2f


private const val METRES_TO_PIXELS = 32f

private const val PIXELS_TO_METRES = 1f / METRES_TO_PIXELS

fun Float.toPixels(): Float = this * METRES_TO_PIXELS

fun Float.toMetres(): Float = this * PIXELS_TO_METRES;

fun Vector2f.toPixels(): Vector2f = Vector2f(x.toPixels(), y.toPixels())

fun Vector2f.toMetres(): Vector2f = Vector2f(x.toMetres(), y.toMetres())
