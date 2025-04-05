package br.com.dio.barbershopui.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Classe que representa a resposta de uma operação de salvamento de cliente.
 * Utiliza o padrão Record do Java para imutabilidade e simplicidade.
 * Anotações do Jackson (@JsonProperty) garantem o mapeamento correto para JSON.
 */
public record SaveClientResponse(
        // ID único do cliente no sistema
        @JsonProperty("id")
        Long id,

        // Nome completo do cliente
        @JsonProperty("name")
        String name,

        // Endereço de e-mail do cliente
        @JsonProperty("email")
        String email,

        // Telefone de contato do cliente
        @JsonProperty("phone")
        String phone
) {}