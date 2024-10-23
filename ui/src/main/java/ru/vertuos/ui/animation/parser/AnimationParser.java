package ru.vertuos.ui.animation.parser;

import ru.vertuos.ui.animation.container.AnimationContainer;

public interface AnimationParser<T> {

    AnimationContainer<T> parse();
}
