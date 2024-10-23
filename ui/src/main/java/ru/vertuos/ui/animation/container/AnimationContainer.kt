package ru.vertuos.ui.animation.container;

import com.badlogic.gdx.graphics.g2d.Animation;

import java.util.HashMap;
import java.util.Map;

public class AnimationContainer<T> {

    private final Map<String, Animation<T>> animationMap;

    public AnimationContainer() {
        this.animationMap = new HashMap<>();
    }

    public void putAnimation(String key, Animation<T> animation) {
        animationMap.put(key, animation);
    }

    public Animation<T> remove(String key) {
        return animationMap.remove(key);
    }

    public Animation<T> getAnimation(String key) {
        return animationMap.get(key);
    }
}
