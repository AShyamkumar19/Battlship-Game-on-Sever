package cs3500.pa04.JSON;

import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa04.Model.Coord;
import cs3500.pa04.Model.DirectionType;

/**
 * Represents the JSON for the ship.
 */
public record ShipJson(@JsonProperty("coord") Coord coord,
                       @JsonProperty("length") int length,
                       @JsonProperty("direction") DirectionType direction) {

}
