package cs3500.pa04.JSON;

import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa04.Model.ShipType;
import java.util.Map;

/**
 * Represents the JSON for the setup.
 */
public record SetupJson(@JsonProperty("width") int width,
                        @JsonProperty("height") int height,
                        @JsonProperty("fleet-spec")Map<ShipType, Integer> fleetSpec) {
}
