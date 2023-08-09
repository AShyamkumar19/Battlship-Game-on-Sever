package cs3500.pa04.JSON;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents the JSON for the join.
 */
public record JoinJson(@JsonProperty("name") String githubUser,
                       @JsonProperty("game-type") String gameType) {
}
