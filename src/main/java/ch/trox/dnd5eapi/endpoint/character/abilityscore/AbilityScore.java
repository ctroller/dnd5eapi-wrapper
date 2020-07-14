package ch.trox.dnd5eapi.endpoint.character.abilityscore;

import com.google.common.collect.ImmutableList;

import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

import javax.annotation.Nonnull;

import ch.trox.dnd5eapi.endpoint.character.skill.ESkill;
import kong.unirest.JsonNode;
import kong.unirest.json.JSONObject;

public class AbilityScore {
    private static final Pattern SHORTHAND_PATTERN = Pattern.compile("/api/skills/");

    private final String shorthand;
    private final String name;
    private final String description;
    private final List<ESkill> skills;

    public AbilityScore(@Nonnull JsonNode node) {
        JSONObject object = Objects.requireNonNull(node.getObject());
        shorthand = object.getString("index");
        name = object.getString("full_name");
        description = object.getJSONArray("desc")
                .join(System.lineSeparator());

        //Don't pre-load skills to reduce API usage. Skills will be loaded on-demand.
        //noinspection unchecked
        skills = (List<ESkill>) object.getJSONArray("skills")
                .toList()
                .stream()
                .map(obj -> SHORTHAND_PATTERN.matcher(((JSONObject) obj).getString("url"))
                        .replaceAll(""))
                .map(obj -> ESkill.of((String) obj))
                .filter(Objects::nonNull)
                .collect(ImmutableList.toImmutableList());
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

    @Nonnull
    public List<ESkill> getSkills() {
        return skills;
    }
}
