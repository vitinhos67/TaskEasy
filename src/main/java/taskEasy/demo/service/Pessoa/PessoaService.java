package taskEasy.demo.service.Pessoa;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import taskEasy.demo.dto.CriarPessoaDTO;
import taskEasy.demo.models.entity.Pessoa;
import taskEasy.demo.models.repository.PessoaRepository;

import java.util.List;

@Service
public class PessoaService {

    @Autowired
    PessoaRepository pessoaRepository;

    public List<Pessoa> todasPessoas() {
        return this.pessoaRepository.findAll();
    }

    public Pessoa criarPessoa(@NotNull CriarPessoaDTO pessoa) {
        Pessoa new_pessoa = new Pessoa(pessoa.nome(), pessoa.email());

        return this.pessoaRepository.save(new_pessoa);
    }


}
