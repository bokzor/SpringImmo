package be.bcdi.immo.utils;

import java.util.Map;
import java.util.Optional;

public class JsonUtils {

    static public <T> Optional<T> get(Map<String, Object> jsonMapped, String nestedProperty, Class<T> t) throws IllegalAccessException, InstantiationException {
        String[] properties = nestedProperty.split("\\.");
        Optional<Object> current = Optional.empty();
        for (String property : properties) {
            if (current.isPresent()) {
                current = Optional.ofNullable(((Map<String, Object>) current.get()).getOrDefault(property, null));
            } else {
                current = Optional.ofNullable(jsonMapped.get(property));
            }
        }

        if (t.isEnum() && current.isPresent()) {
            Class<? extends Enum> myEnum = t.asSubclass(Enum.class);
            try {
                final Enum<?> valueEnum = Enum.valueOf(myEnum, (String) current.get());
                return (Optional<T>) Optional.of(valueEnum);
            } catch (IllegalArgumentException ex) {
                return Optional.empty();
            }
        } else {
            return (Optional<T>) current;
        }
    }

}
