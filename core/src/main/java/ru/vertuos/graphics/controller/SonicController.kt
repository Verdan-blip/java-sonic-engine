package ru.vertuos.graphics.controller;

import ru.vertuos.engine.hedgehog.sonic.SonicMechanics;
import ru.vertuos.graphics.input.KeyboardInputProcessor;

public class SonicController extends HedgehogController<SonicMechanics> {

    public SonicController(SonicMechanics physics, KeyboardInputProcessor processor) {
        super(physics, processor);
    }
}
