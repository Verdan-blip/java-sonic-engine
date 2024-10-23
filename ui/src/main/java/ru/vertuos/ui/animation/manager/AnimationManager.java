package ru.vertuos.ui.animation.manager;

import com.badlogic.gdx.graphics.g2d.Animation;
import lombok.Getter;
import lombok.Setter;
import ru.vertuos.ui.animation.container.AnimationContainer;

public class AnimationManager<T> {

    protected final AnimationContainer<T> animationContainer;

    @Getter
    protected Animation<T> currentAnimation;

    private float accumulatedTime;

    @Getter
    @Setter
    protected boolean isFlipped;

    public AnimationManager(AnimationContainer<T> animationContainer) {
        this.animationContainer = animationContainer;
        this.accumulatedTime = 0f;
    }

    public void selectAnimation(String key) {
        accumulatedTime = 0f;
        currentAnimation = animationContainer.getAnimation(key);
    }

    public T getCurrentFrame(float dt) {
        accumulatedTime += dt;
        return currentAnimation.getKeyFrame(accumulatedTime);
    }
}
