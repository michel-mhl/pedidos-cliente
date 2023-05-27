package com.eicon.pedidoscliente;

import com.eicon.pedidoscliente.domain.Cliente;
import com.eicon.pedidoscliente.domain.Pedido;
import com.eicon.pedidoscliente.domain.Produto;
import com.eicon.pedidoscliente.repositories.ClienteRepository;
import com.eicon.pedidoscliente.repositories.PedidoRepository;
import com.eicon.pedidoscliente.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

@SpringBootApplication
public class PedidosClienteApplication implements CommandLineRunner {
    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private ClienteRepository clienterRepoitory;
    @Autowired
    private PedidoRepository pedidoRepository;

    public static void main(String[] args) {
        SpringApplication.run(PedidosClienteApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        Cliente cli1 = new Cliente(null, "michel");
        clienterRepoitory.save(cli1);

        Produto p1 = new Produto(null, "cadeira", 70.00);
        Produto p2 = new Produto(null, "mesa", 150.00);
        Produto p3 = new Produto(null, "geladeira", 1900.00);
        Produto p4 = new Produto(null, "fogão", 700.90);
        Produto p5 = new Produto(null, "armario", 1600.00);

        produtoRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));

        Pedido ped1 = new Pedido(null, cli1, LocalDateTime.of(2023, 05, 24, 10, 20));
        Pedido ped2 = new Pedido(null, cli1, LocalDateTime.of(2023, 05, 24, 10, 20));
        pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
        ped1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
        ped2.getProdutos().addAll(Arrays.asList(p4, p5));



        cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
        clienterRepoitory.save(cli1);








    }
}
