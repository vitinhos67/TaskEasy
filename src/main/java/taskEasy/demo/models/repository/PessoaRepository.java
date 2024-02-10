package taskEasy.demo.models.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import taskEasy.demo.models.entity.Pessoa;

import java.util.List;

@Repository
public interface PessoaRepository extends PagingAndSortingRepository<Pessoa, Integer>, CrudRepository<Pessoa, Integer> {
    Pessoa findByEmail(String email);
    List<Pessoa> findAll();
}
