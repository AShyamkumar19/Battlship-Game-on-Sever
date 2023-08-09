package cs3500.pa04.JSON;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

public record FleetSpecJson(@JsonProperty("fleet-spec") Map<String, Integer> fleetSpec) {
}
