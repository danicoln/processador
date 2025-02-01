package com.microservices.pedidos.processador.repository;

import com.microservices.pedidos.processador.entity.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemPedidoRespository extends JpaRepository<ItemPedido, Long> {
}
