package br.com.webservice.config;

import br.com.webservice.model.Funcionario;
import br.com.webservice.repository.FuncionarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;


@Configuration
public class TestConfig implements CommandLineRunner {
    @Autowired
    private FuncionarioRepositorio repositorio;

    @Override
    public void run(String... args) throws Exception {
        Funcionario func1 = new Funcionario(null,"Sonia Abrel","soniaabrel@gmail.com");
        Funcionario func2 = new Funcionario(null, "Melissa Costa", "melissa@gmail.com");
        Funcionario func3 = new Funcionario(null, "Bernardo Filho", "bernardo@gmail.com");
        Funcionario func4 = new Funcionario(null, "Giulia Salgado", "giuliasalgado@gmail.com");
        Funcionario func5 = new Funcionario(null, "Silvana Cordeiro", "silvana@gmail.com");

       repositorio.saveAndFlush(func1);
       repositorio.saveAndFlush(func2);
       repositorio.saveAndFlush(func3);
       repositorio.saveAndFlush(func4);
       repositorio.saveAndFlush(func5);

       System.out.println(repositorio.findAll());
    }

}
