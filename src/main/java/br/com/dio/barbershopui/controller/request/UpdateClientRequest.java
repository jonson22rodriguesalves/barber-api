package br.com.dio.barbershopui.controller.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

/**
 * Classe que representa a requisição de atualização de dados de um cliente.
 * Inclui validações básicas para garantir a integridade dos dados.
 * 
 * Obs.: Não inclui o ID pois normalmente vem na URL (PATCH /clients/{id})
 */
public record UpdateClientRequest(
        // Nome completo (obrigatório, sem validação de formato específico)
        @NotNull(message = "O nome não pode ser nulo")
        @JsonProperty("name")
        String name,

        // E-mail válido (formato padrão e obrigatório)
        @NotNull(message = "O e-mail não pode ser nulo")
        @Email(message = "Deve ser um e-mail válido")
        @JsonProperty("email")
        String email,

        // Telefone (obrigatório, sem validação de formato específico)
        @NotNull(message = "O telefone não pode ser nulo")
        @JsonProperty("phone")
        String phone
) {}