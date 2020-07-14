package ch.trox.dnd5eapi.endpoint.character.skill;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import ch.trox.dnd5eapi.DnD5eApiWrapper;
import ch.trox.dnd5eapi.endpoint.character.abilityscore.EAbilityScore;
import ch.trox.dnd5eapi.internal.RestHelper;

import static org.assertj.core.api.Assertions.assertThat;

public class SkillLedgerTest {
    @BeforeAll
    static void setUp() {
        RestHelper.init();
    }

    @Test
    void lookupByNonExistingShorthand() {
        assertThat(DnD5eApiWrapper.character()
                .skill("blabla")).isNull();
    }

    @Test
    void testLookup() {
        for (ESkill skill : ESkill.values()) {
            assertThat(DnD5eApiWrapper.character()
                    .skill(skill)).isNotNull();
        }
    }

    @Test
    void testMapping() {
        Skill skill = DnD5eApiWrapper.character().skill(ESkill.SLEIGHT_OF_HAND);
        assertThat(skill).isNotNull();
        assertThat(skill.getName()).isEqualTo("Sleight of Hand");
        assertThat(skill.getShorthand()).isEqualTo("sleight-of-hand");
        assertThat(skill.getDescription()).contains("legerdemain or manual trickery");
        assertThat(skill.getAbilityScore()).isEqualTo(EAbilityScore.DEXTERITY);
    }
}
