package br.com.dio.barbershopui.controller.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * DTO (Data Transfer Object) para representar a resposta de uma atualização de cliente.
 * Usado como corpo de resposta em endpoints PUT /clients/{id}.
 * 
 * Propriedades:
 * @param id Identificador único do cliente
 * @param name Nome completo do cliente
 * @param email Endereço de e-mail único
 * @param phone Número de telefone formatado
 */
public record UpdateClientResponse(
        @JsonProperty("id") // Define o nome da propriedade no JSON
        Long id,
        
        @JsonProperty("name") 
        String name,
        
        @JsonProperty("email")
        String email,
        
        @JsonProperty("phone") 
        String phone
) {
    /**
     * Exemplo de JSON gerado:
     * {
     *   "id": 1,
     *   "name": "João Silva",
     *   "email": "joao@example.com",
     *   "phone": "11999998888"
     * }
     */
}