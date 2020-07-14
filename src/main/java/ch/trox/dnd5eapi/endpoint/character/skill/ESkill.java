package ch.trox.dnd5eapi.endpoint.character.skill;

import com.google.common.collect.ImmutableMap;

import java.util.function.Function;
import java.util.stream.Stream;

import javax.annotation.Nullable;

public enum ESkill {
    ACROBATICS("acrobatics"),
    ANIMAL_HANDLING("animal-handling"),
    ARCANA("arcana"),
    ATHLETICS("athletics"),
    DECEPTION("deception"),
    HISTORY("history"),
    INSIGHT("insight"),
    INTIMIDATION("intimidation"),
    INVESTIGATION("investigation"),
    MEDICINE("medicine"),
    NATURE("nature"),
    PERCEPTION("perception"),
    PERFORMANCE("performance"),
    PERSUASION("persuasion"),
    RELIGION("religion"),
    SLEIGHT_OF_HAND("sleight-of-hand"),
    STEALTH("stealth"),
    SURVIVAL("survival");

    private static final ImmutableMap<String, ESkill> LOOKUP = Stream.of(values())
            .collect(ImmutableMap.toImmutableMap(ESkill::getShorthand, Function.identity()));

    private final String shorthand;

    ESkill(String shorthand) {
        this.shorthand = shorthand;
    }

    public String getShorthand() {
        return shorthand;
    }

    @Nullable
    public static ESkill of(String shorthand) {
        return LOOKUP.get(shorthand);
    }
}
