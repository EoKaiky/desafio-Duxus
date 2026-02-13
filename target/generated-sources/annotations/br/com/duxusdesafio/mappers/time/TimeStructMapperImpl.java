package br.com.duxusdesafio.mappers.time;

import br.com.duxusdesafio.dtos.time.input.CriarTimeInputDto;
import br.com.duxusdesafio.model.ComposicaoTime;
import br.com.duxusdesafio.model.Time;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-02-12T23:17:38-0300",
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
        List<ComposicaoTime> list = inputDto.getComposicaoTime();
        if ( list != null ) {
            time.setComposicaoTime( new ArrayList<ComposicaoTime>( list ) );
        }

        return time;
    }
}
