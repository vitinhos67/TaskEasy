package taskEasy.demo.Controller.Pessoa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import taskEasy.demo.dto.CriarPessoaDTO;
import taskEasy.demo.models.entity.Pessoa;
import taskEasy.demo.models.repository.PessoaRepository;
import taskEasy.demo.service.Pessoa.PessoaService;

import java.util.List;

@RestController
@RequestMapping(path = "/api/pessoa")
public class PessoaController {

    @Autowired
    PessoaService pessoaService;

    @GetMapping()
    public List<Pessoa> getTodasPessoas() {
        return this.pessoaService.todasPessoas();
    }

    @PostMapping(path= "/criar")
    public Pessoa criarPessoa(
            @RequestBody(required = true) CriarPessoaDTO pessoa
            ) {

        return this.pessoaService.criarPessoa(pessoa);
    }


}
