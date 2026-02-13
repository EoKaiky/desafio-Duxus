package br.com.duxusdesafio.mappers.time;

import br.com.duxusdesafio.dtos.time.input.CriarTimeInputDto;
import br.com.duxusdesafio.model.Time;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TimeStructMapper {
    Time toEntity(CriarTimeInputDto inputDto);
}
