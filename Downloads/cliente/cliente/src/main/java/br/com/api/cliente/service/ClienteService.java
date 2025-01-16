package br.com.api.cliente.service;

import br.com.api.cliente.entity.Cliente;
import br.com.api.cliente.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente salvar(Cliente cliente){
        return clienteRepository.save(cliente);
    }

    public List<Cliente> listar(){
        return clienteRepository.findAll();
    }

    public void deletarPorId(Long id){
        clienteRepository.deleteById(id);
    }

    public List<Cliente> atualizar(Cliente cliente) {
        clienteRepository.save(cliente);
        return clienteRepository.findAll();
    }

    public Optional<Cliente> buscarPorId(Long id) {
        return clienteRepository.findById(id);
    }
}
