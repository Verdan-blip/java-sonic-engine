package ru.vertuos.engine.world.handlers.calmer;

public interface Calmer<T> {

    void calm(T object, float dt);
}
