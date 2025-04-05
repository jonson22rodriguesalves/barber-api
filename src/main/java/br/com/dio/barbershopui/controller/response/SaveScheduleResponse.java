package br.com.dio.barbershopui.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.OffsetDateTime;

/**
 * DTO (Data Transfer Object) para representar a resposta após a criação de um agendamento.
 * Retornado pelo endpoint POST /schedules com status HTTP 201 Created.
 * 
 * @param id Identificador único do agendamento criado
 * @param startAt Data/hora de início do agendamento (com timezone)
 * @param endAt Data/hora de término do agendamento (com timezone)
 * @param clientId Identificador do cliente associado
 */
public record SaveScheduleResponse(
        @JsonProperty("id") // Nome da propriedade no JSON
        Long id,
        
        @JsonProperty("startAt") 
        OffsetDateTime startAt,  // Usa OffsetDateTime para precisão temporal com fuso horário
        
        @JsonProperty("endAt") 
        OffsetDateTime endAt,
        
        @JsonProperty("clientId") 
        Long clientId
) {
    /**
     * Exemplo de JSON gerado:
     * {
     *   "id": 123,
     *   "startAt": "2023-11-20T14:00:00-03:00",
     *   "endAt": "2023-11-20T15:00:00-03:00",
     *   "clientId": 456
     * }
     */
}