package ru.vertuos.ui.contracts;

public class DimensionUtils {

    private static final float METRES_TO_PIXELS = 32f;

    private static final float PIXELS_TO_METRES = 1f / METRES_TO_PIXELS;

    public static float metresToPixels(float metres) {
        return metres * METRES_TO_PIXELS;
    }

    public static float pixelsToMetres(float pixels) {
        return pixels * PIXELS_TO_METRES;
    }
}
