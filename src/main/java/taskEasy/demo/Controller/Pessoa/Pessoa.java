package taskEasy.demo.Controller.Pessoa;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/pessoa")
public class Pessoa {

    @GetMapping()
    public String getPessoa() {
        return "Testando 2";
    }

}
