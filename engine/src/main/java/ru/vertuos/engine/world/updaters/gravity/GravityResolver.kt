package ru.vertuos.engine.world.updaters.gravity

interface GravityResolver<T> {

    fun resolve(obj: T, dt: Float)
}
