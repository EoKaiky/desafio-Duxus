package br.com.duxusdesafio.dtos.time.input;

import br.com.duxusdesafio.model.ComposicaoTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CriarTimeInputDto {
    private LocalDate data;

    private List<ComposicaoTime> composicaoTime;
}
