package taskEasy.demo.models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import taskEasy.demo.models.entity.Pessoa;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;


public class DataResponse<T> {

    private boolean sucesso;
    private Date momento;
    private T data;


    public DataResponse(boolean sucesso, T response) {
        this.sucesso = sucesso;
        this.momento = new Date();
        this.data = response;
    }

    public DataResponse set(boolean sucesso, T response) {
        this.sucesso = sucesso;
        this.momento = new Date();
        this.data = response;
        return this;
    }

    public boolean isSucesso() {
        return sucesso;
    }

    public void setMomento(Date momento) {
        this.momento = momento;
    }

    public void setSucesso(boolean sucesso) {
        this.sucesso = sucesso;
    }

    public Date getMomento() {
        return momento;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
