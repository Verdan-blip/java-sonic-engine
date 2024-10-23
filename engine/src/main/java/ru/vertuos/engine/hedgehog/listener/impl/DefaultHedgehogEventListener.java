package ru.vertuos.engine.hedgehog.listener.impl;

import ru.vertuos.engine.hedgehog.listener.HedgehogEventListener;

public class DefaultHedgehogEventListener implements HedgehogEventListener {

    private static DefaultHedgehogEventListener instance;

    private DefaultHedgehogEventListener() { }

    public static DefaultHedgehogEventListener getInstance() {
        if (instance == null)
            instance = new DefaultHedgehogEventListener();
        return instance;
    }

    @Override
    public void onGameOver() {

    }

    @Override
    public void onRecover(float hp) {

    }

    @Override
    public void onDie(int livesLeft) {

    }

    @Override
    public void onTakeDamage(float damage) {

    }

    @Override
    public void onLossRings() {

    }
}
