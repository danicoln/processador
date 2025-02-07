package com.microservices.pedidos.processador.service;

import com.microservices.pedidos.processador.entity.ItemPedido;
import com.microservices.pedidos.processador.repository.ItemPedidoRespository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ItemPedidoService {

    private final ItemPedidoRespository respository;

    public ItemPedidoService(ItemPedidoRespository respository) {
        this.respository = respository;
    }

    @Transactional
    public List<ItemPedido> save(List<ItemPedido> itens) {
        itens.forEach(this::salvar);
        return respository.saveAll(itens);
    }

    @Transactional
    public void salvar(ItemPedido itemPedido){
        respository.save(itemPedido);
    }
}
