package com.eicon.pedidoscliente.services;

import com.eicon.pedidoscliente.domain.Cliente;
import com.eicon.pedidoscliente.repositories.ClienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository repo;

    @Transactional
    public Cliente findById(Long id) {
        Cliente cliente = repo.findById(id).get();
        return cliente;
    }

    @Transactional
    public List<Cliente> findAll() {
        List<Cliente> list = repo.findAll();
        return list;
    }
}
