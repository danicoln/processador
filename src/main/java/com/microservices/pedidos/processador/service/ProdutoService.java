package com.microservices.pedidos.processador.service;

import com.microservices.pedidos.processador.entity.ItemPedido;
import com.microservices.pedidos.processador.repository.ProdutoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository repository;

    public ProdutoService(ProdutoRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public List<ItemPedido> save(List<ItemPedido> itens) {
        itens.forEach(item -> repository.save(item.getProduto()));
        return itens;
    }
}
