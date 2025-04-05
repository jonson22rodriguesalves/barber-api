package br.com.dio.barbershopui.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * DTO que representa a resposta com todos os agendamentos de um mês específico.
 * Usado como resposta para endpoints como GET /schedules/{year}/{month}.
 * 
 * @param year Ano dos agendamentos
 * @param month Mês dos agendamentos (1-12)
 * @param scheduledAppointments Lista de agendamentos do período
 */
public record ScheduleAppointmentMonthResponse(
        @JsonProperty("year") // Nome da propriedade no JSON
        int year,
        
        @JsonProperty("month") 
        int month,
        
        @JsonProperty("scheduledAppointments") // Nome no JSON para a lista
        List<ClientScheduleAppointmentResponse> scheduledAppointments
) {
    /**
     * Exemplo de JSON gerado:
     * {
     *   "year": 2023,
     *   "month": 11,
     *   "scheduledAppointments": [
     *     {
     *       "clientId": 1,
     *       "clientName": "João Silva",
     *       "day": 15,
     *       ...
     *     },
     *     ...
     *   ]
     * }
     */
}