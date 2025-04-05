package br.com.dio.barbershopui.exception;

/**
 * Exceção lançada quando um número de telefone já está em uso no sistema.
 * 
 * Situações típicas:
 * - Tentativa de cadastrar um telefone já registrado para outro cliente
 * - Atualização de telefone para um número já existente
 */
public class PhoneInUseException extends RuntimeException {

    /**
     * Cria uma nova exceção com mensagem personalizada
     * @param message Descrição detalhada do erro (ex: "Telefone 99999-9999 já cadastrado para outro cliente")
     */
    public PhoneInUseException(String message) {
        super(message); // Passa a mensagem para a classe pai (RuntimeException)
    }
}