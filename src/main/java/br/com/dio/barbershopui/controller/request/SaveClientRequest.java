package br.com.dio.barbershopui.controller.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

/**
 * Classe que representa a requisição de cadastro de um novo cliente.
 * Inclui validações básicas para garantir a integridade dos dados antes da persistência.
 */
public record SaveClientRequest(
        // Nome completo do cliente (campo obrigatório)
        @NotNull(message = "O nome é obrigatório")
        @JsonProperty("name")
        String name,

        // E-mail válido e único no sistema (campo obrigatório)
        @NotNull(message = "O e-mail é obrigatório")
        @Email(message = "Formato de e-mail inválido")
        @JsonProperty("email")
        String email,

        // Telefone para contato (campo obrigatório)
        @NotNull(message = "O telefone é obrigatório")
        @JsonProperty("phone")
        String phone
) {}