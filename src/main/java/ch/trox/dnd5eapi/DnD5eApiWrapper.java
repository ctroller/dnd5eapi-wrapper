package ch.trox.dnd5eapi;

import ch.trox.dnd5eapi.endpoint.CharacterMirror;
import ch.trox.dnd5eapi.internal.RestHelper;

/**
 * Base class to access all available endpoints of the D&D 5th Edition API with a functional
 * programming access style.
 */
public enum DnD5eApiWrapper {
    INSTANCE;

    DnD5eApiWrapper()
    {
        RestHelper.init();
    }

    /**
     * Shorthand function to access the {@link CharacterMirror} endpoint.
     *
     * @return the {@link CharacterMirror} endpoint
     */
    public static CharacterMirror character() {
        return CharacterMirror.INSTANCE;
    }
}
