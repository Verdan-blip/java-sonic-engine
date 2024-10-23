package ru.vertuos.engine.physics

import ru.vertuos.engine.math.Vector2f
import ru.vertuos.engine.world.obj.GameObject

interface Physics<G : GameObject> {

    fun applyForceToCenter(force: Vector2f)

    fun applyLinearImpulseToCenter(impulse: Vector2f)

    fun applyImpulseXToCenter(impulseX: Float)

    fun applyImpulseYToCenter(impulseY: Float)
}
