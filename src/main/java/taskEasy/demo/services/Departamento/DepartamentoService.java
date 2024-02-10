package taskEasy.demo.services.Departamento;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import taskEasy.demo.dto.Departamento.CriarDepartamento;
import taskEasy.demo.models.entity.Departamento;
import taskEasy.demo.models.repository.DepartamentoRepository;

import java.util.List;
import java.util.Locale;

@Service
public class DepartamentoService {

    @Autowired
    private DepartamentoRepository departamentoRepository;


    public Departamento criarDepartamento(CriarDepartamento criarDepartamento) {
        Departamento departamento = new Departamento(criarDepartamento.nome().toLowerCase());
        return this.departamentoRepository.save(departamento);
    }


    public Departamento encontrarDepartamentoPorNome(String nome) {
        return this.departamentoRepository.findByNome(nome.toLowerCase());
    }

    public List<Departamento> todosDepartamentos() {
        return (List<Departamento>) this.departamentoRepository.findAll();
    }


}
