package be.bcdi.immo.scrapers;

import be.bcdi.immo.entities.ImmoProperty;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import java.util.Optional;

@Mapper
public interface ImmowebMapper {
    @Mappings({
         //   @Mapping(target = "id", source = "entity.sourceId"),
           // @Mapping(target = "source", source = "entity.source"),
    })
    ImmowebProperty propertyToImmowebDTO(ImmoProperty entity);

    @Mappings({
            @Mapping(target = "sourceId", source = "dto.id"),
            @Mapping(target = "source", source = "dto.source"),
            @Mapping(target = "bedroomCount", source = "dto.bedroomCount"),
            @Mapping(target = "landSurface", source = "dto.landSurface"),
            @Mapping(target = "netHabitableSurface", source = "dto.netHabitableSurface"),
            @Mapping(target = "sellingPrice", source = "dto.price"),
            @Mapping(target = "rentingPrice", source = "dto.monthlyRentalPrice"),
            @Mapping(target = "propertyType", source = "dto.propertyType"),
            @Mapping(target = "peb", source = "dto.peb"),
    })
    ImmoProperty immowebDTOtoProperty(ImmowebProperty dto);



}
