package br.com.dio.barbershopui.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Classe que representa a resposta simplificada para listagem de clientes.
 * Contém apenas os dados essenciais para exibição em listas/grids.
 * Difere de SaveClientResponse por não incluir todos os campos possíveis do cliente.
 */
public record ListClientResponse(
        // ID único do cliente (usado para operações subsequentes)
        @JsonProperty("id")
        Long id,

        // Nome completo para exibição na interface
        @JsonProperty("name")
        String name,

        // E-mail para contato rápido
        @JsonProperty("email")
        String email,

        // Telefone formatado para exibição
        @JsonProperty("phone")
        String phone
) {}