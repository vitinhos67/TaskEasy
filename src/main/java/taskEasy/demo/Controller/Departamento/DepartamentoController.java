package taskEasy.demo.Controller.Departamento;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import taskEasy.demo.dto.Departamento.CriarDepartamento;
import taskEasy.demo.models.entity.Departamento;
import taskEasy.demo.services.Departamento.DepartamentoService;

import java.awt.*;
import java.util.List;

@RestController
@RequestMapping("/api/departamento")
public class DepartamentoController {

    @Autowired
    DepartamentoService departamentoService;


    @GetMapping(
            "/todos"
    )
    public List<Departamento> todosDepartamentos(){
        return this.departamentoService.todosDepartamentos();
    }

    @PostMapping
    public Departamento criarDepartamento(@RequestBody CriarDepartamento criarDepartamento) {
        return this.departamentoService.criarDepartamento(criarDepartamento);
    }

    @GetMapping
    public Departamento encontrarDepartamento(@RequestParam String nome) {
        return this.departamentoService.encontrarDepartamentoPorNome(nome);
    }

}
