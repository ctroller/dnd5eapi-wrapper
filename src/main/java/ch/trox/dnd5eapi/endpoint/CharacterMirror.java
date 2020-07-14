package ch.trox.dnd5eapi.endpoint;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import ch.trox.dnd5eapi.endpoint.character.skill.ESkill;
import ch.trox.dnd5eapi.endpoint.character.skill.SkillLedger;
import ch.trox.dnd5eapi.endpoint.character.abilityscore.AbilityScore;
import ch.trox.dnd5eapi.endpoint.character.abilityscore.AbilityScoreLookup;
import ch.trox.dnd5eapi.endpoint.character.abilityscore.EAbilityScore;
import ch.trox.dnd5eapi.endpoint.character.skill.Skill;

/**
 * Character Mirror class
 *
 * <p>This class represents the D&D 5th Edition API "character" endpoint as described on
 * <a href="https://www.dnd5eapi.co/docs/#character-data-section">
 * https://www.dnd5eapi.co/docs/#character-data-section</a>.</p>
 *
 * <p>The name stems from looking into a mirror, revealing secrets about yourself.</p>
 */
public enum CharacterMirror {
    INSTANCE;

    /**
     * Shorthand function to get access to the {@link AbilityScoreLookup} class
     *
     * @return the instance of the {@link AbilityScoreLookup} class
     */
    @Nonnull
    public AbilityScoreLookup ability() {
        return AbilityScoreLookup.INSTANCE;
    }

    /**
     * Looks up ability score with given {@code shorthand} identifier on the API.
     *
     * @param shorthand the shorthand identifier of the ability score. For available shorthands, see
     *                  {@link EAbilityScore}.
     * @return the AbilityScore representative of given {@code shorthand}, or {@code NULL} if not
     * found
     */
    @Nullable
    public AbilityScore ability(@Nonnull String shorthand) {
        return ability().lookup( shorthand );
    }

    /**
     * Looks up ability score with given {@code abilityScore} enum on the API.
     *
     * @param abilityScore an enum representation of type {@link EAbilityScore}
     * @return the AbilityScore representative of given {@code abilityScore}, or {@code NULL} if not
     * found
     */
    @Nullable
    public AbilityScore ability(@Nonnull EAbilityScore abilityScore) {
        return ability().lookup( abilityScore );
    }


    /**
     * Shorthand function to get access to the {@link SkillLedger} endpoint
     *
     * @return the instance of the {@link SkillLedger} endpoint
     */
    @Nonnull
    public SkillLedger skill() {
        return SkillLedger.INSTANCE;
    }

    /**
     * Looks up skill with given {@code shorthand} identifier on the API.
     *
     * @param shorthand the shorthand identifier of the skill. For available shorthands, see
     *                  {@link ESkill}.
     * @return the Skill representative of given {@code shorthand}, or {@code NULL} if not
     * found
     */
    @Nullable
    public Skill skill(@Nonnull String shorthand) {
        return skill().lookup( shorthand );
    }

    /**
     * Looks up ability score with given {@code skill} enum on the API.
     *
     * @param skill an enum representation of type {@link EAbilityScore}
     * @return the AbilityScore representative of given {@code skill}, or {@code NULL} if not
     * found
     */
    @Nullable
    public Skill skill(@Nonnull ESkill skill) {
        return skill().lookup( skill );
    }
}
