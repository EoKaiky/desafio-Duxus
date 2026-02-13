package br.com.duxusdesafio.service;

import br.com.duxusdesafio.dtos.time.input.CriarTimeInputDto;
import br.com.duxusdesafio.mappers.time.TimeStructMapper;
import br.com.duxusdesafio.model.ComposicaoTime;
import br.com.duxusdesafio.model.Integrante;
import br.com.duxusdesafio.model.Time;
import br.com.duxusdesafio.model.Integrante;
import br.com.duxusdesafio.repository.integrantes.IntegranteJpaRepository;
import br.com.duxusdesafio.repository.time.TimeJpaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static jakarta.persistence.GenerationType.UUID;
import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

/**
 * Service que possuirá as regras de negócio para o processamento dos dados
 * solicitados no desafio!
 *
 * OBS ao candidato: PREFERENCIALMENTE, NÃO ALTERE AS ASSINATURAS DOS MÉTODOS!
 * Trabalhe com a proposta pura.
 *
 * @author carlosau
 */
@Service
@RequiredArgsConstructor
public class ApiService {

    private final TimeJpaRepository repositoryTime;
    private final IntegranteJpaRepository repositoryIntegrante;
    private final TimeStructMapper timeStructMapper;

    /**
     * Metodo de Cadastro de time
     */
    @Transactional
    public Time criarTime(CriarTimeInputDto input) {

        Time time = this.timeStructMapper.toEntity(input);

        List<ComposicaoTime> listaComposicao = new ArrayList<>();

        if (input.getComposicaoTime() != null && !input.getComposicaoTime().isEmpty()) {
            for (Long id : input.getComposicaoTime()) {
                Integrante integrante = repositoryIntegrante.findById(id)
                        .orElseThrow(() -> new RuntimeException("Integrante nao encontrado ID:" + id));

                ComposicaoTime composicao = new ComposicaoTime();

                composicao.setIntegrante(integrante);
                composicao.setTime(time); // <--- O PULO DO GATO: Vínculo do Pai

                listaComposicao.add(composicao);
            }
        }

        time.setComposicaoTime(listaComposicao);

        return this.repositoryTime.save(time);
    }

    /**
     * Vai retornar um Time, com a composição do time daquela data
     */
    public Time timeDaData(LocalDate data, List<Time> todosOsTimes){
        // TODO Implementar método seguindo as instruções!
        return null;
    }

    /**
     * Vai retornar o integrante que estiver presente na maior quantidade de times
     * dentro do período
     */
    public Integrante integranteMaisUsado(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes){
        // TODO Implementar método seguindo as instruções!
        return null;
    }

    /**
     * Vai retornar uma lista com os nomes dos integrantes do time mais comum
     * dentro do período
     */
    public List<String> integrantesDoTimeMaisComum(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes){
        // TODO Implementar método seguindo as instruções!
        return null;
    }

    /**
     * Vai retornar a função mais comum nos times dentro do período
     */
    public String funcaoMaisComum(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes){
        // TODO Implementar método seguindo as instruções!
        return null;
    }

    /**
     * Vai retornar o nome da Franquia mais comum nos times dentro do período
     */
    public String franquiaMaisFamosa(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes) {
        // TODO Implementar método seguindo as instruções!
        return null;
    }


    /**
     * Vai retornar o número (quantidade) de Franquias dentro do período
     */
    public Map<String, Long> contagemPorFranquia(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes){
        // TODO Implementar método seguindo as instruções!
        return null;
    }

    /**
     * Vai retornar o número (quantidade) de Funções dentro do período
     */
    public Map<String, Long> contagemPorFuncao(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes){
        // TODO Implementar método seguindo as instruções!
        return null;
    }

}
