package ru.vertuos.engine.world.object;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class GameObject {

    protected float positionX, positionY;

    protected float width, height;
}
