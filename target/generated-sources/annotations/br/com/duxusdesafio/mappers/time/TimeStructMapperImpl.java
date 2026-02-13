package br.com.duxusdesafio.mappers.time;

import br.com.duxusdesafio.dtos.time.input.CriarTimeInputDto;
import br.com.duxusdesafio.model.Time;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-02-13T08:44:46-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.10 (Oracle Corporation)"
)
@Component
public class TimeStructMapperImpl implements TimeStructMapper {

    @Override
    public Time toEntity(CriarTimeInputDto inputDto) {
        if ( inputDto == null ) {
            return null;
        }

        Time time = new Time();

        time.setData( inputDto.getData() );

        return time;
    }
}
