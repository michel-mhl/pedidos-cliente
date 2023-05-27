package com.eicon.pedidoscliente.domain;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    private LocalDateTime dataPedido;

    @ManyToMany
    @JoinTable(
            name = "pedido_produto",
            joinColumns = @JoinColumn(name = "pedido_id"),
            inverseJoinColumns = @JoinColumn(name = "produto_id")
    )
    private List<Produto> produtos = new ArrayList<>();

    @PrePersist
    public void validarLimitePedidos() {
        if (cliente.getPedidos().size() >= 10) {
            throw new RuntimeException("Limite máximo de pedidos atingido para o cliente.");
        }
    }

    public Pedido(Long id, Cliente cliente, LocalDateTime dataPedido) {
        this.id = id;
        this.cliente = cliente;
        this.dataPedido = (dataPedido ==null? LocalDateTime.now(): dataPedido );
    }

    public Pedido() {
    }
}
