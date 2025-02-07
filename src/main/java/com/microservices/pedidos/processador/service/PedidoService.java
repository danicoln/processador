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
    public Pedido save(Pedido pedido){
        List<ItemPedido> itens = getItemAndPedidos(pedido);
        pedido.setItens(itens);

        //salva o pedido
        Pedido pedidoSalvo = repository.save(pedido);
        logger.info("Pedido salvo:: {}", pedido);

        return pedidoSalvo;

    }

    private List<ItemPedido> getItemAndPedidos(Pedido pedido) {
        //salva os produtos
        List<ItemPedido> itens = produtoService.save(pedido.getItens());
        for (ItemPedido item : itens) {
            item.setPedido(pedido);
        }
        //salva os itens do pedido
        return itemPedidoService.save(itens);
    }

}
