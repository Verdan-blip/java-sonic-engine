package ru.vertuos.engine.world.updaters.calmer

import ru.vertuos.engine.util.setLinearVelocityX
import ru.vertuos.engine.world.obj.DynamicGameObject
import kotlin.math.abs
import kotlin.math.sign

class GroundVelocityCalmer : Calmer<DynamicGameObject> {

    override fun calm(obj: DynamicGameObject, dt: Float) {
        var groundVel = obj.groundVelocity
        val groundAcc = obj.groundAcceleration
        var delta = 0f

        if (isBreaking(groundVel, obj.direction)) {
            delta = DEC_VALUE * dt
        }
        if (isNotAccelerating(groundAcc)) {
            delta = FRC_VALUE * dt
        }
        if (abs(groundVel.toDouble()) > delta) {
            groundVel -= sign(groundVel) * delta
        } else {
            groundVel = 0f
        }
        obj.groundVelocity = groundVel
    }

    private fun isBreaking(velX: Float, dir: DynamicGameObject.Direction): Boolean {
        return when (dir) {
            DynamicGameObject.Direction.FORWARD -> velX > 0
            DynamicGameObject.Direction.BACKWARD -> velX < 0
        }
    }

    private fun isNotAccelerating(groundAcc: Float): Boolean {
        return abs(groundAcc).compareTo(0f) == 0
    }

    companion object {

        private const val DEC_VALUE = 5f
        private const val FRC_VALUE = 4f
    }
}
