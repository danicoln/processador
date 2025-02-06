package com.microservices.pedidos.processador.listener;

import com.microservices.pedidos.processador.entity.Pedido;
import com.microservices.pedidos.processador.entity.enums.Status;
import com.microservices.pedidos.processador.service.PedidoService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class PedidoListener {
    private final PedidoService pedidoService;

    public PedidoListener(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @RabbitListener(queues = "pedidos.v1.pedido-criado.gerar-processamento")
    public void salvarPedido(Pedido pedido) {
        pedido.setStatus(Status.PROCESSADO);
        pedidoService.save(pedido);
    }
}
