package br.com.dio.barbershopui.exception;

// Exceção customizada para indicar que um agendamento está em uso/conflito
public class ScheduleInUseException extends RuntimeException {

    /**
     * Construtor que recebe uma mensagem de erro personalizada.
     * @param message Descrição detalhada do erro (ex: "Horário já reservado")
     */
    public ScheduleInUseException(String message) {
        super(message); // Chama o construtor da classe pai (RuntimeException)
    }
}