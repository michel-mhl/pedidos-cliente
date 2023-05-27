package com.eicon.pedidoscliente.resources;

import com.eicon.pedidoscliente.domain.Produto;
import com.eicon.pedidoscliente.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/produto")
public class ProdutoResource {
    @Autowired
    private ProdutoService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Produto> findById(@PathVariable Long id) {
        Produto obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping()
    public ResponseEntity<List<Produto>> findAll() {
        List<Produto> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }
}
