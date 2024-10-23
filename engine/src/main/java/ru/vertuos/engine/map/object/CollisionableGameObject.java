package ru.vertuos.engine.map.object;

import lombok.Getter;
import ru.vertuos.engine.world.object.DynamicGameObject;
import ru.vertuos.engine.world.object.GameObject;

@Getter
public abstract class CollisionableGameObject extends GameObject {

    public CollisionableGameObject(float positionX, float positionY, float width, float height) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.width = width;
        this.height = height;
    }

    public abstract void onCollide(DynamicGameObject dynamic);
}
