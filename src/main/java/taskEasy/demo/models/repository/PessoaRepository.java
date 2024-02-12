package taskEasy.demo.models.repository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import taskEasy.demo.dto.Pessoa.AtualizarPessoaDTO;
import taskEasy.demo.dto.Pessoa.EncontrarPessoaPorParametroDTO;
import taskEasy.demo.models.entity.Pessoa;

import java.util.List;

@Repository
public interface PessoaRepository extends PagingAndSortingRepository<Pessoa, Integer>, CrudRepository<Pessoa, Integer> {
    Pessoa findByEmail(String email);
    public List<Pessoa> findAll();

    @Query("SELECT p FROM Pessoa p where p.nome IS NOT NULL AND p.nome = :nome AND p.departamento IS NOT NULL AND p.departamento = :departamento")
    public List<Pessoa> encontrarPessoaPorParametros(String nome, String departamento);
}
