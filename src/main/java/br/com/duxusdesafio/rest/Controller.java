package br.com.duxusdesafio.rest;

import br.com.duxusdesafio.dtos.integrante.input.CriarIntegranteInputDTO;
import br.com.duxusdesafio.dtos.time.input.CriarTimeInputDto;
import br.com.duxusdesafio.dtos.time.output.FranquiaMaisFamosaOutputDTO;
import br.com.duxusdesafio.dtos.time.output.FuncaoMaisComumOutputDTO;
import br.com.duxusdesafio.dtos.time.output.IntegranteMaisUsadoOutputDTO;
import br.com.duxusdesafio.model.Integrante;
import br.com.duxusdesafio.model.Time;
import br.com.duxusdesafio.repository.time.TimeJpaRepository;
import br.com.duxusdesafio.service.ApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/times")
@RequiredArgsConstructor
public class Controller {

    private final ApiService service;
    private final TimeJpaRepository timeRepository;

    @PostMapping("/criar-time")
    public ResponseEntity<Time> criarTime (@RequestBody CriarTimeInputDto inputDto) {
        Time timeSalvo = service.criarTime(inputDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(timeSalvo);
    }

    @PostMapping("/criar-integrante")
    public ResponseEntity<Integrante> criarIntegrante (@RequestBody CriarIntegranteInputDTO inputDto) {
        Integrante integrante = service.criarIntegrante(inputDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(integrante);
    }

    @GetMapping("/time-da-data")
    public ResponseEntity<Time> timeDaData(@RequestParam("data") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data) {
        List<Time> listaTodosOsTimes = timeRepository.findAll();

        Time timeEncontrado = service.timeDaData(data, listaTodosOsTimes);

        if (timeEncontrado == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(timeEncontrado);
    }

    @GetMapping("/Integrante-mais-usado-periodo")
    public ResponseEntity<IntegranteMaisUsadoOutputDTO> integranteMaisUsado(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicial,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFinal) {
        List<Time> listaTodosOsTimes = timeRepository.findAll();

        Integrante integrante = service.integranteMaisUsado(dataInicial, dataFinal, listaTodosOsTimes);

        if (integrante == null) {
                return ResponseEntity.notFound().build();
        }


        return ResponseEntity.ok(new IntegranteMaisUsadoOutputDTO(integrante));
    }

    @GetMapping("/funcao-mais-comum-periodo")
    public ResponseEntity<FuncaoMaisComumOutputDTO> funcaoMaisComum(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicial,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFinal) {
        List<Time> listaTodosOsTimes = timeRepository.findAll();

         var funcao = service.funcaoMaisComum(dataInicial, dataFinal, listaTodosOsTimes);

        return ResponseEntity.ok(new FuncaoMaisComumOutputDTO(funcao));
    }

    @GetMapping("/Integrantes-time-mais-comum")
    public ResponseEntity<List<String>> integrantesDoTimeMaisComum(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicial,
                                                   @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFinal) {
        List<Time> listaTodosOsTimes = timeRepository.findAll();

        List<String> resultado = service.integrantesDoTimeMaisComum(dataInicial, dataFinal, listaTodosOsTimes);
        return ResponseEntity.ok(resultado);
    }

    @GetMapping("/Franquia-mais-famosa-periodo")
    public ResponseEntity<FranquiaMaisFamosaOutputDTO> franquiaMaisFamosa(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicial,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFinal) {
        List<Time> listaTodosOsTimes = timeRepository.findAll();

        var franquia = service.franquiaMaisFamosa(dataInicial, dataFinal, listaTodosOsTimes);

        return ResponseEntity.ok(new FranquiaMaisFamosaOutputDTO(franquia));
    }

    @GetMapping("/Quantidade-de-franquias-periodo")
    public Map<String, Long> getContagemFranquia(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicial,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFinal) {
        List<Time> listaTodosOsTimes = timeRepository.findAll();

        return service.contagemPorFranquia(dataInicial, dataFinal, listaTodosOsTimes);
    }

    @GetMapping("/Quantidade-de-funcao-periodo")
    public Map<String, Long> getContagemFuncao(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicial,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFinal) {
        List<Time> listaTodosOsTimes = timeRepository.findAll();

        return service.contagemPorFuncao(dataInicial, dataFinal, listaTodosOsTimes);
    }
}
