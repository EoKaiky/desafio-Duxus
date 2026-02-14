package br.com.duxusdesafio.dtos.time.output;

import br.com.duxusdesafio.model.Integrante;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FranquiaMaisFamosaOutputDTO {
    private String franquia;

    public FranquiaMaisFamosaOutputDTO(Integrante integrante) {
        if (integrante != null) {
            this.franquia = integrante.getNome();
        }
    }
}
