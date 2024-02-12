package taskEasy.demo.Controller.Pessoa;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import taskEasy.demo.dto.Pessoa.AtualizarPessoaDTO;
import taskEasy.demo.dto.Pessoa.CriarPessoaDTO;

import taskEasy.demo.dto.Pessoa.EncontrarPessoaPorParametroDTO;
import taskEasy.demo.dto.Tarefa.TarefaPorPeriodoDTO;
import taskEasy.demo.exceptions.UsuarioInexistenteException;
import taskEasy.demo.helpers.MediaResponsavel;
import taskEasy.demo.models.DataResponse;
import taskEasy.demo.models.entity.Pessoa;
import taskEasy.demo.models.entity.Tarefa;
import taskEasy.demo.services.Pessoa.PessoaService;

import javax.xml.crypto.Data;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/pessoa")
public class PessoaController {

    @Autowired
    PessoaService pessoaService;


    @GetMapping()
    public ResponseEntity<DataResponse<List<Pessoa>>> getTodasPessoas() {
        List<Pessoa> pessoas = this.pessoaService.todasPessoas();
        return ResponseEntity.status(HttpStatus.OK).body(new DataResponse(true,pessoas ));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataResponse<List<Pessoa>>> getPessoa(@PathVariable int id) {
        Optional<Pessoa> pessoa = this.pessoaService.getPessoa(id);
        return ResponseEntity.status(HttpStatus.OK).body(new DataResponse(true, pessoa));
    }


    @PostMapping()
    public ResponseEntity<DataResponse<Pessoa>> criarPessoa(
            @RequestBody(required = true) CriarPessoaDTO pessoaDTO
    ) {
        Pessoa pessoa = this.pessoaService.criarPessoa(pessoaDTO);

        return ResponseEntity.status(HttpStatus.OK).body(new DataResponse<>(true, pessoa));
    }

    @DeleteMapping()
    public ResponseEntity<DataResponse<String>> deletarPessoa(@RequestParam int id) {
        this.pessoaService.deletarPessoa(id);
        return ResponseEntity.status(HttpStatus.OK).body(new DataResponse<>(true, "Pessoa deletada"));
    }

    @PutMapping
    public ResponseEntity<DataResponse<Pessoa>> atualizarPessoa(@RequestParam int id, @RequestBody AtualizarPessoaDTO pessoa) throws Exception {
        try {
            Pessoa pessoa_salva = this.pessoaService.atualizarPessoa(id, pessoa);
            return ResponseEntity.status(HttpStatus.OK).body(new DataResponse<>(true,pessoa_salva));
        } catch (Exception e) {
            if (e instanceof UsuarioInexistenteException) throw new UsuarioInexistenteException(e.getMessage());
            if (e != null) throw new Exception(e.getMessage());


        }
        return null;
    }


    @GetMapping(path = "/encontrar")
    public ResponseEntity<DataResponse<List<Pessoa>>> encontrarPessoaPorParametros(@RequestParam String nome, @RequestParam String departamento) {
        List<Pessoa> pessoaEncontrada = this.pessoaService.encontrarPessoaPorParametros(nome, departamento);
        return ResponseEntity.status(HttpStatus.OK).body(new DataResponse<>(true, pessoaEncontrada));
    }

    @PostMapping("/media")
    public ResponseEntity<DataResponse<MediaResponsavel>> mediaResponsavel(@RequestBody TarefaPorPeriodoDTO tarefaPorPeriodoDTO) throws ParseException {

            MediaResponsavel mediaResponsavel = this.pessoaService.mediaResponsavel(tarefaPorPeriodoDTO);
            return ResponseEntity.status(HttpStatus.OK).body(new DataResponse<>(true, mediaResponsavel));
        }
    }



