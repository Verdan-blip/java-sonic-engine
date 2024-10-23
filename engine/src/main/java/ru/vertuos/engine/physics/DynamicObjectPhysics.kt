package ru.vertuos.engine.physics

import ru.vertuos.engine.math.Vector2f
import ru.vertuos.engine.math.copied
import ru.vertuos.engine.math.div
import ru.vertuos.engine.math.plus
import ru.vertuos.engine.world.obj.DynamicGameObject

open class DynamicObjectPhysics<D : DynamicGameObject>(
    val obj: D
) : Physics<D> {

    override fun applyForceToCenter(force: Vector2f) {
        obj.acceleration += force / obj.mass
    }

    override fun applyLinearImpulseToCenter(impulse: Vector2f) {
        obj.linearVelocity = impulse / obj.mass
    }

    override fun applyImpulseXToCenter(impulseX: Float) {
        obj.linearVelocity = obj.linearVelocity.copied(x = impulseX / obj.mass)
    }

    override fun applyImpulseYToCenter(impulseY: Float) {
        obj.linearVelocity = obj.linearVelocity.copied(y = impulseY / obj.mass)
    }
}
