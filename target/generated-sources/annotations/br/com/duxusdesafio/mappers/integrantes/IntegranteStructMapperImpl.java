package br.com.duxusdesafio.mappers.integrantes;

import br.com.duxusdesafio.dtos.integrante.input.CriarIntegranteInputDTO;
import br.com.duxusdesafio.model.Integrante;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-02-16T15:43:21-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.10 (Oracle Corporation)"
)
@Component
public class IntegranteStructMapperImpl implements IntegranteStructMapper {

    @Override
    public Integrante toEntity(CriarIntegranteInputDTO inputDto) {
        if ( inputDto == null ) {
            return null;
        }

        Integrante integrante = new Integrante();

        integrante.setFranquia( inputDto.getFranquia() );
        integrante.setNome( inputDto.getNome() );
        integrante.setFuncao( inputDto.getFuncao() );

        return integrante;
    }
}
