package ru.vertuos.graphics.input

import com.badlogic.gdx.Input
import com.badlogic.gdx.InputAdapter
import lombok.Getter

class KeyboardInputProcessor : InputAdapter() {

    var isRightKeyPressed = false
    var isLeftKeyPressed = false

    var isDownKeyPressed = false

    var isZKeyPressed = false

    override fun keyDown(keycode: Int): Boolean {
        when (keycode) {
            Input.Keys.RIGHT -> {
                isRightKeyPressed = true
                return true
            }
            Input.Keys.LEFT -> {
                isLeftKeyPressed = true
                return true
            }
            Input.Keys.DOWN -> {
                isDownKeyPressed = true
                return true
            }
            Input.Keys.Z -> {
                isZKeyPressed = true
                return true
            }
        }
        return false
    }

    override fun keyUp(keycode: Int): Boolean {
        when (keycode) {
            Input.Keys.RIGHT -> {
                isRightKeyPressed = false
                return true
            }
            Input.Keys.LEFT -> {
                isLeftKeyPressed = false
                return true
            }
            Input.Keys.DOWN -> {
                isDownKeyPressed = false
                return true
            }
            Input.Keys.Z -> {
                isZKeyPressed = false
                return true
            }
        }
        return false
    }
}
