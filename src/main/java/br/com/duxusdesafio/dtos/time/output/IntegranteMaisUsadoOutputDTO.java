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
public class IntegranteMaisUsadoOutputDTO {
    private String nome;

    public IntegranteMaisUsadoOutputDTO(Integrante integrante) {
        if (integrante != null) {
            this.nome = integrante.getNome();
        }
    }
}
