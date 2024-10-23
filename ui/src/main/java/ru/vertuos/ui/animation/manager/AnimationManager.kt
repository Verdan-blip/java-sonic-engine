package ru.vertuos.ui.animation.manager

import com.badlogic.gdx.graphics.g2d.Animation
import ru.vertuos.ui.animation.container.AnimationContainer

class AnimationManager<T>(
    private val animationContainer: AnimationContainer<T>
) {

    var currentAnimation: Animation<T>? = null
        private set

    private var accumulatedTime: Float = 0f

    var isFlipped: Boolean = false

    fun selectAnimation(key: String) {
        accumulatedTime = 0f
        currentAnimation = animationContainer.getAnimation(key)
    }

    fun getCurrentFrame(dt: Float): T? {
        accumulatedTime += dt
        return currentAnimation?.getKeyFrame(accumulatedTime)
    }
}
