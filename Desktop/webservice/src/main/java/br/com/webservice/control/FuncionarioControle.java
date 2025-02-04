package br.com.webservice.control;

import br.com.webservice.model.Funcionario;

import br.com.webservice.service.FuncionarioServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping ("/funcionarios")
public class FuncionarioControle{

    @Autowired
    private FuncionarioServico servico;

    @GetMapping()
    public ResponseEntity<List<Funcionario>> getFuncionarios(){
        List<Funcionario> listaFuncionarios = servico.listar();
        return ResponseEntity.ok().body(listaFuncionarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Funcionario> getFuncionario(@PathVariable Long id){
        return ResponseEntity.ok().body(servico.encontrarPorId(id));
    }
}
