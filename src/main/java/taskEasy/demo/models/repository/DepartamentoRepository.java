package taskEasy.demo.models.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import taskEasy.demo.models.entity.Departamento;

@Repository
public interface DepartamentoRepository extends PagingAndSortingRepository<Departamento, Integer>, CrudRepository<Departamento, Integer> {
}
