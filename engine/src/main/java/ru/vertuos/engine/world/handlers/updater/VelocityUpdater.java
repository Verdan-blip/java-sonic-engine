package ru.vertuos.engine.world.handlers.updater;

import ru.vertuos.engine.world.object.DynamicGameObject;

public class VelocityUpdater implements Updater<DynamicGameObject> {

    @Override
    public void update(DynamicGameObject object, float dt) {
        object.setLinearVelocityX(
            object.getLinearVelocityX() + object.getAccelerationX() * dt
        );
        object.setLinearVelocityY(
            object.getLinearVelocityY() + object.getAccelerationY() * dt
        );
    }
}
