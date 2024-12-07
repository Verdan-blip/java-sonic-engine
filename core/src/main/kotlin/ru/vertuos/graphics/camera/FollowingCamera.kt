package ru.vertuos.graphics.camera

import com.badlogic.gdx.graphics.OrthographicCamera
import ru.vertuos.engine.math.Rectangle
import ru.vertuos.engine.math.Vector2f
import ru.vertuos.engine.math.contains
import ru.vertuos.engine.world.obj.DynamicGameObject
import ru.vertuos.ui.contracts.metresToPixels

class FollowingCamera : OrthographicCamera() {

    var followingObject: DynamicGameObject? = null

    var boundsWhereToFollow: Rectangle? = null

    var offset: Vector2f = Vector2f(0f, 0f)

    override fun update() {
        followingObject?.apply {
            val pixelPos = position.metresToPixels()
            val bounds = boundsWhereToFollow
            if (bounds == null) {
                this@FollowingCamera
                    .position
                    .set(pixelPos.x + offset.x, pixelPos.y + offset.y, 0f)
            } else if (bounds.contains(pixelPos)) {
                this@FollowingCamera
                    .position
                    .set(pixelPos.x + offset.x, pixelPos.y + offset.y, 0f)
            }
            super.update()
        }
    }
}
