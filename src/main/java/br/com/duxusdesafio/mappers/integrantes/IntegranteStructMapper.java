package br.com.duxusdesafio.mappers.integrantes;

import br.com.duxusdesafio.dtos.integrante.input.CriarIntegranteInputDTO;
import br.com.duxusdesafio.model.Integrante;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IntegranteStructMapper {
    Integrante toEntity(CriarIntegranteInputDTO inputDto);
}
