package taskEasy.demo.Controller.Departamento;


import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import taskEasy.demo.dto.Departamento.CriarDepartamento;
import taskEasy.demo.models.DataResponse;
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
    public ResponseEntity<DataResponse<List<Departamento>>> todosDepartamentos(){
        List<Departamento> departamentos = this.departamentoService.todosDepartamentos();
        return ResponseEntity.status(HttpStatus.OK).body(new DataResponse<List<Departamento>>(true, departamentos));
    }

    @PostMapping
    public ResponseEntity<DataResponse<Departamento>> criarDepartamento(@RequestBody CriarDepartamento criarDepartamento) {
        Departamento departamento = this.departamentoService.criarDepartamento(criarDepartamento);
        return ResponseEntity.status(HttpStatus.OK).body(new DataResponse<Departamento>(true, departamento));
    }

    @GetMapping
    public ResponseEntity<DataResponse<Departamento>> encontrarDepartamento(@RequestParam String nome) {
        Departamento departamento = this.departamentoService.encontrarDepartamentoPorNome(nome);
        return ResponseEntity.status(HttpStatus.OK).body(new DataResponse<Departamento>(true, departamento));
    }

}
