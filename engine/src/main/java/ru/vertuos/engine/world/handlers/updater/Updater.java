package ru.vertuos.engine.world.handlers.updater;

public interface Updater<T> {

    void update(T object, float dt);
}
