package cs3500.pa04.JSON;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * Represents the JSON for the fleet.
 */
public record FleetJson(@JsonProperty("fleet") List<ShipJson> fleet) {
}
