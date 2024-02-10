package taskEasy.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import taskEasy.demo.exceptions.UsuarioInexistenteException;
import taskEasy.demo.models.ErrorResponse;

@RestControllerAdvice
public class ApplicationControllerAdvice extends ResponseEntityExceptionHandler {



    @ExceptionHandler({ UsuarioInexistenteException.class })
    public ResponseEntity<ErrorResponse> handleUsuarioInexistenteException(
            UsuarioInexistenteException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage()));
    }




}