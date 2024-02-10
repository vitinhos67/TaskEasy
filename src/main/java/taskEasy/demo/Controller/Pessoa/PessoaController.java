package taskEasy.demo.Controller.Pessoa;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import taskEasy.demo.dto.AtualizarPessoaDTO;
import taskEasy.demo.dto.CriarPessoaDTO;

import taskEasy.demo.exceptions.UsuarioInexistenteException;
import taskEasy.demo.models.entity.Pessoa;
import taskEasy.demo.models.repository.PessoaRepository;
import taskEasy.demo.service.Pessoa.PessoaService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/pessoa")
public class PessoaController {

    @Autowired
    PessoaService pessoaService;

    @GetMapping()
    public List<Pessoa> getTodasPessoas() {
        return this.pessoaService.todasPessoas();
    }

    @GetMapping("/{id}")
    public Optional<Pessoa> getPessoa(@PathVariable int id) {
        return this.pessoaService.getPessoa(id);
    }


    @PostMapping()
    public Pessoa criarPessoa(
            @RequestBody(required = true) CriarPessoaDTO pessoa
    ) {

        return this.pessoaService.criarPessoa(pessoa);
    }

    @DeleteMapping()
    public String deletarPessoa(@RequestParam int id) {
        this.pessoaService.deletarPessoa(id);
        return "deletado.";
    }

    @PutMapping
    public ResponseEntity<Pessoa> atualizarPessoa(@RequestParam int id, @RequestBody AtualizarPessoaDTO pessoa) throws Exception {
        try {
            Pessoa pessoa_salva = this.pessoaService.atualizarPessoa(id, pessoa);


            return ResponseEntity.status(HttpStatus.OK).body(pessoa_salva);
        } catch (Exception e) {
            if (e instanceof UsuarioInexistenteException) throw new UsuarioInexistenteException(e.getMessage());

            if (e != null) throw new Exception(e.getMessage());


        }
        return null;
    }
}