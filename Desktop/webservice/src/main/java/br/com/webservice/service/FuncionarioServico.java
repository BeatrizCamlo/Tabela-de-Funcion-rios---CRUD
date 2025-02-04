package br.com.webservice.service;

import br.com.webservice.model.Funcionario;
import br.com.webservice.repository.FuncionarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioServico {

    @Autowired
    private FuncionarioRepositorio repositorio;

    public List<Funcionario> listar(){
        return repositorio.findAll();
    }

    public Funcionario encontrarPorId(Long id){
        Optional<Funcionario> funcionarioEncontrado = repositorio.findById(id);
        return funcionarioEncontrado.get();
    }

}
