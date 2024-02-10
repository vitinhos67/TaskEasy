package taskEasy.demo.models.entity;


import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
public class Departamento {


    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private Date momento;

    public Departamento(){}

    public Departamento(String nome) {
        this.nome = nome;
        this.momento = new Date();
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setMomento(Date momento) {
        this.momento = momento;
    }

    public Date getMomento() {
        return momento;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }


}
