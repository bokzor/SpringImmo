package be.bcdi.immo.utils;

import java.util.Map;
import java.util.Optional;

public class JsonUtils {

  static public <T> Optional<T> get(Map<String, Object> jsonMapped, String nestedProperty, Class<T> t) {
    String[] properties = nestedProperty.split("\\.");
    Optional<Object> current = Optional.empty();
    for(String property: properties) {
      if(current.isPresent()) {
        St
        current = Optional.of(((Map<String, Object>)current.get()).getOrDefault(property, null));
      }
      current = Optional.of(jsonMapped.get(property));
    }
    return (Optional<T>) current;

  }
}
