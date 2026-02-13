package br.com.duxusdesafio.dtos.time.output;

import br.com.duxusdesafio.model.Integrante;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FuncaoMaisComumOutputDTO {
    private String funcao;

    public FuncaoMaisComumOutputDTO(Integrante integrante) {
        if (integrante != null) {
            this.funcao = integrante.getNome();
        }
    }
}
