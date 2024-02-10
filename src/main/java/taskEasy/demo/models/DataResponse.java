package taskEasy.demo.models;

import javax.xml.crypto.Data;
import java.util.Date;

public class DataResponse {

    boolean sucesso;
    Date momento;


    public DataResponse(boolean sucesso, Date momento) {
        this.sucesso = sucesso;
        this.momento = momento;
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
}
