package com.eicon.pedidoscliente.services;

import com.eicon.pedidoscliente.domain.Produto;
import com.eicon.pedidoscliente.repositories.ProdutoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository repo;

    @Transactional
    public Produto findById(Long id) {
        Produto produto = repo.findById(id).get();
        return produto;
    }

    @Transactional
    public List<Produto> findAll() {
        List<Produto> list = repo.findAll();
        return list;
    }
}
