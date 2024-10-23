package ru.vertuos.engine.world;

import ru.vertuos.engine.map.object.CollisionableGameObject;
import ru.vertuos.engine.world.object.DynamicGameObject;
import ru.vertuos.engine.world.object.GameObject;

import java.util.ArrayList;
import java.util.List;

public abstract class World {

    protected final List<CollisionableGameObject> mapObjects;

    protected final List<DynamicGameObject> dynamicGameObjects;

    public World() {
        this.mapObjects = new ArrayList<>();
        this.dynamicGameObjects = new ArrayList<>();
    }

    public void add(GameObject object) {
        if (object instanceof CollisionableGameObject) {
            mapObjects.add((CollisionableGameObject) object);
        } else if (object instanceof DynamicGameObject) {
            dynamicGameObjects.add((DynamicGameObject) object);
        }
    }

    public abstract void step(float dt);
}
