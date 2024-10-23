package ru.vertuos.engine.hedgehog.listener;

import ru.vertuos.engine.hedgehog.Hedgehog;
import ru.vertuos.engine.world.object.DynamicGameObject;

public interface HedgehogPropertyChangeListener {

    void onPositionChange(float newPosX, float newPosY);

    void onLinearVelocityChange(float newVelX, float newVelY);

    void onAccelerationChange(float newAccX, float newAccY);

    void onStateChange(Hedgehog.State newState);

    void onHpChange(float newHp);

    void onLivesCountChange(int newLivesCount);

    void onRingsCountChange(int newRingsCount);

    void onOnGroundChange(boolean isOnGroundNow);

    void onChangeDirection(DynamicGameObject.Direction dir);
}
