package ru.vertuos.engine.world.updaters.updater

interface Updater<T> {

    fun update(obj: T, dt: Float)
}
