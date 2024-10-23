package ru.vertuos.engine.world.obj

import ru.vertuos.engine.math.Vector2f

abstract class GameObject {
    var position: Vector2f = Vector2f.Zero
    var size: Vector2f = Vector2f.Zero
}
