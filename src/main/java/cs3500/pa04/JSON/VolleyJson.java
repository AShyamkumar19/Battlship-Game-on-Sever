package cs3500.pa04.JSON;

import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa04.Model.Coord;
import java.util.List;

/**
 * Represents the JSON for the volley.
 */
public record VolleyJson(@JsonProperty("coordinates") List<Coord> coords) {
}
