package taskEasy.demo.Controller.Tarefa;


import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import taskEasy.demo.dto.Tarefa.CriarTarefaDTO;
import taskEasy.demo.dto.Tarefa.TarefaPorPeriodoDTO;
import taskEasy.demo.models.DataResponse;
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


    @GetMapping(path = "/pendentes")
    public ResponseEntity<DataResponse<List<Tarefa>>> tarefaPendentes(@RequestParam(required = true) int limite) {
        List<Tarefa> tarefas = this.tarefaService.tarefasPendentes(limite);
        return ResponseEntity.status(HttpStatus.OK).body(new DataResponse(true, tarefas));
    }


    @GetMapping("/responsavel/{id}")
    public ResponseEntity<DataResponse<List<Tarefa>>> tarefasPorResponsavel(@PathVariable int id) {
        List<Tarefa> tarefas = this.tarefaService.encontrarTarefasDeResponsavel(id);
        return ResponseEntity.status(HttpStatus.OK).body(new DataResponse<>(true, tarefas));
    }


    @PostMapping("/responsavel/media")
    public ResponseEntity<DataResponse<List<Tarefa>>> tarefasDoResponsavelPorPeriodo(@RequestBody TarefaPorPeriodoDTO tarefaPorPeriodoDTO) throws ParseException {

        List<Tarefa> tarefas = this.tarefaService.tarefasPorResponsavelePeriodo(tarefaPorPeriodoDTO);

        return ResponseEntity.status(HttpStatus.OK).body(new DataResponse<>(true, tarefas));
    }





}
