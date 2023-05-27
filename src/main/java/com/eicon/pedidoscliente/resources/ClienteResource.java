package com.eicon.pedidoscliente.resources;

import com.eicon.pedidoscliente.domain.Cliente;
import com.eicon.pedidoscliente.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/cliente")
public class ClienteResource {
    @Autowired
    private ClienteService clienteService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Cliente> findById(@PathVariable Long id) {
        Cliente obj = clienteService.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping()
    public ResponseEntity<List<Cliente>> findAll() {
        List<Cliente> list = clienteService.findAll();
        //   List<ClienteDTO> listDto = list.stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(list);
    }
}
