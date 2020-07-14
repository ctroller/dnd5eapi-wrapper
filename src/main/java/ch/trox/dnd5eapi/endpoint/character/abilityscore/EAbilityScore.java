package ch.trox.dnd5eapi.endpoint.character.abilityscore;

import com.google.common.collect.ImmutableMap;

import java.util.function.Function;
import java.util.stream.Stream;

import javax.annotation.Nullable;

public enum EAbilityScore {
    STRENGTH("str"),
    DEXTERITY("dex"),
    CONSTITUTION("con"),
    WISDOM("wis"),
    INTELLIGENCE("int"),
    CHARISMA("cha");

    private static final ImmutableMap<String, EAbilityScore> LOOKUP = Stream.of(values())
            .collect(ImmutableMap.toImmutableMap(EAbilityScore::getShorthand, Function.identity()));

    private final String shorthand;

    EAbilityScore(String shorthand) {
        this.shorthand = shorthand;
    }

    public String getShorthand() {
        return shorthand;
    }

    @Nullable
    public static EAbilityScore of(String shorthand) {
        return LOOKUP.get(shorthand);
    }
}
