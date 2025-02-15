package br.com.webservice.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "banco_funcionarios")
public class Funcionario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String nome;
    @Column
    private String email;
    @Column
    @OneToMany(mappedBy = "funcionario")
    private List<Tarefa> tarefas;

    public Funcionario() {
    }

    public Funcionario(Long id, String nome, String email) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        tarefas = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
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
}
