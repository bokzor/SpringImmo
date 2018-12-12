package be.bcdi.immo.scrapers;

import be.bcdi.immo.entities.ImmoAddress;
import be.bcdi.immo.entities.ImmoProperty;
import be.bcdi.immo.utils.OptionalMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import java.util.Optional;

@Mapper(componentModel = "spring", uses = OptionalMapper.class)
public interface ImmowebAddressMapper {
  @Mappings({
    //   @Mapping(target = "id", source = "entity.sourceId"),
    // @Mapping(target = "source", source = "entity.source"),
  })
  ImmowebAddress propertyToImmowebDTO(ImmoAddress address);

  @Mappings({
    @Mapping(target = "latitude", source = "dto.latitude"),
    @Mapping(target = "longitude", source = "dto.longitude"),
    @Mapping(target = "country", source = "dto.country"),
    @Mapping(target = "postalCode", source = "dto.postalCode"),
    @Mapping(target = "street", source = "street"),
    @Mapping(target = "number", source = "dto.number"),
    @Mapping(target = "floor", source = "dto.floor"),
    @Mapping(target = "locality", source = "dto.locality")
  })
  ImmoAddress immowebAddressDTOtoImmoAddress(ImmowebAddress dto);

}
