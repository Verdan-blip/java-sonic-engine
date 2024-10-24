package ru.vertuos.engine.world.updaters.calmer

interface Calmer<T> {

    fun calm(obj: T, dt: Float)
}
