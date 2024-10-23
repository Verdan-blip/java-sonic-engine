package ru.vertuos.ui.animation.container

import com.badlogic.gdx.graphics.g2d.Animation

class AnimationContainer<T> {

    private val animationMap: MutableMap<String, Animation<T>> = HashMap()

    fun putAnimation(key: String, animation: Animation<T>) {
        animationMap[key] = animation
    }

    fun remove(key: String): Animation<T>? {
        return animationMap.remove(key)
    }

    fun getAnimation(key: String): Animation<T>? {
        return animationMap[key]
    }
}
