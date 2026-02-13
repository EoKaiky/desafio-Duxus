package br.com.duxusdesafio.mappers.time;

import br.com.duxusdesafio.dtos.time.input.CriarTimeInputDto;
import br.com.duxusdesafio.model.Time;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TimeStructMapper {

    @Mapping(target = "composicaoTime", ignore = true)
    @Mapping(target = "id", ignore = true)
    Time toEntity(CriarTimeInputDto inputDto);
}
