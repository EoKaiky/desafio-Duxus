package br.com.duxusdesafio.service;

import br.com.duxusdesafio.dtos.integrante.input.CriarIntegranteInputDTO;
import br.com.duxusdesafio.dtos.time.input.CriarTimeInputDto;
import br.com.duxusdesafio.mappers.integrantes.IntegranteStructMapper;
import br.com.duxusdesafio.mappers.time.TimeStructMapper;
import br.com.duxusdesafio.model.ComposicaoTime;
import br.com.duxusdesafio.model.Integrante;
import br.com.duxusdesafio.model.Time;
import br.com.duxusdesafio.repository.integrantes.IntegranteJpaRepository;
import br.com.duxusdesafio.repository.time.TimeJpaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

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
    private final IntegranteStructMapper integranteStructMapper;

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
                composicao.setTime(time);

                listaComposicao.add(composicao);
            }
        }

        time.setComposicaoTime(listaComposicao);

        return this.repositoryTime.save(time);
    }

    /**
     * Metodo de Cadastro de integrante
     */
    @Transactional
    public Integrante criarIntegrante(CriarIntegranteInputDTO inputDTO){
        Integrante integrante = this.integranteStructMapper.toEntity(inputDTO);
        return this.repositoryIntegrante.save(integrante);
    }

    /**
     * Vai retornar um Time, com a composição do time daquela data
     */
    public Time timeDaData(LocalDate data, List<Time> todosOsTimes){
        return todosOsTimes.stream()
                .filter(time -> time.getData().equals(data))
                .findFirst()
                .orElse(null);
    }

    /**
     * Vai retornar o integrante que estiver presente na maior quantidade de times
     * dentro do período
     */
    public Integrante integranteMaisUsado(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes){
        return todosOsTimes.stream()
                .filter(time -> !time.getData().isBefore(dataInicial) && !time.getData().isAfter(dataFinal))
                .flatMap(time -> time.getComposicaoTime().stream())
                .map(ComposicaoTime::getIntegrante)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }


    public List<String> integrantesDoTimeMaisComum(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes){
        return todosOsTimes.stream()
                .filter(time -> (dataInicial == null || !time.getData().isBefore(dataInicial)) &&
                        (dataFinal == null || !time.getData().isAfter(dataFinal)))
                .map(time -> time.getComposicaoTime().stream()
                        .map(comp -> comp.getIntegrante().getNome())
                        .sorted() // Ordenação é crucial para comparação de listas
                        .collect(Collectors.toList()))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(new ArrayList<>());
    }


    public String funcaoMaisComum(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes){
        return todosOsTimes.stream()
                .filter(time -> !time.getData().isBefore(dataInicial) && !time.getData().isAfter(dataFinal))
                .flatMap(time -> time.getComposicaoTime().stream())
                .map(composicaoTime -> composicaoTime.getIntegrante())
                .filter(Objects::nonNull)
                .map(integrante -> integrante.getFuncao())
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }

    /**
     * Vai retornar o nome da Franquia mais comum nos times dentro do período
     */
    public String franquiaMaisFamosa(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes) {
        return todosOsTimes.stream()
                .filter(time -> !time.getData().isBefore(dataInicial) && !time.getData().isAfter(dataFinal))
                .flatMap(time -> time.getComposicaoTime().stream())
                .map(composicaoTime -> composicaoTime.getIntegrante())
                .filter(Objects::nonNull)
                .map(integrante -> integrante.getFranquia())
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
    }


    /**
     * Vai retornar o número (quantidade) de Franquias dentro do período
     */
    public Map<String, Long> contagemPorFranquia(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes) {
        return todosOsTimes.stream()
                .filter(time ->
                        (dataInicial == null || time.getData().isAfter(dataInicial)) && (dataFinal   == null || !time.getData().isAfter(dataFinal)))
                .flatMap(time -> time.getComposicaoTime().stream()
                        .map(ComposicaoTime::getIntegrante)
                        .filter(i -> i != null && i.getFranquia() != null && !i.getFranquia().trim().isEmpty())
                        .map(i -> i.getFranquia().trim())
                        .distinct())
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

    /**
     * Vai retornar o número (quantidade) de Funções dentro do período
     */
    public Map<String, Long> contagemPorFuncao(LocalDate dataInicial, LocalDate dataFinal, List<Time> todosOsTimes){
        return todosOsTimes.stream()
                .filter(time -> (dataInicial == null || time.getData().isAfter(dataInicial)) &&
                                      (dataFinal   == null || !time.getData().isAfter(dataFinal)))
                .flatMap(time -> time.getComposicaoTime().stream()
                .map(ComposicaoTime::getIntegrante))
                .filter(i -> i != null && i.getNome() != null && i.getFuncao() != null && !i.getFuncao().trim().isEmpty())
                .collect(Collectors.collectingAndThen(Collectors.toMap(i -> i.getNome().trim().toLowerCase(),i -> i,
                        (existente, novo) -> existente), map -> map.values().stream()))
                .map(i -> i.getFuncao().trim())
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

}
