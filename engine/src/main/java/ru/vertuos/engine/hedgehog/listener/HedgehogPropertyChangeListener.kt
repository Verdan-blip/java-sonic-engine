package ru.vertuos.engine.hedgehog.listener

import ru.vertuos.engine.hedgehog.Hedgehog
import ru.vertuos.engine.world.obj.DynamicGameObject.Direction

interface HedgehogPropertyChangeListener {

    fun onPositionChange(newPosX: Float, newPosY: Float)

    fun onLinearVelocityChange(newVelX: Float, newVelY: Float)

    fun onAccelerationChange(newAccX: Float, newAccY: Float)

    fun onStateChange(newState: Hedgehog.State)

    fun onHpChange(newHp: Float)

    fun onLivesCountChange(newLivesCount: Int)

    fun onRingsCountChange(newRingsCount: Int)

    fun onOnGroundChange(isOnGroundNow: Boolean)

    fun onChangeDirection(dir: Direction)
}
