package ru.vertuos.engine.physics;

import ru.vertuos.engine.world.object.DynamicGameObject;

public class DynamicObjectPhysics<D extends DynamicGameObject> implements Physics<D> {

    protected final D object;

    public DynamicObjectPhysics(D object) {
        this.object = object;
    }

    @Override
    public void applyForceToCenter(float forceX, float forceY) {
        object.setAccelerationX(object.getAccelerationX() + forceX / object.getMass());
        object.setAccelerationY(object.getAccelerationY() + forceY / object.getMass());
    }

    @Override
    public  void applyLinearImpulseToCenter(float impulseX, float impulseY) {
        object.setLinearVelocityX(impulseX / object.getMass());
        object.setLinearVelocityY(impulseY / object.getMass());
    }

    @Override
    public void applyImpulseXToCenter(float impulseX) {
        object.setLinearVelocityX(impulseX / object.getMass());
    }

    @Override
    public void applyImpulseYToCenter(float impulseY) {
        object.setLinearVelocityY(impulseY / object.getMass());
    }
}
