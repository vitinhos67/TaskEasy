package taskEasy.demo.services.Pessoa;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import taskEasy.demo.dto.Pessoa.AtualizarPessoaDTO;
import taskEasy.demo.dto.Pessoa.CriarPessoaDTO;
import taskEasy.demo.dto.Pessoa.EncontrarPessoaPorParametroDTO;
import taskEasy.demo.dto.Tarefa.TarefaPorPeriodoDTO;
import taskEasy.demo.exceptions.DepartamentoInvalido;
import taskEasy.demo.exceptions.UsuarioInexistenteException;
import taskEasy.demo.helpers.MediaResponsavel;
import taskEasy.demo.models.DataResponse;
import taskEasy.demo.models.entity.Departamento;
import taskEasy.demo.models.entity.Pessoa;
import taskEasy.demo.models.entity.Tarefa;
import taskEasy.demo.models.repository.PessoaRepository;
import taskEasy.demo.models.repository.TarefaRepository;
import taskEasy.demo.services.Departamento.DepartamentoService;
import taskEasy.demo.services.Tarefa.TarefaService;

import javax.print.attribute.standard.Media;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {

    @Autowired
    PessoaRepository pessoaRepository;

    @Autowired
    DepartamentoService departamentoService;

    @Autowired
    TarefaRepository tarefaRepository;



    public List<Pessoa> todasPessoas() {
        return this.pessoaRepository.findAll();
    }

    public Pessoa criarPessoa(CriarPessoaDTO pessoa) {

        Departamento departamento = null;
        Pessoa new_pessoa = null;
        if(pessoa.departamento() != null) {
            departamento = this.departamentoService.encontrarDepartamentoPorNome(pessoa.departamento());

            if(departamento == null) {
                throw new DepartamentoInvalido("Departamento não encontrado");
            }

            departamentoService.adicionarQtdPessoa(departamento);
            new_pessoa = new Pessoa(pessoa.nome(), pessoa.email(), pessoa.ativo(), departamento.getNome());

        } else {
            new_pessoa = new Pessoa(pessoa.nome(), pessoa.email(), pessoa.ativo(), null);
        }

        return this.pessoaRepository.save(new_pessoa);
    }

    public Pessoa encontrarPessoa(int id) {
        return this.pessoaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Pessoa não encontrada com o ID: " + id));
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

        if(pessoa.departamento() != null) {
           Departamento departamento = this.departamentoService.encontrarDepartamentoPorNome(pessoa.departamento());

           if(departamento.getId() == null) {
               throw new DepartamentoInvalido("Departamento não encontrado");   
           }
        }

        Pessoa pessoaAtualiziada = this.pessoaRepository.save(pessoaObjeto);

        return pessoaAtualiziada;

    }


    public Pessoa atribuirTarefa(Pessoa pessoa, Tarefa tarefa) {
        pessoa.adicionarTarefa(tarefa);
        this.pessoaRepository.save(pessoa);
        return pessoa;
    }


    public List<Pessoa> encontrarPessoaPorParametros(String nome, String departamento) {
        List<Pessoa> pessoaEncontrada = this.pessoaRepository.encontrarPessoaPorParametros(nome, departamento);
        return pessoaEncontrada;
    }


    public MediaResponsavel mediaResponsavel(TarefaPorPeriodoDTO tarefaPorPeriodoDTO) throws ParseException {

        Pessoa pessoa = this.encontrarPessoa(tarefaPorPeriodoDTO.id());

        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date dataInicio = (Date) formato.parse(tarefaPorPeriodoDTO.inicio());
        Date dataFim = (Date) formato.parse(tarefaPorPeriodoDTO.fim());

        List<Tarefa> tarefas = this.tarefaRepository.encontrarTarefasDeResponsavelPorPeriodo(tarefaPorPeriodoDTO.id(), dataInicio, dataFim);

        long total = 0;
        long media = 0;

        if(tarefas.size() >= 1) {
            total = 0;
            for (Tarefa elemento : tarefas) {
                total += elemento.getTempoFinalizado();
            }
            media = total / tarefas.size();
        }

            MediaResponsavel mediaResponsavel = new MediaResponsavel(media, total, pessoa.getNome(), pessoa.getEmail());
            return mediaResponsavel;




    }


    public Pessoa adicionarTempoGasto(Pessoa pessoa, long tempo) {
        pessoa.setTotalDeHoras(pessoa.getTotalDeHoras() + tempo);
        return this.pessoaRepository.save(pessoa);
    }






}
