package taskEasy.demo.models.entity;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String email;
    private Date momento;
    private Integer ativo;


    private String departamento;

    @ManyToMany
    @JoinTable(
            name = "pessoa_tarefa",
            joinColumns = @JoinColumn(name = "pessoa_id"),
            inverseJoinColumns = @JoinColumn(name = "tarefa_id")
    )
    @JsonIgnore
    private List<Tarefa> tarefas;


    public Pessoa() {}


    public Pessoa(String nome, String email, int ativo, String departamento) {
        this.nome = nome;
        this.email = email;
        this.ativo = ativo;
        momento = new Date();
        this.departamento = departamento;
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

    public List<Tarefa> getTarefas() {
        return tarefas;
    }

    public Tarefa adicionarTarefa(Tarefa tarefa) {
            this.tarefas.add(tarefa);
            return tarefa;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getDepartamentos() {
        return departamento;
    }
}
