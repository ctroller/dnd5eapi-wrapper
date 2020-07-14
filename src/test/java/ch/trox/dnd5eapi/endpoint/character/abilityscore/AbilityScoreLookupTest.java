package ch.trox.dnd5eapi.endpoint.character.abilityscore;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import ch.trox.dnd5eapi.DnD5eApiWrapper;
import ch.trox.dnd5eapi.internal.RestHelper;

import static org.assertj.core.api.Assertions.assertThat;

class AbilityScoreLookupTest {
    @BeforeAll
    static void setUp() {
        RestHelper.init();
    }

    @Test
    void lookupByNonExistingShorthand() {
        assertThat(DnD5eApiWrapper.character()
                .ability("blabla")).isNull();
    }

    @Test
    void testLookup() {
        for (EAbilityScore score : EAbilityScore.values()) {
            assertThat(DnD5eApiWrapper.character()
                    .ability(score)).isNotNull();
        }
    }

    @Test
    void testStrengthMapping() {
        AbilityScore strength = DnD5eApiWrapper.character().ability(EAbilityScore.STRENGTH);
        assertThat(strength).isNotNull();
        assertThat(strength.getName()).isEqualTo("Strength");
        assertThat(strength.getShorthand()).isEqualTo("STR");
        assertThat(strength.getDescription()).contains("bodily power");
    }
}