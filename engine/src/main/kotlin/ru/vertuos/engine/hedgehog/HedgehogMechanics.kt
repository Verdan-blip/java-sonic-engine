package ru.vertuos.engine.hedgehog

import ru.vertuos.engine.math.*
import ru.vertuos.engine.physics.DynamicObjectPhysics
import ru.vertuos.engine.util.absoluteVelocity
import ru.vertuos.engine.world.obj.DynamicGameObject
import kotlin.math.cos
import kotlin.math.sin

abstract class HedgehogMechanics<H : Hedgehog, C>(hedgehog: H) : DynamicObjectPhysics<H>(hedgehog) {

    private var accumulatedImpulse: Float = IMPULSE_MIN_VALUE
        set(value) {
            if (value compareTo IMPULSE_MAX_VALUE <= 0) {
                field = value
            }
        }

    enum class CommonCommand {
        GO_FORWARD,
        GO_BACKWARD,
        JUMP,
        ACCELERATE_BY_SPINNING,
        HIDE,
        DASH
    }

    abstract fun sendSpecialCommand(specialCommand: C)

    fun sendCommand(command: CommonCommand) {
        when (command) {
            CommonCommand.GO_FORWARD -> {
                if (obj.state != Hedgehog.State.SPIN || obj.isJumped) {
                    obj.groundAcceleration += ACCELERATION_VALUE
                    obj.direction = DynamicGameObject.Direction.FORWARD
                }
            }
            CommonCommand.GO_BACKWARD -> {
                if (obj.state != Hedgehog.State.SPIN || obj.isJumped) {
                    obj.groundAcceleration -= ACCELERATION_VALUE
                    obj.direction = DynamicGameObject.Direction.BACKWARD
                }
            }
            CommonCommand.HIDE -> {
                obj.state = if (obj.linearVelocity.isZeroByX) {
                    Hedgehog.State.DUCK
                } else {
                    Hedgehog.State.SPIN
                }
            }
            CommonCommand.JUMP -> {
                if (obj.isOnGround) {
                    obj.linearVelocity += Vector2f(
                        x = JUMP_VALUE * -sin(obj.angle),
                        y = JUMP_VALUE * cos(obj.angle)
                    )
                    obj.state = Hedgehog.State.SPIN
                    obj.isJumped = true
                    obj.isOnGround = false
                }
            }
            CommonCommand.ACCELERATE_BY_SPINNING -> {
                if (obj.absoluteVelocity.isZero) {
                    accumulatedImpulse *= IMPULSE_INCREASE_FACTOR
                    obj.state = Hedgehog.State.SPINDASH
                }
            }
            CommonCommand.DASH -> {
                if (obj.direction == DynamicGameObject.Direction.FORWARD) {
                    obj.groundVelocity = accumulatedImpulse
                } else {
                    obj.groundVelocity = -accumulatedImpulse
                }
                accumulatedImpulse = IMPULSE_MIN_VALUE
                obj.state = Hedgehog.State.SPIN
            }
        }
    }

    fun updateStates() {
        updateStatesByVelocities(obj.absoluteVelocity)
    }

    private fun updateStatesByVelocities(absVel: Vector2f) {
        if (obj.state == Hedgehog.State.DUCK) {
            return
        }
        if (obj.state == Hedgehog.State.SPINDASH) {
            return
        }
        if (obj.isOnGround) {
            if (obj.state == Hedgehog.State.SPIN) {
                if (obj.isJumped) {
                    handleDefaultStateByVelocity(absVel)
                    obj.isJumped = false
                } else {
                    if (absVel.isZero) {
                        obj.state = Hedgehog.State.IDLE
                    }
                }
            } else {
                handleDefaultStateByVelocity(absVel)
            }
        }
    }

    private fun handleDefaultStateByVelocity(absVel: Vector2f) {
        obj.state = if (absVel.x compareTo MAX_IDLE_VELOCITY_X_VALUE < 0) {
            Hedgehog.State.IDLE
        } else if (absVel.x compareTo MAX_WALK_VELOCITY_VALUE < 0) {
            Hedgehog.State.WALK
        } else {
            Hedgehog.State.RUN
        }
    }

    companion object {

        const val MAX_WALK_VELOCITY_VALUE = 16f
        const val MAX_IDLE_VELOCITY_X_VALUE = 0.01f
        const val MAX_IDLE_VELOCITY_Y_VALUE = 0.01f
        const val MAX_VELOCITY_VALUE = 20f

        const val FORCE_TO_MOVE = 175f
        const val ACCELERATION_VALUE = 10f
        const val JUMP_VALUE = 10f
        const val IMPULSE_INCREASE_FACTOR = 2f

        const val IMPULSE_MAX_VALUE = 50f
        const val IMPULSE_MIN_VALUE = 10f
    }
}
