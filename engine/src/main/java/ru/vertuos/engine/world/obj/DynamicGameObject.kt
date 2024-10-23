package ru.vertuos.engine.world.object;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class DynamicGameObject extends GameObject {

    public enum Direction { FORWARD, BACKWARD }

    protected float groundVelocity;

    protected float linearVelocityX, linearVelocityY;

    protected float accelerationX, accelerationY;

    protected float angle;

    protected float angularVelocity;

    protected float mass;

    protected Direction direction;

    protected boolean isOnGround;
}
