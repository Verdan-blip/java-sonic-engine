package ru.vertuos.engine.hedgehog;

import lombok.Getter;
import lombok.Setter;
import ru.vertuos.engine.hedgehog.listener.HedgehogEventListener;
import ru.vertuos.engine.hedgehog.listener.HedgehogPropertyChangeListener;
import ru.vertuos.engine.hedgehog.listener.impl.DefaultHedgehogEventListener;
import ru.vertuos.engine.hedgehog.listener.impl.DefaultHedgehogPropertyChangeListener;
import ru.vertuos.engine.world.object.DynamicGameObject;

@Setter
@Getter
public abstract class Hedgehog extends DynamicGameObject {

    public enum State { IDLE, WALK, RUN, SPINDASH, SPIN, DUCK, LOOK_UP }

    protected float hp;

    protected int ringsCount;

    protected int livesCount;

    protected float accelerationValue;

    protected float jumpValue;

    protected float strengthValue;

    protected State state;


    protected boolean isJumped;


    protected HedgehogEventListener eventListener;

    protected HedgehogPropertyChangeListener propertyChangeListener;

    public Hedgehog() {
        eventListener = DefaultHedgehogEventListener.getInstance();
        propertyChangeListener = DefaultHedgehogPropertyChangeListener.getInstance();
    }

    public void setState(State state) {
        if (this.state != state) {
            this.state = state;
            propertyChangeListener.onStateChange(state);
        }
    }

    @Override
    public void setDirection(Direction direction) {
        if (this.direction != direction) {
            this.direction = direction;
            propertyChangeListener.onChangeDirection(direction);
        }
    }

    public void setHp(float hp) {
        if (Float.compare(this.hp, hp) != 0) {
            this.hp = hp;
            propertyChangeListener.onHpChange(hp);
        }
    }

    public void setRingsCount(int ringsCount) {
        this.ringsCount = ringsCount;
        propertyChangeListener.onRingsCountChange(ringsCount);
    }

    public void setLivesCount(int livesCount) {
        this.livesCount = livesCount;
        propertyChangeListener.onLivesCountChange(livesCount);
    }

    @Override
    public void setLinearVelocityX(float linearVelocityX) {
        this.linearVelocityX = linearVelocityX;
        propertyChangeListener.onLinearVelocityChange(linearVelocityX, linearVelocityY);
    }

    @Override
    public void setLinearVelocityY(float linearVelocityY) {
        this.linearVelocityY = linearVelocityY;
        propertyChangeListener.onLinearVelocityChange(linearVelocityX, linearVelocityY);
    }

    @Override
    public void setAccelerationX(float accelerationX) {
        this.accelerationX = accelerationX;
        propertyChangeListener.onAccelerationChange(accelerationX, accelerationY);
    }

    @Override
    public void setAccelerationY(float accelerationY) {
        this.accelerationY = accelerationY;
        propertyChangeListener.onAccelerationChange(accelerationX, accelerationY);
    }

    @Override
    public void setPositionX(float positionX) {
        this.positionX = positionX;
        propertyChangeListener.onPositionChange(positionX, positionY);
    }

    @Override
    public void setPositionY(float positionY) {
        this.positionY = positionY;
        propertyChangeListener.onPositionChange(positionX, positionY);
    }
}
