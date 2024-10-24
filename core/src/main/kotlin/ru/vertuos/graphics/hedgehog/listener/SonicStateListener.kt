package ru.vertuos.graphics.hedgehog.listener

import com.badlogic.gdx.graphics.g2d.TextureRegion
import ru.vertuos.engine.hedgehog.Hedgehog
import ru.vertuos.engine.hedgehog.listener.HedgehogPropertyChangeListener
import ru.vertuos.engine.world.obj.DynamicGameObject.Direction
import ru.vertuos.ui.animation.contracts.HedgehogAnimationContract
import ru.vertuos.ui.animation.manager.AnimationManager

class SonicStateListener(
    private val manager: AnimationManager<TextureRegion>
) : HedgehogPropertyChangeListener {
    override fun onStateChange(newState: Hedgehog.State) {
        when (newState) {
            Hedgehog.State.IDLE -> manager.selectAnimation(HedgehogAnimationContract.KEY_IDLE)
            Hedgehog.State.WALK -> manager.selectAnimation(HedgehogAnimationContract.KEY_WALK)
            Hedgehog.State.RUN -> manager.selectAnimation(HedgehogAnimationContract.KEY_RUN)
            Hedgehog.State.SPIN -> manager.selectAnimation(HedgehogAnimationContract.KEY_SPIN)
            Hedgehog.State.SPINDASH -> manager.selectAnimation(HedgehogAnimationContract.KEY_SPINDASH)
            Hedgehog.State.DUCK -> Unit
            Hedgehog.State.LOOK_UP -> Unit
        }
    }

    override fun onPositionChange(newPosX: Float, newPosY: Float) {
    }

    override fun onLinearVelocityChange(newVelX: Float, newVelY: Float) {
    }

    override fun onAccelerationChange(newAccX: Float, newAccY: Float) {
    }

    override fun onHpChange(newHp: Float) {
    }

    override fun onLivesCountChange(newLivesCount: Int) {
    }

    override fun onRingsCountChange(newRingsCount: Int) {
    }

    override fun onOnGroundChange(isOnGroundNow: Boolean) {
    }

    override fun onChangeDirection(dir: Direction) {
        manager.isFlipped = dir == Direction.BACKWARD
    }
}
