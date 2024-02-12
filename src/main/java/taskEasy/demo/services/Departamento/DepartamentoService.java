package taskEasy.demo.services.Departamento;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import taskEasy.demo.dto.Departamento.CriarDepartamento;
import taskEasy.demo.exceptions.DepartamentoInvalido;
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

        Departamento jaExiste = this.encontrarDepartamentoPorNome(criarDepartamento.nome());

        if(jaExiste != null) {
            throw new DepartamentoInvalido("Departamento ja criado");
        }

        return this.departamentoRepository.save(departamento);
    }


    public Departamento encontrarDepartamentoPorNome(String nome) {
        return this.departamentoRepository.findByNome(nome.toLowerCase());
    }

    public List<Departamento> todosDepartamentos() {
        return (List<Departamento>) this.departamentoRepository.findAll();
    }


    public Departamento adicionarQtdDeTarefa(Departamento departamento) {
        int atualizarQtd = departamento.getQuantidadeDeTarefas() + 1;
        departamento.setQuantidadeDeTarefas(atualizarQtd);
        Departamento departamentoAtualizado = this.departamentoRepository.save(departamento);
        return departamentoAtualizado;

    }

    public Departamento adicionarQtdPessoa(Departamento departamento) {
        int atualizarQtd = departamento.getQuantidadeAtual() + 1;
        departamento.setQuantidadeAtual(atualizarQtd);
        Departamento departamentoAtualizado = this.departamentoRepository.save(departamento);
        return departamentoAtualizado;

    }

    public Departamento removerPessoaDepartamento(Departamento departamento) {
        int atualizarQtd = departamento.getQuantidadeAtual() - 1;
        departamento.setQuantidadeAtual(atualizarQtd);
        Departamento departamentoAtualizado = this.departamentoRepository.save(departamento);
        return departamentoAtualizado;

    }






}
