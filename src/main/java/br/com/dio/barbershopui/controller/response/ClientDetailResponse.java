package br.com.dio.barbershopui.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Classe que representa a resposta detalhada de um cliente.
 * Contém informações completas para visualização de perfil ou edição.
 * Utilizado quando é necessário exibir todos os dados sensíveis do cliente.
 */
public record ClientDetailResponse(
        // ID único do cliente no banco de dados (imutável)
        @JsonProperty("id")
        Long id,

        // Nome completo legal do cliente (para documentos)
        @JsonProperty("name")
        String name,

        // E-mail principal (usado para login e notificações)
        @JsonProperty("email")
        String email,

        // Telefone principal com DDD (para contato oficial)
        @JsonProperty("phone")
        String phone
) {}