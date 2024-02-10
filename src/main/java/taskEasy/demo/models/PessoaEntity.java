package taskEasy.demo.models;


import jakarta.annotation.Nullable;
import jakarta.persistence.*;

@Entity
public class PessoaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;
    private String email;

    @OneToMany()
    @Nullable()
    private DepartamentoEntity departamento;



}
