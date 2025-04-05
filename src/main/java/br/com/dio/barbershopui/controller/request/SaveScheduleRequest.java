package br.com.dio.barbershopui.controller.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import java.time.OffsetDateTime;

/**
 * Classe que representa a requisição de agendamento de um horário na barbearia.
 * Valida os dados essenciais para criação de um novo agendamento.
 * 
 * Obs.: Datas devem estar no formato ISO-8601 (ex: "2023-12-25T10:00:00-03:00")
 */
public record SaveScheduleRequest(
        // Data/hora de início do atendimento (obrigatória)
        @NotNull(message = "A data/hora de início é obrigatória")
        @JsonProperty("startAt")
        OffsetDateTime startAt,

        // Data/hora de término do atendimento (obrigatória)
        @NotNull(message = "A data/hora de término é obrigatória")
        @JsonProperty("endAt")
        OffsetDateTime endAt,

        // ID do cliente (deve existir na base de dados)
        @NotNull(message = "O ID do cliente é obrigatório")
        @JsonProperty("clientId")
        Long clientId
) {}