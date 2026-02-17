package br.com.duxusdesafio.dtos.integrante.input;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CriarIntegranteInputDTO {
    private String franquia;

    private String nome;

    private String funcao;
}
