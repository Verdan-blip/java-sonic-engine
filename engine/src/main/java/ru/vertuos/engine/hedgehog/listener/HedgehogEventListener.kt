package ru.vertuos.engine.hedgehog.listener;

public interface HedgehogEventListener {

    void onGameOver();

    void onDie(int livesLeft);

    void onTakeDamage(float damage);

    void onRecover(float recoverValue);

    void onLossRings();
}
