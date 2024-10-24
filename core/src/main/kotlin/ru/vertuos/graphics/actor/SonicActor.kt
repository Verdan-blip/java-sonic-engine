package ru.vertuos.graphics.actor

import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.scenes.scene2d.Actor
import ru.vertuos.engine.hedgehog.sonic.Sonic
import ru.vertuos.engine.hedgehog.sonic.SonicMechanics
import ru.vertuos.graphics.controller.SonicController
import ru.vertuos.graphics.hedgehog.listener.SonicStateListener
import ru.vertuos.graphics.input.KeyboardInputProcessor
import ru.vertuos.ui.animation.container.AnimationContainer
import ru.vertuos.ui.animation.contracts.HedgehogAnimationContract
import ru.vertuos.ui.animation.manager.AnimationManager
import ru.vertuos.ui.animation.parser.AnimationParser
import ru.vertuos.ui.animation.parser.TextureRegionAnimationParser
import ru.vertuos.ui.contracts.toPixels

class SonicActor(
    private val sonic: Sonic,
    private val sonicMechanics: SonicMechanics,
    keyboardInputProcessor: KeyboardInputProcessor
) : Actor() {

    private val sonicController: SonicController = SonicController(
        sonicMechanics, keyboardInputProcessor
    )

    private val animationManager: AnimationManager<TextureRegion>

    init {
        val parser: AnimationParser<TextureRegion> = TextureRegionAnimationParser("animations/sonic/sonic_advance.xml")
        val animationContainer: AnimationContainer<TextureRegion> = parser.parse()

        this.animationManager = AnimationManager(animationContainer)
        animationManager.selectAnimation(HedgehogAnimationContract.KEY_IDLE)

        this.sonic.propertyChangeListener = SonicStateListener(animationManager)
    }

    fun updateStates() {
        sonicMechanics.updateStates()
    }

    fun processInput() {
        sonicController.processInput()
    }

    override fun draw(batch: Batch, dt: Float) {
        val isFlip = animationManager.isFlipped
        val region = animationManager.getCurrentFrame(dt)
        val posPixels = sonic.position.toPixels()
        region?.also { reg ->
            batch.draw(
                reg,
                if (isFlip) posPixels.x + reg.regionWidth.toFloat() else posPixels.x,
                posPixels.y,
                if (isFlip) -reg.regionWidth.toFloat() else reg.regionHeight.toFloat(),
                reg.regionHeight.toFloat()
            )
        }
    }
}
