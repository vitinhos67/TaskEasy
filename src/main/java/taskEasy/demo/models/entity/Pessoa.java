package taskEasy.demo.models.entity;


import jakarta.annotation.Nullable;
import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String email;
    private Date momento;


    private Integer ativo;

    public Pessoa() {}


    public Pessoa(String nome, String email, int ativo) {
        this.nome = nome;
        this.email = email;
        this.ativo = ativo;
        momento = new Date();

    }



    public Date getMomento() {
        return momento;
    }

    public void setMomento(Date momento) {
        this.momento = momento;
    }

    public int getAtivo() {
        return this.ativo;
    }

    public void setAtivo(int ativo) {
        this.ativo = ativo;
    }

    public String getNome() {
        return nome;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
