package be.bcdi.immo.utils;

import java.util.Map;
import java.util.Optional;

public class JsonUtils {

  static public <T> Optional<T> get(Map<String, Object> jsonMapped, String nestedProperty, T t) {
    String[] properties = nestedProperty.split(".");
    Optional<Object> current = Optional.empty();
    for(String property: properties) {
      if(current.isPresent()) {
        Object test = ((Map<String, Object>)current.get()).get(property);
      }
      current = Optional.of(jsonMapped.get(property));
    }
    return (Optional<T>) current;

  }
}
