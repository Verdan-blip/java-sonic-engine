package ru.vertuos.engine.world.updaters.calmer

import ru.vertuos.engine.util.setLinearVelocityX
import ru.vertuos.engine.world.obj.DynamicGameObject
import kotlin.math.abs
import kotlin.math.sign

class VelocityCalmer : Calmer<DynamicGameObject> {

    override fun calm(obj: DynamicGameObject, dt: Float) {
        var velX: Float = obj.linearVelocity.x
        val aX: Float = obj.acceleration.x
        var delta = 0f
        if (isBreaking(velX, aX)) {
            delta = DEC_VALUE * dt
        }
        if (isNotAccelerating(aX)) {
            delta = FRC_VALUE * dt
        }
        if (abs(velX.toDouble()) > delta) {
            velX -= sign(velX) * delta
        } else {
            velX = 0f
        }
        obj.setLinearVelocityX(velX)
    }

    private fun isBreaking(velX: Float, aX: Float): Boolean {
        return aX * velX < 0
    }

    private fun isNotAccelerating(aX: Float): Boolean {
        return abs(aX).compareTo(0f) == 0
    }

    companion object {

        private const val DEC_VALUE = 5f
        private const val FRC_VALUE = 4f
    }
}
