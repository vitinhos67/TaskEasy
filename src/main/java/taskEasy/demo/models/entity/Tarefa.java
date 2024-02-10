package taskEasy.demo.models.entity;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "tarefa")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String descricao;
    private Date prazo;

    private STATUS_TAREFA status;
    private Date momento;

    @ManyToOne
    @JoinColumn(name = "id_pessoa_responsavel")
    private Pessoa responsavel;


    @ManyToOne
    @JoinColumn(name = "departamento_responsavel_id")
    private Departamento departamentoResponsavel;

    public Tarefa() {}

    public Tarefa(String nome, String descricao, STATUS_TAREFA status, Date prazo, Pessoa pessoa, Departamento departamento) {
        this.nome = nome;
        this.descricao = descricao;
        this.status = status;
        this.prazo = prazo;
        this.responsavel = pessoa;
        this.departamentoResponsavel = departamento;
        this.momento = new Date();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getPrazo() {
        return prazo;
    }

    public void setPrazo(Date prazo) {
        this.prazo = prazo;
    }

    public Date getMomento() {
        return momento;
    }

    public void setMomento(Date momento) {
        this.momento = momento;
    }

    public STATUS_TAREFA getStatus() {
        return status;
    }

    public void setStatus(STATUS_TAREFA status) {
        this.status = status;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public Pessoa getResponsavel() {
        return this.responsavel;
    }

    public void setResponsavel(Pessoa pessoa) {
        this.responsavel = pessoa;
    }

    public Departamento getDepartamentoResponsavel() {
        return departamentoResponsavel;
    }

    public void setDepartamentoResponsavel(Departamento departamentoResponsavel) {
        this.departamentoResponsavel = departamentoResponsavel;
    }
}
