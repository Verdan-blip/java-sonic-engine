package ru.vertuos.ui.animation.parser

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.utils.Array
import com.badlogic.gdx.utils.XmlReader
import ru.vertuos.ui.animation.container.AnimationContainer
import java.io.File
import java.nio.file.Paths

class TextureRegionAnimationParser(private val internalPath: String) : AnimationParser<TextureRegion> {
    override fun parse(): AnimationContainer<TextureRegion> {
        val xmlReader = XmlReader()
        val root = xmlReader.parse(Gdx.files.internal(internalPath))

        val pathToImage = root.getAttribute("image")
        val animationListPath = Paths.get(Gdx.files.internal(internalPath).path())
        val animationListDirectory = animationListPath.parent
        val rawPathToTexture = animationListDirectory.resolve(pathToImage).toString()
        val pathToTexture = File(rawPathToTexture).canonicalPath

        val texture = Texture(pathToTexture)
        val animationContainer = AnimationContainer<TextureRegion>()

        val animationElements = root.getChildrenByName("animation")
        for (animationElement in animationElements) {
            val regions = parseRegions(texture, animationElement)
            val title = animationElement.getAttribute("title")
            val delay = animationElement.getInt("delay", 0)
            val animation = Animation(delay / 1000f, regions, Animation.PlayMode.LOOP)
            animationContainer.putAnimation(title, animation)
        }

        return animationContainer
    }

    private fun parseRegions(texture: Texture, animationElement: XmlReader.Element): Array<TextureRegion> {
        val cuts = animationElement.getChildrenByName("cut")
        val regions = Array<TextureRegion>(cuts.size)
        for (cut in cuts) {
            val x = cut.getIntAttribute("x", 0)
            val y = cut.getIntAttribute("y", 0)
            val w = cut.getIntAttribute("w", 0)
            val h = cut.getIntAttribute("h", 0)
            regions.add(TextureRegion(texture, x, y, w, h))
        }
        return regions
    }
}
