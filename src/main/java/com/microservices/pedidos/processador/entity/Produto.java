package com.microservices.pedidos.processador.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "produto")
@Table(name = "produto")
public class Produto {

    @Id
    private UUID id = UUID.randomUUID();
    private String nome;
    private Double valor;
}
