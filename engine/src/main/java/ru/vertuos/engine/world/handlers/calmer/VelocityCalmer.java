package ru.vertuos.engine.world.handlers.calmer;

import ru.vertuos.engine.world.object.DynamicGameObject;

public class VelocityCalmer implements Calmer<DynamicGameObject> {

    private static final float DEC_VALUE = 5f;

    private static final float FRC_VALUE = 4f;

    @Override
    public void calm(DynamicGameObject object, float dt) {
        float velX = object.getLinearVelocityX();
        float aX = object.getAccelerationX();
        float delta = 0f;
        if (isBreaking(velX, aX)) {
            delta = DEC_VALUE * dt;
        }
        if (isNotAccelerating(aX)) {
            delta = FRC_VALUE * dt;
        }
        if (Math.abs(velX) > delta) {
            velX -= Math.signum(velX) * delta;
        } else {
            velX = 0f;
        }
        object.setLinearVelocityX(velX);
    }

    private boolean isBreaking(float velX, float aX) {
        return aX * velX < 0;
    }

    private boolean isNotAccelerating(float aX) {
        return Float.compare(Math.abs(aX), 0f) == 0;
    }
}
