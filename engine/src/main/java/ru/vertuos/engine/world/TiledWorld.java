package ru.vertuos.engine.world;

import ru.vertuos.engine.map.object.CollisionableGameObject;
import ru.vertuos.engine.world.handlers.calmer.Calmer;
import ru.vertuos.engine.world.handlers.calmer.VelocityCalmer;
import ru.vertuos.engine.world.handlers.gravity.GravityResolver;
import ru.vertuos.engine.world.handlers.gravity.VerticalGravityResolver;
import ru.vertuos.engine.world.handlers.updater.PositionUpdater;
import ru.vertuos.engine.world.handlers.updater.Updater;
import ru.vertuos.engine.world.handlers.updater.VelocityUpdater;
import ru.vertuos.engine.world.object.DynamicGameObject;

public class TiledWorld extends World {

    private final Updater<DynamicGameObject> velocityUpdater;

    private final Updater<DynamicGameObject> positionUpdater;

    private final Calmer<DynamicGameObject> velocityCalmer;

    private final GravityResolver<DynamicGameObject> gravityResolver;

    public TiledWorld() {
        super();
        velocityUpdater = new VelocityUpdater();
        positionUpdater = new PositionUpdater();
        gravityResolver = new VerticalGravityResolver();

        velocityCalmer = new VelocityCalmer();
    }

    @Override
    public void step(float dt) {
        for (DynamicGameObject object : dynamicGameObjects) {

            velocityCalmer.calm(object, dt);
            gravityResolver.resolve(object, dt);

            velocityUpdater.update(object, dt);
            positionUpdater.update(object, dt);

            object.setOnGround(false);

            for (CollisionableGameObject mapObject : mapObjects) {

                float dPosX = object.getPositionX();
                float dPosY = object.getPositionY();
                float dW = object.getWidth();
                float dH = object.getHeight();

                float x = mapObject.getPositionX();
                float y = mapObject.getPositionY();
                float w = mapObject.getWidth();
                float h = mapObject.getHeight();

                if (dPosX + dW > x && dPosX < x + w && dPosY + dH > y && dPosY < y + h) {
                    mapObject.onCollide(object);
                }
            }

            object.setAccelerationX(0f);
            object.setAccelerationY(0f);
        }
    }
}
