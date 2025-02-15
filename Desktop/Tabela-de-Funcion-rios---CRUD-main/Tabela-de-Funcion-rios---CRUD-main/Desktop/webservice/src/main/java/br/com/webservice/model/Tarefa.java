package br.com.webservice.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.Instant;

@Entity
@Table(name = "tabela_tarefas")
public class Tarefa implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Instant instante;
    @ManyToOne
    @JoinColumn(name = "funcionario_id")
    private Funcionario funcionario;

    public Tarefa(Long id, Instant instante, Funcionario funcionario) {
        this.id = id;
    }

    public Tarefa() {

    }
}
