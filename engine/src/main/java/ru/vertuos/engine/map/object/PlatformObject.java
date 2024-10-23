package ru.vertuos.engine.map.object;

import ru.vertuos.engine.world.object.DynamicGameObject;

public class PlatformObject extends CollisionableGameObject {

    private static final float JUMP_ON_COEFFICIENT = 1 / 8f;

    public PlatformObject(float positionX, float positionY, float width, float height) {
        super(positionX, positionY, width, height);
    }

    @Override
    public void onCollide(DynamicGameObject dynamic) {
        if (dynamic.getLinearVelocityY() < 0) {
            float deltaY = positionY + height - dynamic.getPositionY();
            if (deltaY < JUMP_ON_COEFFICIENT * height) {
                dynamic.setPositionY(positionY + height);
                dynamic.setLinearVelocityY(0f);
                dynamic.setOnGround(true);
            }
            return;
        }
        if (dynamic.getLinearVelocityY() > 0) {
            return;
        }
        if (dynamic.getLinearVelocityX() > 0) {
            dynamic.setPositionX(positionX - dynamic.getWidth());
            dynamic.setLinearVelocityX(0f);
            return;
        }
        if (dynamic.getLinearVelocityX() < 0) {
            dynamic.setPositionX(positionX + width);
            dynamic.setLinearVelocityX(0f);
        }
    }
}
