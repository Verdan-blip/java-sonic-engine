package ru.vertuos.engine.world.handlers.gravity;

import ru.vertuos.engine.world.object.DynamicGameObject;

public class VerticalGravityResolver
    implements GravityResolver<DynamicGameObject> {

    private static final float G = 15f;

    @Override
    public void resolve(DynamicGameObject object, float dt) {
        if (!object.isOnGround()) {
            object.setAccelerationY(-G);
        }
    }
}
