package com.eicon.pedidoscliente.resources;

import com.eicon.pedidoscliente.domain.Pedido;
import com.eicon.pedidoscliente.domain.Produto;
import com.eicon.pedidoscliente.services.PedidoService;
import com.eicon.pedidoscliente.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(value = "/pedido")
public class PedidoResource {
    @Autowired
    private PedidoService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Pedido> findById(@PathVariable Long id) {
        Pedido obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping()
    public ResponseEntity<List<Pedido>> findAll() {
        List<Pedido> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }
    @GetMapping("/data/{data}")
    public List<Pedido> getPedidosByData(@PathVariable LocalDateTime data) {
        return service.searchByDate(data);
    }
}
