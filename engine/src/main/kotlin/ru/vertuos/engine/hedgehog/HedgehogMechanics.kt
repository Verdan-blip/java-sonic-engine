package ru.vertuos.engine.hedgehog

import ru.vertuos.engine.math.Vector2f
import ru.vertuos.engine.math.isZero
import ru.vertuos.engine.math.isZeroByX
import ru.vertuos.engine.physics.DynamicObjectPhysics
import ru.vertuos.engine.util.absoluteVelocity
import ru.vertuos.engine.world.obj.DynamicGameObject

abstract class HedgehogMechanics<H : Hedgehog, C>(hedgehog: H) : DynamicObjectPhysics<H>(hedgehog) {

    private var accumulatedImpulse: Float = 0f
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
                    applyForceToCenter(Vector2f(x = FORCE_TO_MOVE, y = 0f))
                    obj.direction = DynamicGameObject.Direction.FORWARD
                }
            }
            CommonCommand.GO_BACKWARD -> {
                if (obj.state != Hedgehog.State.SPIN || obj.isJumped) {
                    applyForceToCenter(Vector2f(x = -FORCE_TO_MOVE, y = 0f))
                    obj.direction = DynamicGameObject.Direction.BACKWARD
                }
            }
            CommonCommand.HIDE -> {
                if (obj.linearVelocity.isZeroByX) {
                    obj.state = Hedgehog.State.DUCK
                } else {
                    obj.state = Hedgehog.State.SPIN
                }
            }
            CommonCommand.JUMP -> {
                if (obj.isOnGround) {
                    applyImpulseYToCenter(IMPULSE_TO_JUMP)
                    obj.state = Hedgehog.State.SPIN
                    obj.isJumped = true
                }
            }
            CommonCommand.ACCELERATE_BY_SPINNING -> {
                if (obj.absoluteVelocity.isZero) {
                    accumulatedImpulse *= IMPULSE_INCREASE_FACTOR
                    obj.state = Hedgehog.State.SPINDASH
                }
            }
            CommonCommand.DASH -> {
                if (obj.direction === DynamicGameObject.Direction.FORWARD) {
                    applyLinearImpulseToCenter(Vector2f(x = accumulatedImpulse, y = 0f))
                } else {
                    applyLinearImpulseToCenter(Vector2f(x = -accumulatedImpulse, y = 0f))
                }
                accumulatedImpulse = 0f
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

        const val MAX_WALK_VELOCITY_VALUE: Float = 16f
        const val MAX_IDLE_VELOCITY_X_VALUE: Float = 0.01f
        const val MAX_IDLE_VELOCITY_Y_VALUE: Float = 0.01f
        const val MAX_VELOCITY_VALUE: Float = 20f

        const val FORCE_TO_MOVE: Float = 175f
        const val IMPULSE_TO_JUMP: Float = 350f
        const val IMPULSE_INCREASE_FACTOR: Float = 2f
        const val IMPULSE_MAX_VALUE: Float = 800f
        const val IMPULSE_MIN_VALUE: Float = 200f
    }
}
