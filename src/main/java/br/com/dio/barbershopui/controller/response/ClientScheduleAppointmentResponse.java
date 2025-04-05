package br.com.dio.barbershopui.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.OffsetDateTime;

/**
 * Classe que representa a resposta ao agendamento de um horário na barbearia.
 * Contém informações tanto do slot de horário quanto do cliente associado.
 * Ideal para confirmações de agendamento e exibição na interface.
 */
public record ClientScheduleAppointmentResponse(
        // ID único do agendamento no sistema
        @JsonProperty("id")
        Long id,

        // Dia numérico da semana (1=Segunda, 7=Domingo, conforme ISO-8601)
        @JsonProperty("day")
        Integer day,

        // Data/hora de início no formato ISO-8601 (ex: "2023-12-25T10:00:00-03:00")
        @JsonProperty("startAt")
        OffsetDateTime startAt,

        // Data/hora de término no formato ISO-8601
        @JsonProperty("endAt")
        OffsetDateTime endAt,

        // ID do cliente (para operações internas)
        @JsonProperty("clientId")
        Long clientId,

        // Nome do cliente para exibição amigável
        @JsonProperty("clientName")
        String clientName
) {}