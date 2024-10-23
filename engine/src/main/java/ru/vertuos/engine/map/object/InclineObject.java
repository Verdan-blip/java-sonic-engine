package ru.vertuos.engine.map.object;

import ru.vertuos.engine.world.object.DynamicGameObject;

public class InclineObject extends CollisionableGameObject {

    public InclineObject(float positionX, float positionY, float width, float height) {
        super(positionX, positionY, width, height);
    }

    @Override
    public void onCollide(DynamicGameObject dynamic) {
        float deltaX = positionX - dynamic.getPositionX();
        float deltaY = deltaX * height / width;
        dynamic.setPositionY(positionY - deltaY);
        dynamic.setOnGround(true);
    }
}
