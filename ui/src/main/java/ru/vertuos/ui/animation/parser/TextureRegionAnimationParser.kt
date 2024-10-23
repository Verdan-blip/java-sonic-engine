package ru.vertuos.ui.animation.parser;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.XmlReader;
import ru.vertuos.ui.animation.container.AnimationContainer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TextureRegionAnimationParser implements AnimationParser<TextureRegion> {

    private final String internalPath;

    public TextureRegionAnimationParser(String internalPath) {
        this.internalPath = internalPath;
    }

    @Override
    public AnimationContainer<TextureRegion> parse() {
        XmlReader xmlReader = new XmlReader();
        XmlReader.Element root = xmlReader.parse(Gdx.files.internal(internalPath));

        String pathToImage = root.getAttribute("image");
        try {
            Path animationListPath = Paths.get(Gdx.files.internal(internalPath).path());
            Path animationListDirectory = animationListPath.getParent();
            String rawPathToTexture = animationListDirectory.resolve(pathToImage).toString();
            String pathToTexture = new File(rawPathToTexture).getCanonicalPath();

            Texture texture = new Texture(pathToTexture);
            AnimationContainer<TextureRegion> animationContainer = new AnimationContainer<>();

            Array<XmlReader.Element> animationElements = root.getChildrenByName("animation");
            for (XmlReader.Element animationElement : animationElements) {
                Array<TextureRegion> regions = parseRegions(texture, animationElement);
                String title = animationElement.getAttribute("title");
                int delay = animationElement.getInt("delay", 0);
                Animation<TextureRegion> animation = new Animation<>(delay / 1000f, regions, Animation.PlayMode.LOOP);
                animationContainer.putAnimation(title, animation);
            }

            return animationContainer;
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    private Array<TextureRegion> parseRegions(Texture texture, XmlReader.Element animationElement) {
        Array<XmlReader.Element> cuts = animationElement.getChildrenByName("cut");
        Array<TextureRegion> regions = new Array<>(cuts.size);
        for (XmlReader.Element cut : cuts) {
            int x = cut.getIntAttribute("x", 0);
            int y = cut.getIntAttribute("y", 0);
            int w = cut.getIntAttribute("w", 0);
            int h = cut.getIntAttribute("h", 0);
            regions.add(new TextureRegion(texture, x, y, w, h));
        }
        return regions;
    }
}
