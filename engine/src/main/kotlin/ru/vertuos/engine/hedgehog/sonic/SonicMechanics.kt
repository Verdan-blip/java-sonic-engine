package ru.vertuos.engine.hedgehog.sonic

import ru.vertuos.engine.hedgehog.HedgehogMechanics
import ru.vertuos.engine.hedgehog.sonic.SonicMechanics.SonicSpecialCommand

class SonicMechanics(
    hedgehog: Sonic
) : HedgehogMechanics<Sonic, SonicSpecialCommand>(hedgehog) {

    enum class SonicSpecialCommand

    override fun sendSpecialCommand(specialCommand: SonicSpecialCommand) {

    }
}
