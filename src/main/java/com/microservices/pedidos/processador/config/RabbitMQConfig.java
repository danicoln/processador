package com.microservices.pedidos.processador.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${rabbitmq.exchange.name}")
    private String exchangeName;

    @Value("${rabbitmq.queue.name}")
    private String queueName;

    /**
     * Retorna uma exchange do tipo Fanout
     */
    @Bean
    public FanoutExchange pedidoExchange() {
        return new FanoutExchange(exchangeName);
    }

    /**
     * Retorna uma fila Queue
     */
    @Bean
    public Queue processadorQueue() {
        return new Queue(queueName);
    }

    /**
     * Método que faz o binding da fila com a exchange
     */
    @Bean
    public Binding binding() {
        return BindingBuilder.bind(processadorQueue()).to(pedidoExchange());
    }

    /**
     * Retorna uma nova conexão com o RabbitMq
     */
    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }

    /**
     * Converte as mensagens em JSON
     */
    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    /**
     * Faz a conexão com o RabbitMQ para enviar e receber as mensagens
     */
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory,
                                         MessageConverter messageConverter) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter);
        return rabbitTemplate;
    }

    /**
     * A configuração é aplicada no momento em que o Spring é inicializado
     */
    @Bean
    public ApplicationListener<ApplicationReadyEvent> applicationReadyEventApplicationListener(
            RabbitAdmin rabbitAdmin
    ) {
        return event -> rabbitAdmin.initialize();
    }

}
