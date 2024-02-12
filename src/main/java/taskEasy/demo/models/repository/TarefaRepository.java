package taskEasy.demo.models.repository;

import io.lettuce.core.dynamic.annotation.Param;
import jakarta.annotation.Resource;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import taskEasy.demo.models.entity.Tarefa;

import java.util.Date;
import java.util.List;

@Repository
public interface TarefaRepository extends PagingAndSortingRepository<Tarefa, Integer>, CrudRepository<Tarefa, Integer> {

    public List<Tarefa> findAll();

    @Query(nativeQuery = true,
    value = "SELECT * FROM public.tarefa t WHERE t.id_pessoa_responsavel IS NULL ORDER BY t.prazo ASC LIMIT :limite")
    List<Tarefa> encontrarTarefaPendentes(@Param(value = "limite") int limite);

    @Query(
            nativeQuery = true,
            value = "SELECT * FROM public.tarefa t " +
                    "INNER JOIN public.pessoa_tarefa pt ON pt.pessoa_id  = :id " +
                    "WHERE t.id_pessoa_responsavel = :id"
    )
    List<Tarefa> encontrarTarefasDeResponsavel(int id);

    @Query(
            nativeQuery = true,
            value = "SELECT * FROM public.tarefa t " +
                    "INNER JOIN public.pessoa_tarefa pt ON pt.pessoa_id  = :id " +
                    "WHERE t.id_pessoa_responsavel = :id" +
                    "WHERE momento BETWEEN :inicio AND :fim"
    )
    List<Tarefa> encontrarTarefasDeResponsavelPorPeriodo(int id, Date inicio, Date fim);



}


