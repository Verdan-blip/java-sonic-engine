package ru.vertuos.graphics.controller

import ru.vertuos.engine.hedgehog.HedgehogMechanics
import ru.vertuos.graphics.input.KeyboardInputProcessor

abstract class HedgehogController<H : HedgehogMechanics<*, *>>(
    protected val mechanics: H,
    protected val processor: KeyboardInputProcessor
) {
    private var canJump = true

    private var canSpindash = true

    private var isSpindashing = false

    fun processInput() {
        if (processor.isRightKeyPressed) {
            mechanics.sendCommand(HedgehogMechanics.CommonCommand.GO_FORWARD)
        }
        if (processor.isLeftKeyPressed) {
            mechanics.sendCommand(HedgehogMechanics.CommonCommand.GO_BACKWARD)
        }
        if (processor.isDownKeyPressed) {
            canJump = false
            if (processor.isZKeyPressed && canSpindash) {
                mechanics.sendCommand(HedgehogMechanics.CommonCommand.ACCELERATE_BY_SPINNING)
                canSpindash = false
                isSpindashing = true
            }
            if (!processor.isZKeyPressed) {
                canSpindash = true
                mechanics.sendCommand(HedgehogMechanics.CommonCommand.HIDE)
            }
        } else {
            if (isSpindashing) {
                mechanics.sendCommand(HedgehogMechanics.CommonCommand.DASH)
                isSpindashing = false
                canSpindash = true
            } else {
                canJump = true
            }
        }
        if (processor.isZKeyPressed && canJump) {
            mechanics.sendCommand(HedgehogMechanics.CommonCommand.JUMP)
            canJump = false
        }
        if (!processor.isZKeyPressed) {
            canJump = true
        }
    }
}
