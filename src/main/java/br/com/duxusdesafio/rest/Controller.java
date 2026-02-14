package br.com.duxusdesafio.rest;

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

@RestController
@RequestMapping("/times")
@RequiredArgsConstructor
public class Controller {

    private final ApiService service;
    private final TimeJpaRepository timeRepository;

    @PostMapping()
    public ResponseEntity<Time> criarTime (@RequestBody CriarTimeInputDto inputDto) {
        Time timeSalvo = service.criarTime(inputDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(timeSalvo);
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

    @GetMapping("/Franquia-mais-famosa-periodo")
    public ResponseEntity<FranquiaMaisFamosaOutputDTO> franquiaMaisFamosa(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicial,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFinal) {
        List<Time> listaTodosOsTimes = timeRepository.findAll();

        var franquia = service.franquiaMaisFamosa(dataInicial, dataFinal, listaTodosOsTimes);

        return ResponseEntity.ok(new FranquiaMaisFamosaOutputDTO(franquia));
    }
}
