package ru.vertuos.engine.hedgehog.sonic;

import ru.vertuos.engine.hedgehog.HedgehogMechanics;

public class SonicMechanics extends HedgehogMechanics<Sonic, SonicMechanics.SonicSpecialCommand> {

    public enum SonicSpecialCommand {  }

    public SonicMechanics(Sonic hedgehog) {
        super(hedgehog);
    }

    @Override
    public void sendSpecialCommand(SonicSpecialCommand specialCommand) { }
}
