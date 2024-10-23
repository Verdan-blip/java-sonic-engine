package ru.vertuos.engine.hedgehog

import ru.vertuos.engine.hedgehog.listener.HedgehogEventListener
import ru.vertuos.engine.hedgehog.listener.HedgehogPropertyChangeListener
import ru.vertuos.engine.world.obj.DynamicGameObject

abstract class Hedgehog : DynamicGameObject() {
    enum class State {
        IDLE, WALK, RUN, SPINDASH, SPIN, DUCK, LOOK_UP
    }

    var hp: Float = 0f
        set(value) {
            field = value
            propertyChangeListener?.onHpChange(value)
        }

    var ringsCount: Int = 0
        set(value) {
            field = value
            propertyChangeListener?.onRingsCountChange(value)
        }

    var livesCount: Int = 0
        set(value) {
            field = value
            propertyChangeListener?.onLivesCountChange(value)
        }

    var jumpValue: Float = 0f
    var strengthValue: Float = 0f

    var isJumped: Boolean = false

    var state: State = State.IDLE
        set(value) {
            field = value
            propertyChangeListener?.onStateChange(value)
        }


    var eventListener: HedgehogEventListener? = null
    var propertyChangeListener: HedgehogPropertyChangeListener? = null
}
