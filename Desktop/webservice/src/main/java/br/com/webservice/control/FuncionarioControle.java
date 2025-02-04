package br.com.webservice.control;

import br.com.webservice.model.Funcionario;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("/funcionarios")
public class FuncionarioControle{

    @GetMapping()
    public ResponseEntity<Funcionario> getFuncionarios(){
        Funcionario func1 = new Funcionario(null,"Dolores Cabral","dolorescabral@gmail.com");
        return ResponseEntity.ok().body(func1);
    }
}
