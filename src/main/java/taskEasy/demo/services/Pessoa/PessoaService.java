package taskEasy.demo.services.Pessoa;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import taskEasy.demo.dto.Pessoa.AtualizarPessoaDTO;
import taskEasy.demo.dto.Pessoa.CriarPessoaDTO;
import taskEasy.demo.exceptions.UsuarioInexistenteException;
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


    public Pessoa atualizarPessoa(int id, AtualizarPessoaDTO pessoa) {

        Optional<Pessoa> pessoa1 = this.pessoaRepository.findById(id);

        if(pessoa1.isEmpty()) {

            throw new UsuarioInexistenteException("Usuário não encontrado.");
        }

        Pessoa pessoaObjeto = pessoa1.get();

        if(pessoa.nome() != null){
            pessoaObjeto.setNome(pessoa.nome());
        }

        if(pessoa.email() != null) {
            pessoaObjeto.setEmail(pessoa.email());
        }

        if(pessoa.ativo() != 0) {
            pessoaObjeto.setAtivo(pessoa.ativo());
        }


        return this.pessoaRepository.save(pessoaObjeto);

    }


}
