package cs3500.pa04.JSON;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * Represents the JSON for the message.
 */
public record MessageJson(@JsonProperty("method-name") String method,
                          @JsonProperty("arguments") JsonNode args) {
}
