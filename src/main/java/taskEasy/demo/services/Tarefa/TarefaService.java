package taskEasy.demo.services.Tarefa;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import taskEasy.demo.dto.Tarefa.CriarTarefaDTO;
import taskEasy.demo.models.entity.STATUS_TAREFA;
import taskEasy.demo.models.entity.Tarefa;
import taskEasy.demo.models.repository.TarefaRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class TarefaService {

    @Autowired
    TarefaRepository tarefaRepository;

    public List<Tarefa> todasTarefas() {
        return this.tarefaRepository.findAll();
    }

    public Tarefa criarTarefa(CriarTarefaDTO criarTarefa) throws ParseException {

        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        Date dataFormatada = (Date) formato.parse(criarTarefa.prazo());

        Tarefa tarefa_criada = new Tarefa(criarTarefa.nome(), criarTarefa.descricao(), STATUS_TAREFA.CRIADA, dataFormatada);
        return this.tarefaRepository.save(tarefa_criada);

    }

}
