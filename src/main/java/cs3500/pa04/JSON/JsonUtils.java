package cs3500.pa04.JSON;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Represents JSON serialization utilities.
 */
public class JsonUtils {

  /**
   * Serializes a record into a JSON node.
   *
   * @param record - record to be serialized
   * @return - JSON node
   */
  public static JsonNode serializeRecord(Object record) {
    try {
      ObjectMapper mapper = new ObjectMapper();
      return mapper.convertValue(record, JsonNode.class);
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException("Given record cannot be serialized");
    }
  }
}
