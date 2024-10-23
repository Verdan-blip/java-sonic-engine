package ru.vertuos.engine.hedgehog.sonic

import ru.vertuos.engine.hedgehog.Hedgehog
import ru.vertuos.engine.math.Vector2f

class Sonic : Hedgehog() {

    init {
        ringsCount = 0
        hp = 100f
        state = State.IDLE
        mass = 35f
        jumpValue = 0.5f
        livesCount = 5
        strengthValue = 0.5f
        size = Vector2f(0.5f, 1f)
        direction = Direction.FORWARD
    }
}
