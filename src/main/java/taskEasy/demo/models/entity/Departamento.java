package taskEasy.demo.models.entity;


import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.util.Date;
import java.util.List;

@Entity
public class Departamento {


    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String nome;
    private Date momento;

    @Column(name = "quantidade_de_pessoas")
    private int quantidadeAtual;
    @Column(name = "quantidade_de_tarefas")
    private int quantidadeDeTarefas;

    public Departamento(){}

    public Departamento(String nome) {
        this.nome = nome;
        this.quantidadeAtual = 0;
        this.quantidadeDeTarefas = 0;
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

    public int getQuantidadeDeTarefas() {
        return quantidadeDeTarefas;
    }

    public void setQuantidadeAtual(int quantidadeAtual) {
        this.quantidadeAtual = quantidadeAtual;
    }

    public void setQuantidadeDeTarefas(int quantidadeDeTarefas) {
        this.quantidadeDeTarefas = quantidadeDeTarefas;
    }

    public int getQuantidadeAtual() {
        return quantidadeAtual;
    }


}
