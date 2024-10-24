package ru.vertuos.engine.hedgehog.listener

interface HedgehogEventListener {

    fun onGameOver()

    fun onDie(livesLeft: Int)

    fun onTakeDamage(damage: Float)

    fun onRecover(recoverValue: Float)

    fun onLossRings()
}
