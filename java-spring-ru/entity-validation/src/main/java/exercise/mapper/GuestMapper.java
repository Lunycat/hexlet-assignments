package exercise.mapper;

import exercise.dto.GuestCreateDTO;
import exercise.dto.GuestDTO;
import org.mapstruct.*;

import exercise.model.Guest;

@Mapper(
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    componentModel = MappingConstants.ComponentModel.SPRING,
    unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface GuestMapper {

    Guest map(GuestCreateDTO dto);
    GuestDTO map(Guest model);
}
