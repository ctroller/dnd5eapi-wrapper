package ch.trox.dnd5eapi.endpoint.character.skill;

import java.util.Objects;
import java.util.regex.Pattern;

import javax.annotation.Nonnull;

import ch.trox.dnd5eapi.endpoint.character.abilityscore.EAbilityScore;
import kong.unirest.JsonNode;
import kong.unirest.json.JSONObject;

public class Skill {
    private static final Pattern SHORTHAND_PATTERN = Pattern.compile("/api/ability-scores/");

    private final String shorthand;
    private final String name;
    private final String description;
    private final EAbilityScore abilityScore;

    public Skill(@Nonnull JsonNode node) {
        JSONObject object = Objects.requireNonNull(node.getObject());
        shorthand = object.getString("index");
        name = object.getString("name");
        description = object.getJSONArray("desc")
                .join(System.lineSeparator());
        abilityScore = EAbilityScore.of(SHORTHAND_PATTERN.matcher(object.getJSONObject("ability_score")
                .getString("url"))
                .replaceAll(""));
    }

    public String getShorthand() {
        return shorthand;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public EAbilityScore getAbilityScore() {
        return abilityScore;
    }
}
