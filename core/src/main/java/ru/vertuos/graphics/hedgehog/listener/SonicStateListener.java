package ru.vertuos.graphics.hedgehog.listener;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import ru.vertuos.engine.hedgehog.Hedgehog;
import ru.vertuos.engine.hedgehog.listener.HedgehogPropertyChangeListener;
import ru.vertuos.engine.world.object.DynamicGameObject;
import ru.vertuos.ui.animation.contracts.HedgehogAnimationContract;
import ru.vertuos.ui.animation.manager.AnimationManager;

public class SonicStateListener implements HedgehogPropertyChangeListener {

    private final AnimationManager<TextureRegion> manager;

    public SonicStateListener(AnimationManager<TextureRegion> manager) {
        this.manager = manager;
    }

    @Override
    public void onStateChange(Hedgehog.State state) {
        switch (state) {
            case IDLE:
                manager.selectAnimation(HedgehogAnimationContract.KEY_IDLE);
                break;
            case WALK:
                manager.selectAnimation(HedgehogAnimationContract.KEY_WALK);
                break;
            case RUN:
                manager.selectAnimation(HedgehogAnimationContract.KEY_RUN);
                break;
            case SPIN:
                manager.selectAnimation(HedgehogAnimationContract.KEY_SPIN);
                break;
            case SPINDASH:
                manager.selectAnimation(HedgehogAnimationContract.KEY_SPINDASH);
        }
    }

    @Override
    public void onPositionChange(float newPosX, float newPosY) {

    }

    @Override
    public void onLinearVelocityChange(float newVelX, float newVelY) {

    }

    @Override
    public void onAccelerationChange(float newAccX, float newAccY) {

    }

    @Override
    public void onHpChange(float newHp) {

    }

    @Override
    public void onLivesCountChange(int newLivesCount) {

    }

    @Override
    public void onRingsCountChange(int newRingsCount) {

    }

    @Override
    public void onOnGroundChange(boolean isOnGroundNow) {

    }

    @Override
    public void onChangeDirection(DynamicGameObject.Direction dir) {
        manager.setFlipped(dir == DynamicGameObject.Direction.BACKWARD);
    }
}
