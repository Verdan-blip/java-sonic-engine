package ru.vertuos.engine.hedgehog.listener.impl;

import ru.vertuos.engine.hedgehog.Hedgehog;
import ru.vertuos.engine.hedgehog.listener.HedgehogPropertyChangeListener;
import ru.vertuos.engine.world.object.DynamicGameObject;

public class DefaultHedgehogPropertyChangeListener implements HedgehogPropertyChangeListener {

    private static DefaultHedgehogPropertyChangeListener listener;

    private DefaultHedgehogPropertyChangeListener() { }

    public static DefaultHedgehogPropertyChangeListener getInstance() {
        if (listener == null) {
            listener = new DefaultHedgehogPropertyChangeListener();
        }
        return listener;
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
    public void onStateChange(Hedgehog.State newState) {

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

    }
}
