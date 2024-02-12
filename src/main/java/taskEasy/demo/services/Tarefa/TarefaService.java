package taskEasy.demo.services.Tarefa;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.sun.jdi.InvalidTypeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import taskEasy.demo.dto.Tarefa.CriarTarefaDTO;
import taskEasy.demo.dto.Tarefa.TarefaPorPeriodoDTO;
import taskEasy.demo.exceptions.DepartamentoInvalido;
import taskEasy.demo.exceptions.StatusNaoEncontrado;
import taskEasy.demo.exceptions.TarefaException;
import taskEasy.demo.models.entity.Departamento;
import taskEasy.demo.models.entity.Pessoa;
import taskEasy.demo.models.entity.STATUS_TAREFA;
import taskEasy.demo.models.entity.Tarefa;
import taskEasy.demo.models.repository.TarefaRepository;
import taskEasy.demo.services.Departamento.DepartamentoService;
import taskEasy.demo.services.Pessoa.PessoaService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TarefaService {

    @Autowired
    TarefaRepository tarefaRepository;

    @Autowired
    PessoaService pessoaService;

    @Autowired
    DepartamentoService departamentoService;

    public List<Tarefa> todasTarefas() {
        return this.tarefaRepository.findAll();
    }

    public Tarefa criarTarefa(CriarTarefaDTO criarTarefa) throws ParseException {

        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date dataFormatada = (Date) formato.parse(criarTarefa.prazo());

        Pessoa pessoa = null;
        String departamentoDaPessoa = null;

        Departamento encontrarDepartamento = this.departamentoService.encontrarDepartamentoPorNome(criarTarefa.departamento());

        if (encontrarDepartamento == null) {
            throw new DepartamentoInvalido("Departamento não encontrado.");
        }

        if(criarTarefa.id() != null) {
            pessoa = this.pessoaService.encontrarPessoa(criarTarefa.id());
            departamentoDaPessoa = pessoa.getDepartamentos();
        }


        if (departamentoDaPessoa != null) {
            if (!departamentoDaPessoa.equals(encontrarDepartamento.getNome())) {
                throw new DepartamentoInvalido("Pessoa não pertence ao departamento.");
            }
        } else {
            throw new DepartamentoInvalido("Pessoa não esta em um departamento");
        }

        Tarefa tarefa_criada = new Tarefa(criarTarefa.nome(), criarTarefa.descricao(), STATUS_TAREFA.CRIADA, dataFormatada, pessoa, encontrarDepartamento);
        Tarefa tarefa_salva = this.tarefaRepository.save(tarefa_criada);
        this.departamentoService.adicionarQtdDeTarefa(encontrarDepartamento);

        if(pessoa != null) {
            atribuirTarefa(pessoa, tarefa_salva);
        }


        return tarefa_criada;

    }

    public Tarefa atualizarStatusTarefa(int id, String status) throws Exception {

        Tarefa tarefa = this.encontrarTarefaId(id);

        STATUS_TAREFA escolhido = null;
        for (STATUS_TAREFA c : STATUS_TAREFA.values()) {
            if (c.name().equals(status)) {
                escolhido = c;
                break;
            }
        }
        if (escolhido != null) {

            if(escolhido == STATUS_TAREFA.REALIZADA) {

                if(tarefa.getTempoFinalizado() < 1) {
                    Date horarioDeCriacao = tarefa.getMomento();

                    LocalDateTime inicio = LocalDateTime.ofInstant(horarioDeCriacao.toInstant(), ZoneId.systemDefault());
                    LocalDateTime fim = LocalDateTime.now();

                    Duration duracao = Duration.between(inicio, fim);
                    tarefa.setTempoFinalizado(duracao.toHours());

                    Pessoa pessoa = this.pessoaService.encontrarPessoa(tarefa.getResponsavel().getId());

                    this.pessoaService.adicionarTempoGasto(pessoa, duracao.toHours());
                } else {
                    throw new TarefaException("Tarefa já finalizada");
                }
            }

            tarefa.setStatus(escolhido);
        } else {
            throw new StatusNaoEncontrado("Status não aceito.");
        }

        return this.tarefaRepository.save(tarefa);
    }

    public void atribuirTarefa(Pessoa pessoa, Tarefa tarefa) {
        this.pessoaService.atribuirTarefa(pessoa, tarefa);
    }

    public Tarefa encontrarTarefaId(int id) {
        return  this.tarefaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Tarefa não encontrada com o ID: " + id));
    }


    public Tarefa alocarTarefa(int id, int responsavelId) {

        Tarefa encontrarTarefa = this.encontrarTarefaId(id);
        Pessoa pessoa = this.pessoaService.encontrarPessoa(responsavelId);

        String departamentoDaPessoa = pessoa.getDepartamentos();
        String departamentoDaTarefa = encontrarTarefa.getDepartamento().getNome();


        if(departamentoDaPessoa != null) {

            if(!departamentoDaPessoa.equals(departamentoDaTarefa)) {
                throw new DepartamentoInvalido("Pessoa não pertence ao departamento");
            }

        } else {
            throw new DepartamentoInvalido("Pessoa não contem um departamento");
        }


        encontrarTarefa.setResponsavel(pessoa);

        return this.tarefaRepository.save(encontrarTarefa);

    }

    public List<Tarefa> tarefasPendentes(int limite) {
        return this.tarefaRepository.encontrarTarefaPendentes(limite);
    }


    public List<Tarefa> encontrarTarefasDeResponsavel(int id) {
        return this.tarefaRepository.encontrarTarefasDeResponsavel(id);
    }



    public List<Tarefa> tarefasPorResponsavelePeriodo(TarefaPorPeriodoDTO tarefaPorPeriodoDTO) throws ParseException {

        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date dataInicio = (Date) formato.parse(tarefaPorPeriodoDTO.inicio());
        Date dataFim = (Date) formato.parse(tarefaPorPeriodoDTO.fim());

        List<Tarefa> tarefa = this.tarefaRepository.encontrarTarefasDeResponsavelPorPeriodo(tarefaPorPeriodoDTO.id(), dataInicio, dataFim);
        return tarefa;
    }



}
