package taskEasy.demo.dto.Tarefa;

import taskEasy.demo.models.entity.Departamento;
import taskEasy.demo.models.entity.STATUS_TAREFA;

import java.util.Date;

public record CriarTarefaDTO(String nome, String descricao, String prazo, Integer id, String departamento) {
}
