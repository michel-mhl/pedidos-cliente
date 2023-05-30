package com.eicon.pedidoscliente.services;

import com.eicon.pedidoscliente.domain.Pedido;
import com.eicon.pedidoscliente.domain.Produto;
import com.eicon.pedidoscliente.repositories.PedidoRepository;
import jakarta.transaction.Transactional;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PedidoService {
    @Autowired
    private PedidoRepository repo;

    @Transactional
    public Pedido findById(Long id) {
        Pedido pedido = repo.findById(id).get();
        deductNoteValue(pedido, id);
        return pedido;
    }

    @Transactional
    public List<Pedido> findAll() {
        List<Pedido> list = repo.findAll();

        for (Pedido pedido : list) {
            Hibernate.initialize(pedido.getProdutos());
        }
        return list;
    }


    public void deductNoteValue(Pedido pedido, Long id) {
//metodo para calcular valor total da nota e disponibilizar desconto por quantidade de produtos.
        Double valor = 0.00;
        Double valorDesconto = 0.00;
        for (Produto produto : pedido.getProdutos()) {
            int cont = 0;
            valor += produto.getPreco() * produto.getQuantidade();
            if (pedido.getProdutos().get(cont).getQuantidade() >= 10) {
                valorDesconto = valor - (valor * 0.10);
                pedido.setValorComDesconto(valorDesconto);
            } else if (pedido.getProdutos().get(cont).getQuantidade() >= 5) {
                valorDesconto = valor - (valor * 0.05);
                pedido.setValorComDesconto(valorDesconto);
            }
        }
        pedido.setValorNota(valor);
    }


/*    public List<Pedido> searchByDate(LocalDateTime data) {
        if (data != null) {
            return repo.findByDataPedido(data);
        } else {
            return repo.findAll();
        }
    }*/

    public void setRepo(PedidoRepository repo) {
        this.repo = repo;
    }


}
