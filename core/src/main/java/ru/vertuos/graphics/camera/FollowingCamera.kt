package ru.vertuos.graphics.camera;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import lombok.Setter;
import ru.vertuos.engine.world.object.DynamicGameObject;
import ru.vertuos.ui.contracts.DimensionUtils;

@Setter
public class FollowingCamera extends OrthographicCamera {

    private DynamicGameObject followingObject;

    private Rectangle boundsWhereToFollow;

    private Vector2 offset;

    public FollowingCamera() {
        super();
        followingObject = null;
        offset = new Vector2(0f, 0f);
        boundsWhereToFollow = new Rectangle(0f, 0f, 0f, 0f);
    }

    public void update(SpriteBatch batch) {
        if (followingObject != null) {
            float x = DimensionUtils.metresToPixels(followingObject.getPositionX());
            float y = DimensionUtils.metresToPixels(followingObject.getPositionY());
            if (boundsWhereToFollow.contains(x, y)) {
                position.set(x + offset.x, y + offset.y, 0f);
                super.update();
                //batch.setProjectionMatrix(combined);
            }
        }
    }
}
