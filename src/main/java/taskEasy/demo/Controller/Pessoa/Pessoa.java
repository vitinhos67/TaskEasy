package taskEasy.demo.Controller.Pessoa;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller()
public class Pessoa {

    @GetMapping("/api/pessoa")
    public String getPessoa() {
        return "Testando";
    }

}
