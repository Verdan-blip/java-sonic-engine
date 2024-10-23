package ru.vertuos.engine.world.handlers.updater;

import ru.vertuos.engine.world.object.DynamicGameObject;

public class PositionUpdater implements Updater<DynamicGameObject> {

    @Override
    public void update(DynamicGameObject object, float dt) {
        object.setPositionX(object.getPositionX() + object.getLinearVelocityX() * dt);
        object.setPositionY(object.getPositionY() + object.getLinearVelocityY() * dt);
    }
}
