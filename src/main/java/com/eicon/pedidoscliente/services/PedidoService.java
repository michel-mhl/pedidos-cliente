package com.eicon.pedidoscliente.services;

import com.eicon.pedidoscliente.domain.Pedido;
import com.eicon.pedidoscliente.domain.Produto;
import com.eicon.pedidoscliente.repositories.PedidoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService {
    @Autowired
    private PedidoRepository repo;

    @Transactional
    public Pedido findById(Long id) {

        Pedido pedido = repo.findById(id).get();
        Double valor = 0.00;
        Double subtotal = 0.00;
        for (Produto produto : pedido.getProdutos()) {
            int cont = 0;
            valor += produto.getPreco() * produto.getQuantidade();
            if (pedido.getProdutos().get(cont).getQuantidade() >= 10) {
                subtotal = valor - (valor * 0.10);
                pedido.setDesconto(subtotal);
            } else if (pedido.getProdutos().get(cont).getQuantidade() >= 5) {
                subtotal = valor - (valor * 0.05);
                pedido.setDesconto(subtotal);
            }
        }
        pedido.setValorNota(valor);


        return pedido;
    }

      @Transactional
    public List<Pedido> findAll() {
        List<Pedido> list = repo.findAll();
        return list;
    }


}
