package be.bcdi.immo.utils;

import org.mapstruct.Mapper;

import java.util.Optional;

@Mapper(componentModel = "spring")
public class OptionalMapper {
    public <T> T map(Optional<T> optional) {
        return optional.orElse(null);
    }

    public <T> Optional<T> map(T value) {
        return Optional.of(value);
    }

}
