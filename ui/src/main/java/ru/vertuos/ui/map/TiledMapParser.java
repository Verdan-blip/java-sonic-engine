package ru.vertuos.ui.map;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.math.Rectangle;
import ru.vertuos.engine.map.object.CollisionableGameObject;
import ru.vertuos.engine.map.object.InclineObject;
import ru.vertuos.engine.map.object.PlatformObject;
import ru.vertuos.ui.contracts.DimensionUtils;

import java.util.ArrayList;
import java.util.List;


public class TiledMapParser {

    public List<CollisionableGameObject> parseCollisionObjects(
        MapLayer collisionLayer,
        float unitScale
    ) {
        MapObjects gdxMapObjects = collisionLayer.getObjects();

        List<CollisionableGameObject> collisionables = new ArrayList<>(gdxMapObjects.getCount());

        for (MapObject gdxMapObject : gdxMapObjects) {

            Rectangle bounds = extractBounds(gdxMapObject.getProperties());
            pixelsRectangleToMetresRectangle(bounds);
            scale(bounds, unitScale);

            switch (gdxMapObject.getName()) {
                case MapParsingContract.NAME_PLATFORM:
                    collisionables.add(new PlatformObject(bounds.x, bounds.y, bounds.width, bounds.height));
                    break;
                case MapParsingContract.NAME_INCLINE:
                    collisionables.add(new InclineObject(bounds.x, bounds.y, bounds.width, bounds.height));
                    break;
                case MapParsingContract.NAME_CIRCLED_INCLINE:
                    break;
                case MapParsingContract.NAME_HYPERBOLIC_INCLINE:
                    break;
                default:
                    break;
            }
        }
        return collisionables;
    }

    private Rectangle extractBounds(MapProperties properties) {
        float width = properties.get("width", Float.class);
        float height = properties.get("height", Float.class);
        float x = properties.get("x", Float.class);
        float y = properties.get("y", Float.class);
        return new Rectangle(width, height, x, y);
    }

    private void pixelsRectangleToMetresRectangle(Rectangle outRect) {
        outRect.x = DimensionUtils.pixelsToMetres(outRect.x);
        outRect.y = DimensionUtils.pixelsToMetres(outRect.y);
        outRect.width = DimensionUtils.pixelsToMetres(outRect.width);
        outRect.height = DimensionUtils.pixelsToMetres(outRect.height);
    }

    private void scale(Rectangle outRect, float scale) {
        outRect.x *= scale;
        outRect.y *= scale;
        outRect.width *= scale;
        outRect.height *= scale;
    }
}
