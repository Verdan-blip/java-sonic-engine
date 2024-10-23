package ru.vertuos.engine.hedgehog;

import ru.vertuos.engine.physics.DynamicObjectPhysics;
import ru.vertuos.engine.world.object.DynamicGameObject;

public abstract class HedgehogMechanics<H extends Hedgehog, C>
    extends DynamicObjectPhysics<H> {

    public static final float MAX_WALK_VELOCITY_VALUE = 16f;
    public static final float MAX_IDLE_VELOCITY_X_VALUE = 0.01f;
    public static final float MAX_IDLE_VELOCITY_Y_VALUE = 0.01f;
    public static final float MAX_VELOCITY_VALUE = 20f;

    public static final float FORCE_TO_MOVE = 175f;
    public static final float IMPULSE_TO_JUMP = 350f;
    public static final float IMPULSE_INCREASE_FACTOR = 2;
    public static final float IMPULSE_MAX_VALUE = 800;
    public static final float IMPULSE_MIN_VALUE = 200;

    protected float accumulatedImpulse;

    public enum CommonCommand {

        GO_FORWARD,
        GO_BACKWARD,
        JUMP,
        ACCELERATE_BY_SPINNING,
        HIDE,
        DASH
    }

    public HedgehogMechanics(H hedgehog) {
        super(hedgehog);
        accumulatedImpulse = 0f;
    }

    public abstract void sendSpecialCommand(C specialCommand);

    public void sendCommand(CommonCommand command) {
        Hedgehog.State state = object.getState();
        float absVelX = Math.abs(object.getLinearVelocityX());
        float absVelY = Math.abs(object.getLinearVelocityY());
        switch (command) {
            case GO_FORWARD:
                if (state != Hedgehog.State.SPIN || object.isJumped()) {
                    applyForceToCenter(FORCE_TO_MOVE, 0f);
                    object.setDirection(DynamicGameObject.Direction.FORWARD);
                }
                break;
            case GO_BACKWARD:
                if (state != Hedgehog.State.SPIN || object.isJumped()) {
                    applyForceToCenter(-FORCE_TO_MOVE, 0f);
                    object.setDirection(DynamicGameObject.Direction.BACKWARD);
                }
                break;
            case HIDE:
                if (hedgehogRestsByX(absVelX)) {
                    object.setState(Hedgehog.State.DUCK);
                } else {
                    object.setState(Hedgehog.State.SPIN);
                }
                break;
            case JUMP:
                if (object.isOnGround()) {
                    applyImpulseYToCenter(IMPULSE_TO_JUMP);
                    object.setState(Hedgehog.State.SPIN);
                    object.setJumped(true);
                }
                break;
            case ACCELERATE_BY_SPINNING:
                if (hedgehogRests(absVelX, absVelY)) {
                    if (Float.compare(accumulatedImpulse, 0f) == 0)
                        setAccumulatedImpulse(IMPULSE_MIN_VALUE);
                    else
                        setAccumulatedImpulse(accumulatedImpulse * IMPULSE_INCREASE_FACTOR);
                    object.setState(Hedgehog.State.SPINDASH);
                }
                break;
            case DASH:
                if (object.getDirection() == DynamicGameObject.Direction.FORWARD)
                    applyLinearImpulseToCenter(accumulatedImpulse, 0f);
                else
                    applyLinearImpulseToCenter(-accumulatedImpulse, 0f);
                setAccumulatedImpulse(0f);
                object.setState(Hedgehog.State.SPIN);
                break;
        }
    }

    public void updateStates() {
        updateStatesByVelocities();
    }

    private void updateStatesByVelocities() {
        float absVelX = Math.abs(object.getLinearVelocityX());
        float absVelY = Math.abs(object.getLinearVelocityY());
        Hedgehog.State state = object.getState();
        if (state == Hedgehog.State.DUCK) {
            return;
        }
        if (state == Hedgehog.State.SPINDASH) {
            return;
        }
        if (object.isOnGround()) {
            if (state == Hedgehog.State.SPIN) {
                if (object.isJumped()) {
                    handleDefaultStateByVelocity(absVelX);
                    object.setJumped(false);
                } else {
                    if (hedgehogRests(absVelX, absVelY)) {
                        object.setState(Hedgehog.State.IDLE);
                    }
                }
            } else {
                handleDefaultStateByVelocity(absVelX);
            }
        }
    }

    private void handleDefaultStateByVelocity(float absVelX) {
        if (Float.compare(absVelX, HedgehogMechanics.MAX_IDLE_VELOCITY_X_VALUE) < 0) {
            object.setState(Hedgehog.State.IDLE);
        } else if (Float.compare(absVelX, HedgehogMechanics.MAX_WALK_VELOCITY_VALUE) < 0) {
            object.setState(Hedgehog.State.WALK);
        } else {
            object.setState(Hedgehog.State.RUN);
        }
    }

    public void setAccumulatedImpulse(float accumulatedImpulse) {
        if (Float.compare(accumulatedImpulse, IMPULSE_MAX_VALUE) <= 0)
            this.accumulatedImpulse = accumulatedImpulse;
    }

    private boolean hedgehogRests(float absVelX, float absVelY) {
        return hedgehogRestsByX(absVelX) && hedgehogRestsByY(absVelY);
    }

    private boolean hedgehogRestsByX(float absVelX) {
        return Float.compare(absVelX, MAX_IDLE_VELOCITY_X_VALUE) < 0;
    }

    private boolean hedgehogRestsByY(float absVelY) {
        return Float.compare(absVelY, MAX_IDLE_VELOCITY_Y_VALUE) < 0;
    }
}
