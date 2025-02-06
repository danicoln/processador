package com.microservices.pedidos.processador.service;

import com.microservices.pedidos.processador.entity.ItemPedido;
import com.microservices.pedidos.processador.entity.Pedido;
import com.microservices.pedidos.processador.repository.PedidoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PedidoService {

    private final Logger logger = LoggerFactory.getLogger(PedidoService.class);
    private final PedidoRepository repository;
    private final ProdutoService produtoService;
    private final ItemPedidoService itemPedidoService;

    public PedidoService(PedidoRepository repository, ProdutoService produtoService, ItemPedidoService itemPedidoService) {
        this.repository = repository;
        this.produtoService = produtoService;
        this.itemPedidoService = itemPedidoService;
    }

    @Transactional
    public void save(Pedido pedido){

        //salva os produtos
        List<ItemPedido> itensSalvos = produtoService.save(pedido.getItens());

        //salva os itens do pedido
        itemPedidoService.save(itensSalvos);

        //salva o pedido
        repository.save(pedido);

        logger.info("Pedido salvo:: {}", pedido);
    }

}
