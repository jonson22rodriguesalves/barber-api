package br.com.dio.barbershopui.exceptionhandler;

import br.com.dio.barbershopui.controller.response.ProblemResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.OffsetDateTime;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

// Classe global para tratamento de exceções na aplicação
@Log4j2 // Gerenciamento de logs automatizado via Lombok
@ControllerAdvice // Habilita o tratamento centralizado de exceções em todos os controllers
public class BarberShopExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Captura exceções não tratadas em outros handlers (genéricas).
     * @param ex Exceção lançada
     * @param request Requisição web que originou o erro
     * @return ResponseEntity com detalhes do erro no formato ProblemResponse
     */
    @ExceptionHandler(Exception.class) // Define que este método trata qualquer Exception
    public ResponseEntity<?> handleUncaught(final Exception ex, final WebRequest request) {
        // Loga o erro com stacktrace completo
        log.error("handleUncaught: ", ex);

        // Define o status HTTP como 500 (INTERNAL_SERVER_ERROR)
        var status = INTERNAL_SERVER_ERROR;

        // Constrói a resposta de erro padronizada
        var response = ProblemResponse.builder()
                .status(status.value()) // Código HTTP (500)
                .timestamp(OffsetDateTime.now()) // Data/hora do erro
                .message(ex.getMessage()) // Mensagem da exceção
                .build();

        // Retorna a resposta tratada
        return handleExceptionInternal(ex, response, new HttpHeaders(), status, request);
    }
}