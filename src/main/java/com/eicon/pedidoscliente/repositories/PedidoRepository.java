package com.eicon.pedidoscliente.repositories;

import com.eicon.pedidoscliente.domain.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    List<Pedido> findByDataPedido(LocalDateTime data);
}
