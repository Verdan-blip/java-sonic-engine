package ru.vertuos.graphics.controller

import ru.vertuos.engine.hedgehog.sonic.SonicMechanics
import ru.vertuos.graphics.input.KeyboardInputProcessor

class SonicController(physics: SonicMechanics, processor: KeyboardInputProcessor) :
    HedgehogController<SonicMechanics>(physics, processor)
