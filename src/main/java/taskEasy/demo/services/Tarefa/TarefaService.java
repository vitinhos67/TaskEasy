package taskEasy.demo.services.Tarefa;


import com.sun.jdi.InvalidTypeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import taskEasy.demo.dto.Tarefa.CriarTarefaDTO;
import taskEasy.demo.exceptions.DepartamentoInvalido;
import taskEasy.demo.exceptions.StatusNaoEncontrado;
import taskEasy.demo.models.entity.Departamento;
import taskEasy.demo.models.entity.Pessoa;
import taskEasy.demo.models.entity.STATUS_TAREFA;
import taskEasy.demo.models.entity.Tarefa;
import taskEasy.demo.models.repository.TarefaRepository;
import taskEasy.demo.services.Departamento.DepartamentoService;
import taskEasy.demo.services.Pessoa.PessoaService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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


        // LOGICA PARA ATRIBUIR RESPONSAVEL COM MENAS TAREFAS

        Pessoa pessoa = this.pessoaService.encontrarPessoa(criarTarefa.id());

        Departamento encontrarDepartamento = this.departamentoService.encontrarDepartamentoPorNome(criarTarefa.departamento());

        if(encontrarDepartamento == null) {
            throw new DepartamentoInvalido("Departamento não encontrado.");
        }
        String departamentoDaPessoa = pessoa.getDepartamentos();
        if(departamentoDaPessoa != null) {

            if (!departamentoDaPessoa.equals(encontrarDepartamento.getNome())) {
                throw new DepartamentoInvalido("Pessoa não pertence ao departamento.");
            }

        } else {
            throw new DepartamentoInvalido("A pessoa não contem um departamento.");
        }




        Tarefa tarefa_criada = new Tarefa(criarTarefa.nome(), criarTarefa.descricao(), STATUS_TAREFA.CRIADA, dataFormatada, pessoa, encontrarDepartamento);
        Tarefa tarefa_salva = this.tarefaRepository.save(tarefa_criada);
        atribuirTarefa(pessoa, tarefa_salva);
        return tarefa_salva;
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

        //ADICIONAR FUNCAO PARA VALIDAR SE O PARAMTRO FOI PASSADO, CASO NAO, ATRIBUIR PARA O QUE MENOS TEM TAREFA

        Pessoa pessoa = this.pessoaService.encontrarPessoa(responsavelId);

        String departamentoDaPessoa = pessoa.getDepartamentos();

        if(departamentoDaPessoa != null) {
            if(!departamentoDaPessoa.equals(encontrarTarefa.getDepartamentoResponsavel())) {
                throw new DepartamentoInvalido("Pessoa não pertence ao departamento");
            }
        } else {
            throw new DepartamentoInvalido("Pessoa não contem um departamento");
        }


        encontrarTarefa.setResponsavel(pessoa);

        return this.tarefaRepository.save(encontrarTarefa);

    }


}
