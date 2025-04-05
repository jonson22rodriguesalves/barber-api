package br.com.dio.barbershopui.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Classe que representa a resposta detalhada de um agendamento de cliente.
 * Contém informações pessoais do cliente para visualização detalhada ou confirmação de agendamento.
 * Difere das outras respostas por focar especificamente no contexto de agendamentos.
 */
public record ClientScheduleAppointmentDetailResponse(
        // ID único do agendamento (para operações como cancelamento/reagendamento)
        @JsonProperty("id")
        Long id,

        // Nome completo do cliente (para identificação visual)
        @JsonProperty("name")
        String name,

        // E-mail para notificações e confirmações
        @JsonProperty("email")
        String email,

        // Telefone para contato emergencial sobre o agendamento
        @JsonProperty("phone")
        String phone
) {}