package br.com.dio.barbershopui.exception;

/**
 * Exceção lançada quando um e-mail já está cadastrado no sistema.
 * Representa um erro de negócio para casos de tentativa de cadastro duplicado.
 */
public class EmailInUseException extends RuntimeException {

    /**
     * Cria uma exceção com mensagem personalizada sobre o e-mail em conflito.
     * @param message Descrição detalhada do erro (ex: "O e-mail cliente@example.com já está em uso")
     */
    public EmailInUseException(String message) {
        super(message); // Passa a mensagem para a classe pai RuntimeException
    }
}