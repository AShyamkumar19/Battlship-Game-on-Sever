package cs3500.pa04.JSON;

import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa04.Model.GameResult;

/**
 * Represents the JSON for the endgame.
 */
public record EndgameJson(@JsonProperty("result") GameResult result,
                          @JsonProperty("reason") String reason) {
}
