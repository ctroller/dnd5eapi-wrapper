package ch.trox.dnd5eapi.endpoint.character.skill;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import ch.trox.dnd5eapi.internal.RestHelper;

public enum  SkillLedger {
    INSTANCE;

    private static final String ENDPOINT = "/skills";

    /**
     * Looks up skill with given {@code shorthand} identifier on the API.
     *
     * @param shorthand the shorthand identifier of the ability score. For available shorthands, see
     *                  {@link ESkill}.
     * @return the Skill representative of given {@code shorthand}, or {@code NULL} if not
     * found
     */
    @Nullable
    public Skill lookup(@Nonnull String shorthand) {
        return lookup(ESkill.of(shorthand));
    }

    /**
     * Looks up skill with given {@code skill} enum on the API.
     *
     * @param skill an enum representation of type {@link ESkill}
     * @return the skill representative of given {@code skill}, or {@code NULL} if not
     * found
     */
    @Nullable
    public Skill lookup(@Nullable ESkill skill) {
        if (skill == null) {
            return null;
        }

        return internalLookup(skill);
    }

    @Nullable
    private Skill internalLookup(@Nonnull ESkill skill) {
        return RestHelper.convertOrLog(String.format("%s/%s", ENDPOINT, skill.getShorthand()), Skill::new);
    }
}
