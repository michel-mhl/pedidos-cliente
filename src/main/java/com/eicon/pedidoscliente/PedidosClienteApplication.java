package com.eicon.pedidoscliente;

import com.eicon.pedidoscliente.domain.Cliente;
import com.eicon.pedidoscliente.domain.Pedido;
import com.eicon.pedidoscliente.domain.Produto;
import com.eicon.pedidoscliente.repositories.ClienteRepository;
import com.eicon.pedidoscliente.repositories.PedidoRepository;
import com.eicon.pedidoscliente.repositories.ProdutoRepository;
import com.eicon.pedidoscliente.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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

    @Autowired
    private PedidoService pedidoService;

    public static void main(String[] args) {
        SpringApplication.run(PedidosClienteApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        Produto p1 = new Produto(null, "cadeira", 70.00,6);
        Produto p2 = new Produto(null, "mesa", 150.00,null);
        Produto p3 = new Produto(null, "geladeira", 1900.00,1);
        Produto p4 = new Produto(null, "fogão", 700.90,2);
        Produto p5 = new Produto(null, "armario", 1600.00,null);

        produtoRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));

        Cliente cli1 = new Cliente(null, "gabriela");
        Cliente cli2 = new Cliente(null, "michel");
        Cliente cli3 = new Cliente(null, "alice");
        Cliente cli4 = new Cliente(null, "geovanna");
        Cliente cli5 = new Cliente(null, "maria");
        Cliente cli6 = new Cliente(null, "nina");
        Cliente cli7 = new Cliente(null, "pelucia");
        Cliente cli8 = new Cliente(null, "caramelo");
        Cliente cli9 = new Cliente(null, "jada");
        Cliente cli10 = new Cliente(null, "noah");


        clienterRepoitory.saveAll(Arrays.asList(cli1, cli2));


        Pedido ped1 = new Pedido(null, cli1, null,null,null);
        Pedido ped2 = new Pedido(null, cli2, LocalDateTime.of(2023, 05, 24, 10, 20),null,null);
        Pedido ped3 = new Pedido(null, cli1, LocalDateTime.of(2023, 05, 24, 10, 20),null,null);
        Pedido ped4 = new Pedido(null, cli1, LocalDateTime.of(2023, 05, 24, 10, 20),null,null);
        Pedido ped5 = new Pedido(null, cli1, LocalDateTime.of(2023, 05, 24, 10, 20),null,null);
        Pedido ped6 = new Pedido(null, cli1, LocalDateTime.of(2023, 05, 24, 10, 20),null,null);


        pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
        ped1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
        ped2.getProdutos().addAll(Arrays.asList(p4, p5));
        ped3.getProdutos().addAll(Arrays.asList(p4, p5));
        ped4.getProdutos().addAll(Arrays.asList(p4, p5));
        ped5.getProdutos().addAll(Arrays.asList(p4, p5));
        ped6.getProdutos().addAll(Arrays.asList(p4, p5));
        pedidoRepository.saveAll(Arrays.asList(ped1,ped2,ped3,ped4,ped5,ped6));


        cli1.getPedidos().addAll(Arrays.asList(ped1, ped2,ped3,ped4,ped5,ped6));
        cli2.getPedidos().addAll(Arrays.asList(ped1));
        clienterRepoitory.saveAll(Arrays.asList(cli1, cli2));


    }
}
