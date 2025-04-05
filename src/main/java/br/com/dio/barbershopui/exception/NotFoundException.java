package br.com.dio.barbershopui.exception;

/**
 * Exceção lançada quando um recurso não é encontrado no sistema.
 * 
 * Situações típicas:
 * - Entidade não encontrada por ID
 * - Recurso ausente em operações de busca
 * - Registro inexistente em atualizações/exclusões
 */
public class NotFoundException extends RuntimeException {

    /**
     * Cria uma exceção com mensagem personalizada sobre o recurso não encontrado.
     * @param message Descrição detalhada (ex: "Cliente com ID 123 não encontrado")
     */
    public NotFoundException(String message) {
        super(message); // Chama o construtor da classe pai RuntimeException
    }
}