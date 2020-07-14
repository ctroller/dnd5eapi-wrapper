package ch.trox.dnd5eapi.endpoint.character.abilityscore;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import ch.trox.dnd5eapi.internal.RestHelper;

public enum AbilityScoreLookup {
    INSTANCE;

    private static final String ENDPOINT = "/ability-scores";

    /**
     * Looks up ability score with given {@code shorthand} identifier on the API.
     *
     * @param shorthand the shorthand identifier of the ability score. For available shorthands, see
     *                  {@link EAbilityScore}.
     * @return the AbilityScore representative of given {@code shorthand}, or {@code NULL} if not
     * found
     */
    @Nullable
    public AbilityScore lookup(@Nonnull String shorthand) {
        return lookup(EAbilityScore.of(shorthand));
    }

    /**
     * Looks up ability score with given {@code abilityScore} enum on the API.
     *
     * @param abilityScore an enum representation of type {@link EAbilityScore}
     * @return the AbilityScore representative of given {@code abilityScore}, or {@code NULL} if not
     * found
     */
    @Nullable
    public AbilityScore lookup(@Nullable EAbilityScore abilityScore) {
        if (abilityScore == null) {
            return null;
        }

        return internalLookup(abilityScore);
    }

    @Nullable
    private AbilityScore internalLookup(EAbilityScore abilityScore) {
        return RestHelper.convertOrLog(String.format("%s/%s", ENDPOINT, abilityScore.getShorthand()), AbilityScore::new);
    }

}
