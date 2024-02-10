package taskEasy.demo.models;

import org.springframework.http.HttpStatus;

import javax.xml.crypto.Data;
import java.util.Date;

public class ErrorResponse {
    HttpStatus status;
    String mensagem;
    DataResponse data;

    public ErrorResponse() {

    }

    public ErrorResponse(HttpStatus status, String mensagem){
        this.status = status;
        this.mensagem = mensagem;
        this.data = new DataResponse(false, new Date());
    }


}
