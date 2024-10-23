package ru.vertuos.engine.world.updaters.calmer;

public interface Calmer<T> {

    void calm(T object, float dt);
}
