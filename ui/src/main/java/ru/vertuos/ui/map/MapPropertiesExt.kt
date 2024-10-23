package ru.vertuos.ui.map

import com.badlogic.gdx.maps.MapProperties

val MapProperties.width: Float get() = get("width", Float::class.java)

val MapProperties.height: Float get() = get("height", Float::class.java)

val MapProperties.x: Float get() = get("x", Float::class.java)

val MapProperties.y: Float get() = get("y", Float::class.java)
