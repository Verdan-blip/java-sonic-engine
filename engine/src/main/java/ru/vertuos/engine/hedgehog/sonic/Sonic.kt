package ru.vertuos.engine.hedgehog.sonic;

import ru.vertuos.engine.hedgehog.Hedgehog;

public class Sonic extends Hedgehog {

    public Sonic() {
        super();
        setRingsCount(0);
        setAccelerationValue(0.5f);
        setHp(100);
        setState(State.IDLE);
        setMass(35f);
        setJumpValue(0.5f);
        setLivesCount(5);
        setStrengthValue(0.5f);
        setWidth(0.5f);
        setHeight(1);
        setDirection(Direction.FORWARD);
    }
}
