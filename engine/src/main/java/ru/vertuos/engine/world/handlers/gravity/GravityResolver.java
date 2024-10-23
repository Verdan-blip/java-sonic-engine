package ru.vertuos.engine.world.handlers.gravity;

public interface GravityResolver<T> {

    void resolve(T object, float dt);
}
