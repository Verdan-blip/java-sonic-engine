package ru.vertuos.graphics.input;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import lombok.Getter;

@Getter
public class KeyboardInputProcessor extends InputAdapter {

    private boolean isRightKeyPressed;
    private boolean isLeftKeyPressed;

    private boolean isDownKeyPressed;

    private boolean isZKeyPressed;

    public KeyboardInputProcessor() {
        isRightKeyPressed = false;
        isLeftKeyPressed = false;
        isDownKeyPressed = false;
        isZKeyPressed = false;
    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.RIGHT:
                isRightKeyPressed = true;
                return true;
            case Input.Keys.LEFT:
                isLeftKeyPressed = true;
                return true;
            case Input.Keys.DOWN:
                isDownKeyPressed = true;
                return true;
            case Input.Keys.Z:
                isZKeyPressed = true;
                return true;
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        switch (keycode) {
            case Input.Keys.RIGHT:
                isRightKeyPressed = false;
                return true;
            case Input.Keys.LEFT:
                isLeftKeyPressed = false;
                return true;
            case Input.Keys.DOWN:
                isDownKeyPressed = false;
                return true;
            case Input.Keys.Z:
                isZKeyPressed = false;
                return true;
        }
        return false;
    }
}
