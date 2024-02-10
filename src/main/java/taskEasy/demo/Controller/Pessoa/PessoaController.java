package taskEasy.demo.Controller.Pessoa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.*;
import taskEasy.demo.dto.CriarPessoaDTO;

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



}
