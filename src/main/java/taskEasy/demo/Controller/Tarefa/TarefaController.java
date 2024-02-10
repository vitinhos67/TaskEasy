package taskEasy.demo.Controller.Tarefa;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import taskEasy.demo.dto.Tarefa.CriarTarefaDTO;
import taskEasy.demo.models.entity.STATUS_TAREFA;
import taskEasy.demo.models.entity.Tarefa;
import taskEasy.demo.services.Tarefa.TarefaService;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/api/tarefa")
public class TarefaController {

    @Autowired
    TarefaService tarefaService;


    @GetMapping
    public List<Tarefa> todasTarefas() {
        return this.tarefaService.todasTarefas();
    }

    @PostMapping
    public Tarefa criarTarefa(@RequestBody CriarTarefaDTO criarTarefa) throws ParseException {
        return this.tarefaService.criarTarefa(criarTarefa);
    }


    @PostMapping(path = "/atualizar")
    public Tarefa atualizarStatusTarefa(@RequestParam int id, @RequestParam String status) throws Exception {
        return  this.tarefaService.atualizarStatusTarefa(id, status);

    }

    @PutMapping("/alocar")
    public Tarefa alocarTarefa(@RequestParam int id, @RequestParam int responsavel) {
            return this.tarefaService.alocarTarefa(id, responsavel);
    }



}
