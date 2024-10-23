package ru.vertuos.engine.physics;

import ru.vertuos.engine.world.object.GameObject;

public interface Physics<G extends GameObject> {

    void applyForceToCenter(float forceX, float forceY);

    void applyLinearImpulseToCenter(float impulseX, float impulseY);

    void applyImpulseXToCenter(float impulseX);

    void applyImpulseYToCenter(float impulseY);
}
