package ru.vertuos.graphics.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import ru.vertuos.engine.hedgehog.sonic.Sonic;
import ru.vertuos.engine.hedgehog.sonic.SonicMechanics;
import ru.vertuos.graphics.controller.SonicController;
import ru.vertuos.graphics.hedgehog.listener.SonicStateListener;
import ru.vertuos.graphics.input.KeyboardInputProcessor;
import ru.vertuos.ui.animation.container.AnimationContainer;
import ru.vertuos.ui.animation.contracts.HedgehogAnimationContract;
import ru.vertuos.ui.animation.manager.AnimationManager;
import ru.vertuos.ui.animation.parser.AnimationParser;
import ru.vertuos.ui.animation.parser.TextureRegionAnimationParser;
import ru.vertuos.ui.contracts.DimensionUtils;

public class SonicActor extends Actor {

    private final Sonic sonic;

    private final SonicMechanics sonicMechanics;

    private final SonicController sonicController;

    private final AnimationManager<TextureRegion> animationManager;

    public SonicActor(
        Sonic sonic,
        SonicMechanics sonicMechanics,
        KeyboardInputProcessor keyboardInputProcessor
    ) {
        this.sonic = sonic;
        this.sonicMechanics = sonicMechanics;
        this.sonicController = new SonicController(sonicMechanics, keyboardInputProcessor);

        AnimationParser<TextureRegion> parser = new TextureRegionAnimationParser("animations/sonic/sonic_advance.xml");
        AnimationContainer<TextureRegion> animationContainer = parser.parse();

        this.animationManager = new AnimationManager<>(animationContainer);
        this.animationManager.selectAnimation(HedgehogAnimationContract.KEY_IDLE);

        this.sonic.setPropertyChangeListener(new SonicStateListener(animationManager));
    }

    public void updateStates() {
        sonicMechanics.updateStates();
    }

    public void processInput() {
        sonicController.processInput();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        boolean isFlip = animationManager.isFlipped();
        TextureRegion region = animationManager.getCurrentFrame(Gdx.graphics.getDeltaTime());
        float x = DimensionUtils.metresToPixels(sonic.getPositionX());
        float y = DimensionUtils.metresToPixels(sonic.getPositionY());
        batch.draw(
            region,
            isFlip ? x + region.getRegionWidth() : x,
            y,
            isFlip ? -region.getRegionWidth() : region.getRegionWidth(),
            region.getRegionHeight()
        );
    }
}
