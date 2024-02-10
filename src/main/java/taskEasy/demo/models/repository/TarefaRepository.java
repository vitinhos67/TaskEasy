package taskEasy.demo.models.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import taskEasy.demo.models.entity.Tarefa;

import java.util.List;

@Repository
public interface TarefaRepository extends PagingAndSortingRepository<Tarefa, Integer>, CrudRepository<Tarefa, Integer> {

    List<Tarefa> findAll();

}
