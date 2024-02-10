package taskEasy.demo.service.Pessoa;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import taskEasy.demo.dto.CriarPessoaDTO;
import taskEasy.demo.models.entity.Pessoa;
import taskEasy.demo.models.repository.PessoaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {

    @Autowired
    PessoaRepository pessoaRepository;

    public List<Pessoa> todasPessoas() {
        return this.pessoaRepository.findAll();
    }

    public Pessoa criarPessoa(@NotNull CriarPessoaDTO pessoa) {
        Pessoa new_pessoa = new Pessoa(pessoa.nome(), pessoa.email(), pessoa.ativo());
        return this.pessoaRepository.save(new_pessoa);
    }


    public Optional<Pessoa> getPessoa(int id) {
        return this.pessoaRepository.findById(id);
    }

    public void deletarPessoa(int id) {
        this.pessoaRepository.deleteById(id);
    }



}
