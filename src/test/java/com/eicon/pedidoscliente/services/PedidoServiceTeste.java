package com.eicon.pedidoscliente.services;

import com.eicon.pedidoscliente.domain.Pedido;
import com.eicon.pedidoscliente.domain.Produto;
import com.eicon.pedidoscliente.repositories.PedidoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class PedidoServiceTeste {

    @Mock
    private PedidoRepository repo;

    private PedidoService pedidoService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        pedidoService = new PedidoService();
        pedidoService.setRepo(repo);
    }

    @Test
    public void testFindById() {
        Long id = 1L;
        Pedido pedido = new Pedido();
        pedido.setId(id);

        when(repo.findById(id)).thenReturn(Optional.of(pedido));

        Pedido result = pedidoService.findById(id);

        assertNotNull(result);
        assertEquals(id, result.getId());
        verify(repo, times(1)).findById(id);
    }

    @Test
    public void testFindAll() {
        List<Pedido> pedidoList = new ArrayList<>();
        pedidoList.add(new Pedido());
        pedidoList.add(new Pedido());

        when(repo.findAll()).thenReturn(pedidoList);

        List<Pedido> result = pedidoService.findAll();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(repo, times(1)).findAll();
    }

    @Test
    public void testDeductNoteValue_WithDiscount() {
        Pedido pedido = new Pedido();
        Produto produto1 = new Produto();
        produto1.setPreco(10.0);
        produto1.setQuantidade(6);
        pedido.getProdutos().add(produto1);


        pedidoService.deductNoteValue(pedido, 1L);

        assertEquals(60.0, pedido.getValorNota(), 0.01);
        assertEquals(57.0, pedido.getValorComDesconto(), 0.01);
    }
}
