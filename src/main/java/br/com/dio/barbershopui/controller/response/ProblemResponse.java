package br.com.dio.barbershopui.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import java.time.OffsetDateTime;

/**
 * Classe que representa uma resposta padronizada para erros/problemas na API.
 * Segue o padrão RFC 7807 (Problem Details for HTTP APIs) de forma simplificada.
 * Utiliza Record para imutabilidade e Builder do Lombok para construção flexível.
 */
public record ProblemResponse(
        // Código HTTP de status (ex: 400, 404, 500)
        @JsonProperty("status")
        Integer status,
        
        // Timestamp ISO-8601 do momento em que o erro ocorreu
        @JsonProperty("timestamp")
        OffsetDateTime timestamp,
        
        // Mensagem descritiva do erro para auxiliar no debugging
        @JsonProperty("message")
        String message
) {
    /**
     * Builder do Lombok que permite:
     * - Criação imutável (ex: new ProblemResponse.ProblemResponseBuilder().status(400).build())
     * - Cópias modificadas (ex: problemResponse.toBuilder().message("Novo detalhe").build())
     */
    @Builder(toBuilder = true)
    public ProblemResponse{}
}