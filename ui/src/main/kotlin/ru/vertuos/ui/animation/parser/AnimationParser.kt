package ru.vertuos.ui.animation.parser

import ru.vertuos.ui.animation.container.AnimationContainer

interface AnimationParser<T> {

    fun parse(): AnimationContainer<T>
}
