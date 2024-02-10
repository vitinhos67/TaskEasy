package taskEasy.demo.services.Tarefa;


import com.sun.jdi.InvalidTypeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import taskEasy.demo.dto.Tarefa.CriarTarefaDTO;
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

        Pessoa pessoa = this.pessoaService.encontrarPessoa(criarTarefa.id());

        Departamento encontrarDepartamento = this.departamentoService.encontrarDepartamentoPorNome(criarTarefa.departamento());

        Tarefa tarefa_criada = new Tarefa(criarTarefa.nome(), criarTarefa.descricao(), STATUS_TAREFA.CRIADA, dataFormatada, pessoa, encontrarDepartamento);
        return this.tarefaRepository.save(tarefa_criada);

    }


    public Tarefa atualizarTarefa(int id, String status) throws Exception {

        Optional<Tarefa> tarefa = Optional.ofNullable(this.tarefaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Tarefa não encontrada com o ID: " + id)));

        STATUS_TAREFA escolhido = null;
        for (STATUS_TAREFA c : STATUS_TAREFA.values()) {
            if (c.name().equals(status)) {
                escolhido = c;
                break;
            }
        }
        if (escolhido != null) {
            tarefa.get().setStatus(escolhido);
        } else {
            throw new StatusNaoEncontrado("Status não aceito.");
        }

        return this.tarefaRepository.save(tarefa.get());
    }


}
